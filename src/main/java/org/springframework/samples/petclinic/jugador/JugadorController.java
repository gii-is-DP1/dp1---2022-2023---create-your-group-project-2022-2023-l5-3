package org.springframework.samples.petclinic.jugador;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.samples.petclinic.logros.Logros;
import org.springframework.samples.petclinic.logros.LogrosService;
import org.springframework.samples.petclinic.partida.PartidaService;
import org.springframework.samples.petclinic.user.AuthoritiesService;
import org.springframework.samples.petclinic.user.User;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.security.core.Authentication;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class JugadorController {

    private static final String VIEWS_JUGADOR_CREATE_OR_UPDATE_FORM = "jugador/createOrUpdateJugadorForm";
	private static final String VIEW_JUGADORES = "users/UsersList";


    private final JugadorService jugadorService;
	private final AuthoritiesService authoritiesService;
	private final PartidaService partidaService;
	private final UserService userService;
	private final LogrosService logrosService;
    
	@Autowired
    public JugadorController(JugadorService jugadorService, AuthoritiesService authoritiesService, PartidaService partidaService,UserService userService, LogrosService logrosService){
        this.jugadorService= jugadorService;
		this.authoritiesService=authoritiesService;
		this.partidaService=partidaService;
		this.userService=userService;
		this.logrosService=logrosService;
    }

    @GetMapping(value = "/jugador/new")
	public String initCreationForm(Map<String, Object> model) {
		Jugador jugador = new Jugador();
		model.put("jugador", jugador);
		return VIEWS_JUGADOR_CREATE_OR_UPDATE_FORM;
	}

	@GetMapping(value = "/jugador/new/ad")
	public String initCreationFormADMIN(Map<String, Object> model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth != null){
			if(auth.isAuthenticated()){
				org.springframework.security.core.userdetails.User currentUser =  (org.springframework.security.core.userdetails.User) auth.getPrincipal();
				try{
					Collection<GrantedAuthority> usuario = currentUser.getAuthorities();
					for (GrantedAuthority usuarioR : usuario){
					String credencial = usuarioR.getAuthority();
						if(credencial.equals("admin")){
							Jugador jugador = new Jugador();
							model.put("jugador", jugador); 
							return VIEWS_JUGADOR_CREATE_OR_UPDATE_FORM;
						}else{
							return "welcome";
						}
					}
				} catch (DataIntegrityViolationException ex){
					
					return VIEWS_JUGADOR_CREATE_OR_UPDATE_FORM;
				}
				
				
			}
			
			return "welcome";
		} else {
			return "welcome";
		}
	
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
					UsernamePasswordAuthenticationToken authReq= new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());
					
					SecurityContextHolder.getContext().setAuthentication(authReq);
						//
					jugador.setNumTotalMovimientos();
					jugador.setNumTotalPuntos();
					jugador.setPartidasGanadas();
					jugador.setPartidasNoGanadas();
					jugador.setTotalTiempoJugado();
					/*Logros logro1 = new Logros("Máquina de jugar","Has jugado 5 partidas", false, "", jugador); 
					Logros logro2 = new Logros("No se te da nada mal","Has alcanzado los 100 puntos", false, "", jugador); 
					Logros logro3 = new Logros("¡Estás on fire!","Has alcanzado los 200 movimientos", false, "", jugador); 
					Set<Logros> set = new HashSet<>();
					set.add(logro1);
					set.add(logro2);
					set.add(logro3);
					jugador.setLogros(set);*/
					//HAY QUE AÑADIR LOS LOGROS A LOS USUARIOS REGISTRADOS NUEVOS POR LA WEB
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

	//SI SE LE DA DOS VECES A ACTUALIZAR DATOS SEGUIDAS SIN DARLE A VOLVER, SALTA ERROR
	//Editar jugador
	@GetMapping(value = "/jugador/edit/{id}")
	public String initEditForm(Model model, @PathVariable("id") int id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth != null){
			if(auth.isAuthenticated()){
				org.springframework.security.core.userdetails.User currentUser =  (org.springframework.security.core.userdetails.User) auth.getPrincipal();
				String user = currentUser.getUsername();
				try{
					Jugador player = jugadorService.findJugadorByUsername(user);
					Collection<GrantedAuthority> usuario = currentUser.getAuthorities();
					for (GrantedAuthority usuarioR : usuario){
					String credencial = usuarioR.getAuthority();
						if(player.getId()==id || credencial.equals("admin")){
							Jugador jugador = jugadorService.findJugadorById(id);
							String username = jugador.getUser().getUsername();
							String pass = jugador.getUser().getPassword();
							
							model.addAttribute("pass", pass);
							model.addAttribute("username", username);
							
							model.addAttribute(jugador);
							return VIEWS_JUGADOR_CREATE_OR_UPDATE_FORM;
						}else{
							return "welcome";
						}
					}
				} catch (DataIntegrityViolationException ex){
					
					return VIEWS_JUGADOR_CREATE_OR_UPDATE_FORM;
				}
				
				
			}return "welcome";
		}
		return "welcome";
	
	}
	
	@PostMapping(value = "/jugador/edit/{id}")
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
					return VIEWS_JUGADOR_CREATE_OR_UPDATE_FORM;
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

	//Ver tu perfil siendo jugador
	@GetMapping(value = "/jugador/perfil")
	public String verPerfilJugador(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth != null){
			if(auth.isAuthenticated()){
				org.springframework.security.core.userdetails.User currentUser =  (org.springframework.security.core.userdetails.User) auth.getPrincipal();
				String usuario = currentUser.getUsername();
				Jugador jugador = jugadorService.findJugadorByUsername(usuario);
				model.addAttribute("id", jugador.getId());
				model.addAttribute(jugador);
				return "jugador/showJugador";
			}
			return "welcome";
		}
		return "welcome";
	
	}

	@GetMapping(value = "/jugador/perfil/{id}")
	public String verPerfilJugadorADMIN(@PathVariable("id") int id,Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth != null){
			org.springframework.security.core.userdetails.User currentUser =  (org.springframework.security.core.userdetails.User) auth.getPrincipal();
			Collection<GrantedAuthority> usuario = currentUser.getAuthorities();
			for (GrantedAuthority usuarioR : usuario){
				String credencial = usuarioR.getAuthority();
				if (credencial.equals("admin")) {
					Jugador jugador = jugadorService.findJugadorById(id);
					model.addAttribute("id", jugador.getId());
					model.addAttribute(jugador);
					return "jugador/showJugadorByIdADMIN";
				} else {
					return "welcome";
				}
			}	
		} else {
		return "welcome";
		}
	return "exception";
	}
	
	
	@GetMapping(value = "/jugador/estadisticas/{id}")
	public ModelAndView mostrarEstadisticas(@PathVariable("id") int id){
		ModelAndView result = new ModelAndView("jugador/estadisticasJugador");
		Jugador jugador = jugadorService.findJugadorById(id);
		result.addObject(jugador);
		return result;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@GetMapping(path = "/jugador/delete/{id}")
	public ModelAndView deleteGame(@PathVariable("id") int id, ModelMap modelMap) {
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	if(auth != null){
		org.springframework.security.core.userdetails.User currentUser =  (org.springframework.security.core.userdetails.User) auth.getPrincipal();
		Collection<GrantedAuthority> usuario = currentUser.getAuthorities();
		for (GrantedAuthority usuarioR : usuario){
			String credencial = usuarioR.getAuthority();
			if (credencial.equals("admin")) {
				Jugador jugador = jugadorService.findJugadorById(id);
				if(jugador.getUser().getUsername().equals(currentUser.getUsername())){
					ModelAndView result = new ModelAndView(VIEW_JUGADORES);
					List<User> usuarios = userService.findAllUsers();
					Comparator<User> comparador= Comparator.comparing(User::getJugadorId);
					List<User> listaOrdenada = usuarios.stream().sorted(comparador).collect(Collectors.toList());
					result.addObject("users", listaOrdenada);
					return result;
				} else {
					jugadorService.deleteJugador(jugador);
					ModelAndView result = new ModelAndView(VIEW_JUGADORES);
					List<User> usuarios = userService.findAllUsers();
					Comparator<User> comparador= Comparator.comparing(User::getJugadorId);
					List<User> listaOrdenada = usuarios.stream().sorted(comparador).collect(Collectors.toList());
					result.addObject("users", listaOrdenada);
					return result;
				}
				
			}
		}
		
	}else{
		return new ModelAndView("exception");
	}
	return new ModelAndView("exception");
	}
	
}
