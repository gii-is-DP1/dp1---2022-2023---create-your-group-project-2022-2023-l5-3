package org.springframework.samples.petclinic.jugador;

import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.DataIntegrityViolationException;

import org.springframework.samples.petclinic.jugador.Exceptions.UsernameExceptions;

import org.springframework.samples.petclinic.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

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
			

			if(violations.isEmpty()){
				try{

					this.jugadorService.saveJugador(jugador);
					return "redirect:/";
				}catch (DataIntegrityViolationException ex){
					result.rejectValue("user.username", "Nombre de usuario duplicado","Este nombre de usuario ya esta en uso");
					return VIEWS_JUGADOR_CREATE_OR_UPDATE_FORM;
				}
			}

			else{
				for(ConstraintViolation<User> v : violations){
					result.rejectValue("user."+ v.getPropertyPath(),v.getMessage(),v.getMessage());
				}
								
				return VIEWS_JUGADOR_CREATE_OR_UPDATE_FORM;
			}
		}
	}

	//Editar jugador
	@GetMapping(value = "/jugador/{id}/edit")
	public String initEditForm(Model model, @PathVariable("id") int id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth != null){
			if(auth.isAuthenticated()){
				
				org.springframework.security.core.userdetails.User currentUser =  (org.springframework.security.core.userdetails.User) auth.getPrincipal();
				String usuario = currentUser.getUsername();
				try{
					Jugador player = jugadorService.findJugadorByUsername(usuario);
					if(player.getId()==id){
						Jugador jugador = jugadorService.findJugadorById(id);
						model.addAttribute(jugador);
						return VIEWS_JUGADOR_CREATE_OR_UPDATE_FORM;
					}else{
						return "welcome";
					}
				}catch (DataIntegrityViolationException ex){
					String a = ex.getMessage();
					return VIEWS_JUGADOR_CREATE_OR_UPDATE_FORM;
				}
				//Jugador player = jugadorService.findJugadorByUsername(usuario);
				
			}return "welcome";
		}
		return "welome";
	
	}
	
	
	@PostMapping(value = "/jugador/{id}/edit")
	public String processEditForm(@Valid Jugador jugador, BindingResult result, @PathVariable("id") int id){
		if(result.hasErrors()){
			return VIEWS_JUGADOR_CREATE_OR_UPDATE_FORM;
		}
		else {
			
			User user = jugador.getUser();
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();
			Set<ConstraintViolation<User>> violations = validator.validate(user);
			

			if(violations.isEmpty()){
				try{
					jugador.setId(id);
					this.jugadorService.saveJugador(jugador);
					return "welcome";
				}catch (DataIntegrityViolationException ex){
					result.rejectValue("user.username", "Nombre de usuario duplicado","Este nombre de usuario ya esta en uso");
					return VIEWS_JUGADOR_CREATE_OR_UPDATE_FORM;
				}
			}

			else{
				for(ConstraintViolation<User> v : violations){
					result.rejectValue("user."+ v.getPropertyPath(),v.getMessage(),v.getMessage());
				}
								
				return VIEWS_JUGADOR_CREATE_OR_UPDATE_FORM;
			}
		}
	}
    
	@GetMapping(value = "/jugador/{id}/estadisticas")
	public ModelAndView mostrarEstadisticas(@PathVariable("id") int id){
		ModelAndView mav = new ModelAndView("jugador/estadisticasJugador");
		mav.addObject(this.jugadorService.findJugadorById(id));
		return mav;
	}
}
