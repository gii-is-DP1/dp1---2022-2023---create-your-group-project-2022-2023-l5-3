package org.springframework.samples.petclinic.logros;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.jugador.JugadorService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class LogrosController {
	
	private static final String VIEWS_LOGROS = "jugador/logrosJugador";
	
	private final LogrosService logrosService;
	private final JugadorService jugadorService;

	@Autowired
	public LogrosController(LogrosService logrosService, JugadorService jugadorService) {
		this.logrosService = logrosService;
		this.jugadorService = jugadorService;
	} 

	@GetMapping(value = "/jugador/logros")
	public String logrosUsuarioLogeado(Map<String, Object> model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Jugador jugador = jugadorService.findJugadorByUsername(username);
		List<Logros> conjunto = logrosService.findLogrosJugadorNull();
		for (Logros logro:conjunto){
			logro.setJugador(jugador);
		}
		List<Logros> logrosDelUsuarioLogeado = logrosService.findById(jugador.getId());
		getLogrosDeCadaJugador(jugador.getId());
		model.put("logros",logrosDelUsuarioLogeado);
		return VIEWS_LOGROS;
	}

	//SOLO LOS ADMIN PUEDEN VER LOS LOGROS DE LOS DEMÁS USUARIOS
	@GetMapping(value = "/jugador/logros/{id}")
	public String logrosDeCualquierUsuario(Map<String, Object> model, @PathVariable("id") int id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth != null){
			org.springframework.security.core.userdetails.User currentUser =  (org.springframework.security.core.userdetails.User) auth.getPrincipal();
			Collection<GrantedAuthority> usuario = currentUser.getAuthorities();
			for (GrantedAuthority usuarioR : usuario){
				String credencial = usuarioR.getAuthority();
				Jugador jugador = jugadorService.findJugadorById(id);
				List<Logros> conjunto = logrosService.findLogrosJugadorNull();
					
					for (Logros logro:conjunto){
						logro.setJugador(jugador);
					}
				if (credencial.equals("admin")) { 
					List<Logros> logrosDelUsuarioLogeado = logrosService.findById(id);
					getLogrosDeCadaJugador(id);
					model.put("logros",logrosDelUsuarioLogeado);
					return VIEWS_LOGROS;
				} else {
					/*List<Logros> logrosDelUsuarioLogeado = logrosService.findById(id);
					model.put("logros",logrosDelUsuarioLogeado);*/
					return "welcome";
				}
			}
		} else {
			return "exception";
		}
		return "exception";
	}

	public void getLogrosDeCadaJugador(Integer idJugador) {
		Jugador player = jugadorService.findJugadorById(idJugador);
		List<Logros> logros = logrosService.findById(player.getId());
		Integer primerId = logros.get(0).getId();
		
		for (Logros logro: logros){

			if (player.getPartidasJugadas() >= 5){
				if(logro.getId().equals(primerId)){
					logro.setIs_unlocked(true);
				}
			}
			if (player.getNumTotalPuntos() >= 100){
				if(logro.getId().equals(primerId+1)){
					logro.setIs_unlocked(true);
				}
			}
			if (player.getNumTotalMovimientos() >= 200 ){
				if(logro.getId().equals(primerId+2)){
					logro.setIs_unlocked(true);
				}
			} 
		}
	}
}