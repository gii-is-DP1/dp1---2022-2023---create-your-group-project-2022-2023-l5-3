package org.springframework.samples.petclinic.partida;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.jugador.JugadorService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;



@Controller

public class PartidaController {

	@Autowired
	private PartidaService partidaService;
	
	@Autowired
	private JugadorService jugadorService;

	
	private static final String VIEW_CREATE_PARTIDA = "partidas/createOrUpdatePartidaForm";
	private static final String VIEW_LIST = "partidas/partidaListEnCurso";
	private static final String VIEW_LIST2 = "partidas/partidaListFinalizadas";
	private static final String TABLERO = "tableros/tablero";

	

	@GetMapping(value = { "/partidas/enCurso" })
	public String showPartidaList(Map<String, Object> model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){
			org.springframework.security.core.userdetails.User currentUser =  (org.springframework.security.core.userdetails.User) auth.getPrincipal();
			Collection<GrantedAuthority> usuario = currentUser.getAuthorities();
			for (GrantedAuthority usuarioR : usuario){
				String credencial = usuarioR.getAuthority();
				if (credencial.equals("admin")) {
					List<Partida> partidas = new ArrayList<>();
					partidas = (List<Partida>) partidaService.findPartidasEnCurso();
					model.put("partidas", partidas);
					return VIEW_LIST;
				} else {
					return "welcome";
				}
			
			}
		} else {
			return "welcome";
		}

		return "welcome";
	}



	@GetMapping(value = { "/partidas/finalizadas" })
	public String showPartidaListFin(Map<String, Object> model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (auth != null){
			org.springframework.security.core.userdetails.User currentUser =  (org.springframework.security.core.userdetails.User) auth.getPrincipal();
			Collection<GrantedAuthority> usuario = currentUser.getAuthorities();
			for (GrantedAuthority usuarioR : usuario){
				String credencial = usuarioR.getAuthority();
				if (credencial.equals("admin")) {
					List<Partida> partidas = new ArrayList<>();
					partidas = (List<Partida>) partidaService.findPartidasFinalizadas();
					model.put("partidas", partidas);
					return VIEW_LIST2;
				} else {
					return "welcome";
				}
			
			}
		} else {
			return "welcome";
		}

		return "welcome";
	}

	@GetMapping(path="/partidas/create")
	public String initCreationForm(Map<String,Object> model){
		Partida partida = new Partida();
	
		partida.setNumMovimientos(0);
		partida.setMomentoInicio(LocalDateTime.now());
		partida.setVictoria(false);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		org.springframework.security.core.userdetails.User currentUser =  (org.springframework.security.core.userdetails.User) auth.getPrincipal();
		String usuario = currentUser.getUsername();
		Jugador player = jugadorService.findJugadorByUsername(usuario);
		partida.setJugador(player);
		model.put("partida", partida);
		return VIEW_CREATE_PARTIDA;
		
	}
	
	@PostMapping(path="/partidas/create")
	public String processCreationForm(@Valid Partida p, BindingResult result, Map<String, Object> model) {
		
		if (result.hasErrors()) {
			
			model.put("message", result.getAllErrors());
			return VIEW_CREATE_PARTIDA;
		}
		else {
			//Para asociar la partida nueva a un jugador:
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			org.springframework.security.core.userdetails.User currentUser =  (org.springframework.security.core.userdetails.User) auth.getPrincipal();
			String usuario = currentUser.getUsername();
			Jugador player = jugadorService.findJugadorByUsername(usuario);
			p.setJugador(player);
			p.setNumMovimientos(0);
			p.setMomentoInicio(LocalDateTime.now());
			p.setVictoria(false);
			this.partidaService.save(p);
			model.put("message", "Partida empezada");
			
			return TABLERO;
		
		
		}
	
	}

	//CREAR MÉTODO QUE FINALICE UNA PARTIDA
	@GetMapping(path = "/partidas/delete/{id}")
	public ModelAndView deletePartida(@PathVariable("id") int id, ModelMap modelMap) {
		Partida partida = partidaService.findById(id);
		partidaService.deletePartida(partida);
		ModelAndView result = new ModelAndView("partidas/partidaListFinalizadas");
		result.addObject("partidas", (List<Partida>) partidaService.findPartidasFinalizadas());
		return result;
		
	}
	
	
}
