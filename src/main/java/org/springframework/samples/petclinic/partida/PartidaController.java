package org.springframework.samples.petclinic.partida;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.jugador.JugadorService;
import org.springframework.samples.petclinic.user.User;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



@Controller

public class PartidaController {

	@Autowired
	private PartidaService partidaService;
	
	@Autowired
	private JugadorService jugadorService;
	
	@Autowired
	private UserService userService;
	
	private static final String VIEW_CREATE_PARTIDA = "partidas/createOrUpdatePartidaForm";
	private static final String VIEW_LIST = "partidas/partidaList";
	private static final String TABLERO = "tableros/tablero";

	

    
	

	@GetMapping(value = { "/partidas" })
	public String showPartidaList(Map<String, Object> model) {
		
		List<Partida> partidas = new ArrayList<>();
        partidas = (List<Partida>) partidaService.findPartidas();
        
      

		model.put("partidas", partidas);
		return VIEW_LIST;
	}
	
	@GetMapping(path="/partidas/create")
	public String initCreationForm(Map<String,Object> model){
		Partida partida = new Partida();
	
		partida.setNumMovimientos(0);
		partida.setMomentoInicio(LocalDateTime.now());
		partida.setVictoria(false);
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
			this.partidaService.save(p);
			model.put("message", "Partida empezada");
			
			return TABLERO;
		}
	
		
	}
	

	
	
	
}
