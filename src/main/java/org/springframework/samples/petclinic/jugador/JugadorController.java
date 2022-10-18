package org.springframework.samples.petclinic.jugador;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class JugadorController {

    private static final String VIEWS_JUGADOR_CREATE_OR_UPDATE_FORM = "jugador/createOrUpdateJugadorForm";

    private final JugadorService jugadorService;

    @Autowired
    public JugadorController(JugadorService jugadorService){
        this.jugadorService= jugadorService;
    }

    @GetMapping(value = "/jugador/new")
	public String initCreationForm(Map<String, Object> model) {
		Jugador jugador = new Jugador();
		model.put("jugador", jugador);
		return VIEWS_JUGADOR_CREATE_OR_UPDATE_FORM;
	}



    @PostMapping(value = "/jugador/new")
	public String processCreationForm(@Valid Jugador jugador, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_JUGADOR_CREATE_OR_UPDATE_FORM;
		}
		else {
			//creating owner, user and authorities
			this.jugadorService.saveJugador(jugador);
			
			return "redirect:/jugadores/" + jugador.getId();
		}
	}


    
}
