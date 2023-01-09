package org.springframework.samples.petclinic.logros;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.jugador.JugadorService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
		logrosService.setLogrosDeCadaJugador();
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
					logrosService.setLogrosDeCadaJugador();
					model.put("logros",logrosDelUsuarioLogeado);
					return VIEWS_LOGROS;
				} else {
					return "welcome";
				}
			}
		} else {
			return "exception";
		}
		return "exception";
	}


	@GetMapping(value = "/jugador/logros/editar")
	public String editarLogrosAdmin (Map<String, Object> model) {
		List<Logros> logrosDefinidos = logrosService.findAll().stream().limit(3).collect(Collectors.toList());
		model.put("logros",logrosDefinidos);
		return "jugador/editarLogrosGeneral";
	}


	@GetMapping(value = "/jugador/logros/editar/{id}")
	public String editarLogroAdmin (Map<String, Object> model, @PathVariable("id") int id) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if(auth != null){
				if(auth.isAuthenticated()){
					org.springframework.security.core.userdetails.User currentUser =  (org.springframework.security.core.userdetails.User) auth.getPrincipal();
					try {
						Collection<GrantedAuthority> usuario = currentUser.getAuthorities();
						for (GrantedAuthority usuarioR : usuario){
						String credencial = usuarioR.getAuthority();
							if(credencial.equals("admin")){
								Logros logro = logrosService.findByIdlOGRO(id);
								Integer num = logro.getNumCondicion();
								model.put("num", num);
								model.put("name",logro.getName());
								model.put("description",logro.getDescription());
								model.put("image",logro.getImage());
								model.put("is_unlocked",logro.getIs_unlocked());
								model.put("jugador",logro.getJugador());
								model.put("logro",logro);
								return "jugador/editarLogro";
							}else{
								return "welcome";
							}
						}
					} catch (DataIntegrityViolationException ex){
						
						return "jugador/editarLogro";
					}
					
					
				}return "welcome";
			}
			return "welcome";
		
		}


		@PostMapping(value = "/jugador/logros/editar/{id}")
		public String processEditForm(@Valid Logros logro, BindingResult result, @PathVariable("id") int id, Map<String, Object> model){
			
			if(result.hasErrors()){
				return "jugador/editarLogro";
			} else {
						//logro.setId(id);
						//logro.setDescription(image);
						Logros logrosToUpdate=this.logrosService.findByIdlOGRO(id);
						BeanUtils.copyProperties(logro, logrosToUpdate, "id","name","image","is_unlocked","jugador"); 
						this.logrosService.save(logrosToUpdate);
						
						List<Logros> conjuntoLogros = logrosService.findLogrosByName(logrosToUpdate.getName());
						
						for (Logros logroIt :conjuntoLogros){
							logroIt.setNumCondicion(logrosToUpdate.getNumCondicion());
							logroIt.setDescription(logrosToUpdate.getDescription());
						}
						
						logrosService.setLogrosDeCadaJugador();
						model.put("message","Logro editado correctamente");
						
						List<Logros> logrosDefinidos = logrosService.findAll().stream().limit(3).collect(Collectors.toList());
						model.put("logros",logrosDefinidos);
						return "redirect:/jugador/logros/editar";
			}
	}
	
}