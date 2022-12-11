package org.springframework.samples.petclinic.jugador;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
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
import org.springframework.samples.petclinic.partida.Partida;
import org.springframework.samples.petclinic.partida.PartidaService;
import org.springframework.samples.petclinic.user.User;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.security.core.Authentication;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.concurrent.ListenableFutureAdapter;
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
	private final UserService userService;
	private final LogrosService logrosService;
	private final PartidaService partidaService;
    
	@Autowired
    public JugadorController(JugadorService jugadorService, UserService userService, LogrosService logrosService, PartidaService partidaService){
        this.jugadorService= jugadorService;
		this.userService=userService;
		this.logrosService=logrosService;
		this.partidaService=partidaService;
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
					jugador.setAllStats0();
					Logros logro1 = new Logros();
					Logros logro2 = new Logros();
					Logros logro3 = new Logros();
					List<Logros> lista = new ArrayList<>();
					lista.add(logro1);
					lista.add(logro2);
					lista.add(logro3);
					for(Logros logro:lista){
						if(lista.get(0).equals(logro)){
							logro.setName("Máquina de jugar");
							logro.setDescription("Has jugado 5 partidas");
						} else if(lista.get(1).equals(logro)){
							logro.setName("No se te da nada mal");
							logro.setDescription("Has alcanzado los 100 puntos");
						} else {
							logro.setName("¡Estás on fire!");
							logro.setDescription("Has alcanzado los 200 movimientos");
						}
						logro.setIs_unlocked(false);
						logro.setImage("");
						logro.setJugador(jugador);	
					}
					logrosService.save(lista.get(0));
					logrosService.save(lista.get(1));
					logrosService.save(lista.get(2));
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
					//SI SE EDITA UN JUGADOR, SE PIERDEN LAS STATS Y LOS LOGROS : REGLA DE NEGOCIO
					jugador.setId(id);
					jugador.setAllStats0();
					this.jugadorService.saveJugador(jugador);
					List<Logros> conjunto = logrosService.findLogrosJugadorNull();
					for (Logros logro:conjunto){
						logro.setJugador(jugador);
					}
					return VIEWS_JUGADOR_CREATE_OR_UPDATE_FORM;

				}catch (DataIntegrityViolationException ex){
					result.rejectValue("user.username", "Nombre de usuario duplicado","Este nombre de usuario ya esta en uso");
					return VIEWS_JUGADOR_CREATE_OR_UPDATE_FORM;
				}
			}else{
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
					model.addAttribute("message", "You need to be admin for see this profile!");
					return "error";
				}
			}	
		} else {
			
			model.addAttribute("message", "Id does not exist for any player!");
			return "error";
		}
		model.addAttribute("message", "Id does not exist for any player!");
		return "error";
	}
	
	
	@GetMapping(value = "/jugador/estadisticas/{id}")
	public ModelAndView mostrarEstadisticasAdmin(@PathVariable("id") int id){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth != null){
			org.springframework.security.core.userdetails.User currentUser =  (org.springframework.security.core.userdetails.User) auth.getPrincipal();
			Collection<GrantedAuthority> usuario = currentUser.getAuthorities();
			for (GrantedAuthority usuarioR : usuario){
				String credencial = usuarioR.getAuthority();
				if (credencial.equals("admin")) {
					ModelAndView result = new ModelAndView("jugador/estadisticasJugador");
					Jugador jugador = jugadorService.findJugadorById(id);
					setEstadisticasJugador(jugador);
					setEstadisticasGenerales(result,jugador);
					return result;
				} else {
					ModelAndView result = new ModelAndView("error");
					result.addObject("message", "You need to be admin for see this stats!");
					return result;
				}
			}
		} else {
			ModelAndView result = new ModelAndView("welcome");
			return result;
		}
		return new ModelAndView("exception");
	}

	@GetMapping(value = "/jugador/estadisticas")
	public ModelAndView mostrarEstadisticasUsuario(){
		ModelAndView result = new ModelAndView("jugador/estadisticasJugador");
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Jugador jugador = jugadorService.findJugadorByUsername(username);
		setEstadisticasJugador(jugador);
		setEstadisticasGenerales(result,jugador);
		return result;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@GetMapping(path = "/jugador/delete/{id}")
	public ModelAndView deleteJugador(@PathVariable("id") int id, ModelMap modelMap) {
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	if(auth != null){
		org.springframework.security.core.userdetails.User currentUser =  (org.springframework.security.core.userdetails.User) auth.getPrincipal();
		Collection<GrantedAuthority> usuario = currentUser.getAuthorities();
		for (GrantedAuthority usuarioR : usuario){
			String credencial = usuarioR.getAuthority();
			if (credencial.equals("admin")) {
				Jugador jugador = jugadorService.findJugadorById(id);
				if(jugador.getUser().getUsername().equals(currentUser.getUsername())){ //para que el admin no pueda eliminarse a sí mismo
					ModelAndView result = new ModelAndView(VIEW_JUGADORES);
					List<User> usuarios = userService.findAllUsers();
					Comparator<User> comparador= Comparator.comparing(User::getJugadorId);
					List<User> listaOrdenada = usuarios.stream().sorted(comparador).collect(Collectors.toList());
					result.addObject("users", listaOrdenada);
					return result;
				} else {
					List<Logros> listaLogros = logrosService.findById(jugador.getId());
					for(Logros logro : listaLogros){
						logrosService.delete(logro);
					}
					jugadorService.deleteJugador(jugador);
					ModelAndView result = new ModelAndView(VIEW_JUGADORES);
					List<User> usuarios = userService.findAllUsers();
					Comparator<User> comparador= Comparator.comparing(User::getJugadorId);
					List<User> listaOrdenada = usuarios.stream().sorted(comparador).collect(Collectors.toList());
					result.addObject("users", listaOrdenada);
					return result;
				}
				
			} else {
				ModelAndView result = new ModelAndView("error");
				result.addObject("message", "You can not delete a player!");
				return result;
			}
		}
		
	}else{
		return new ModelAndView("exception");
	}
	return new ModelAndView("exception");
	}

	public void setEstadisticasJugador(Jugador jugador){
		Collection<Partida> lista = partidaService.findPartidasFinalizadasPorJugador(jugador);
		if(lista.size()>0){
			List<Partida> partidasGanadas = lista.stream().filter(x -> x.getVictoria()==true).collect(Collectors.toList());
			Comparator<Partida> comparador= Comparator.comparing(Partida::getNumMovimientos);
			Comparator<Partida> comparador2= Comparator.comparing(Partida::getDuracionMaxMin);	
			List<Partida> numMovLista = partidasGanadas.stream().sorted(comparador.reversed()).collect(Collectors.toList());
			List<Partida> timeLista = partidasGanadas.stream().sorted(comparador2.reversed()).collect(Collectors.toList());
			Integer sumaPuntos = lista.stream().mapToInt(x -> (int) x.puntos()).sum();
			Integer sumaMovimientos = lista.stream().mapToInt(x -> (int) x.getNumMovimientos()).sum();
			Integer sumaGanadas = (lista.stream().filter(x -> x.getVictoria()==true).collect(Collectors.toList())).size();
			Integer sumaPerdidas = lista.size() - sumaGanadas;
			long tiempoJugado = lista.stream().mapToInt(x -> (int) x.getDuracionMaxMin()).sum();

			jugador.setPartidasGanadas(sumaGanadas);
			jugador.setPartidasNoGanadas(sumaPerdidas);
			jugador.setTotalTiempoJugado(jugador.getTotalTiempoJugado().plusSeconds(tiempoJugado));
			jugador.setNumTotalMovimientos(sumaMovimientos);
			jugador.setNumTotalPuntos(sumaPuntos);
			if(partidasGanadas.size()>0){
				jugador.setMaxTiempoPartidaGanada(timeLista.get(0).duracion());
				jugador.setMinTiempoPartidaGanada(timeLista.get(timeLista.size()-1).duracion());
				jugador.setNumMaxMovimientosPartidaGanada(numMovLista.get(0).getNumMovimientos());
				jugador.setNumMinMovimientosPartidaGanada(numMovLista.get(numMovLista.size()-1).getNumMovimientos());
			}
		}
	}

	public void setEstadisticasGenerales(ModelAndView result,Jugador jugador){
		List<Partida> listPartidas = partidaService.findAllPartidas();
		Integer ganadas = (int) listPartidas.stream().filter(x -> x.getVictoria()==true).count();
		Integer puntos = (int) listPartidas.stream().mapToLong(x -> x.puntos()).sum();
		Integer movimientos = (int) listPartidas.stream().mapToLong(x -> x.getNumMovimientos()).sum();
		
		if(listPartidas.size()==0){
			result.addObject("partidasTotalesJugadas", 0);
			result.addObject("partidasGanadasTotales", 0);
			result.addObject("partidasPerdidasTotales", 0);
			result.addObject("puntosPromedio", 0);
			result.addObject("movimientosPromedio", 0);
			result.addObject(jugador);
		}else {
			Integer puntosPromedio = puntos/listPartidas.size();
			Integer movPromedio = movimientos/listPartidas.size();
			result.addObject("partidasTotalesJugadas", listPartidas.size());
			result.addObject("partidasGanadasTotales", ganadas);
			result.addObject("partidasPerdidasTotales", listPartidas.size()-ganadas);
			result.addObject("puntosPromedio", puntosPromedio);
			result.addObject("movimientosPromedio",movPromedio);
			result.addObject(jugador);
		}
	}

}
