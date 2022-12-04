package org.springframework.samples.petclinic.logros;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.jugador.JugadorService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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

	@GetMapping(value = "/jugador/logros/{id}")
	public String initCreationForm(Map<String, Object> model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Jugador player = jugadorService.findJugadorByUsername(username);
		List<Logros> logrosDelUsuarioLogeado = logrosService.findById(player.getId());
		model.put("logros",logrosDelUsuarioLogeado);
		return VIEWS_LOGROS;
	}
}