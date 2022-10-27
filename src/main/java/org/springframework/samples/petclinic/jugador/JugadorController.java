package org.springframework.samples.petclinic.jugador;

import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.jugador.Exceptions.UsernameExceptions;
import org.springframework.samples.petclinic.user.User;
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
			
			User user = jugador.getUser();
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();
			Set<ConstraintViolation<User>> violations = validator.validate(user);
			for(ConstraintViolation<User> v : violations){
				result.rejectValue("user."+ v.getPropertyPath(),v.getMessage());
			}
			result.rejectValue("firstName", "fallo");

			return "redirect:/jugadores/" + jugador.getId();
		}
	}


    
}
