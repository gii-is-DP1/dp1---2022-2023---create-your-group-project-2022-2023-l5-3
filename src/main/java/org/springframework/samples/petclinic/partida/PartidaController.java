package org.springframework.samples.petclinic.partida;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.cartasPartida.CartasPartida;
import org.springframework.samples.petclinic.cartasPartida.CartasPartidaService;
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
	private CartasPartidaService cartasPartidaService;

	@Autowired
	private JugadorService jugadorService;

	@Autowired
	private PartidaBuilder pb;

	
	private static final String VIEW_CREATE_PARTIDA = "partidas/createOrUpdatePartidaForm";
	private static final String VIEW_LIST = "partidas/partidaListEnCurso";
	private static final String VIEW_LIST2 = "partidas/partidaListFinalizadas";
	private static final String TABLERO = "tableros/tablero";

	
// ==========================================CREAR PARTIDA===================================================
	
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
			p.setNumMovimientos(0); //PREDEFINIDO
			p.setMomentoInicio(LocalDateTime.now());
			p.setVictoria(false);
			this.partidaService.save(p);
			pb.crearMazosIntermedios(p);
			model.put("message", "Partida empezada");

			List<CartasPartida> cp = cartasPartidaService.findCartasPartidaByPartidaId(p.getId());
			List<Integer> mazos = cartasPartidaService.getMazosIdSorted(p.getId());
			model.put("cartasPartida",cp);
			model.put("mazosOrder", mazos);

			
			return TABLERO;
	
		}
	
	}

//===============================LISTAR PARTIDAS ================================
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

	@GetMapping(value = { "/partidas/jugador" })
	public String showPartidaListViewJugador(Map<String, Object> model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (auth != null){
			org.springframework.security.core.userdetails.User currentUser =  (org.springframework.security.core.userdetails.User) auth.getPrincipal();
			Collection<GrantedAuthority> usuario = currentUser.getAuthorities();
			for (GrantedAuthority usuarioR : usuario){
					String credencial = usuarioR.getAuthority();
					Jugador player = jugadorService.findJugadorByUsername(username);
					if(currentUser.getUsername().equals(player.getUser().getUsername()) || credencial.equals("admin")){
						List<Partida> partidas = new ArrayList<>();
						partidas = (List<Partida>) partidaService.findPartidasFinalizadas();
						List<Partida> res = new ArrayList<>(); 
						for(Partida partida : partidas){
							if(partida.getJugador().getUser().getUsername().equals(username)){
								res.add(partida);
							}
						}
						model.put("partidas", res);
						return "partidas/partidaListUser";
					
					} else {
						return "welcome";
					}
			}
			return "welcome";	
		} else {
			return "welcome";
		}
	}


	@GetMapping(value = { "/partidas/jugador/{id}" })
	public String showPartidaListViewJugador(@PathVariable("id") int id,Map<String, Object> model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (auth != null){
			org.springframework.security.core.userdetails.User currentUser =  (org.springframework.security.core.userdetails.User) auth.getPrincipal();
			Collection<GrantedAuthority> usuario = currentUser.getAuthorities();
			for (GrantedAuthority usuarioR : usuario){
					String credencial = usuarioR.getAuthority();
					Jugador player = jugadorService.findJugadorById(id);
					if(currentUser.getUsername().equals(player.getUser().getUsername()) || credencial.equals("admin")){
						List<Partida> partidas = new ArrayList<>();
						partidas = (List<Partida>) partidaService.findPartidasFinalizadas();
						List<Partida> res = new ArrayList<>(); 
						for(Partida partida : partidas){
							if(partida.getJugador().getId().equals(id)){
								res.add(partida);
							}
						}
						model.put("partidas", res);
						return "partidas/partidaListUser";
					
					} else {
						return "welcome";
					}
			}
			return "welcome";	
		} else {
			return "welcome";
		}
	}


	//CREAR MÉTODO QUE FINALICE UNA PARTIDA
	@GetMapping(path = "/partidas/delete/{id}")
	public ModelAndView deletePartida(@PathVariable("id") int id, ModelMap modelMap) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth != null){
			org.springframework.security.core.userdetails.User currentUser =  (org.springframework.security.core.userdetails.User) auth.getPrincipal();
			Collection<GrantedAuthority> usuario = currentUser.getAuthorities();
			for (GrantedAuthority usuarioR : usuario){
				String credencial = usuarioR.getAuthority();
				if (credencial.equals("admin")) {
					Partida partida = partidaService.findById(id);
					Jugador jugador = partida.getJugador();
					long diffInSeconds = ChronoUnit.SECONDS.between(partida.getMomentoInicio(), partida.getMomentoFin());
					if(partida.getVictoria() == true){
						jugador.setPartidasGanadas(jugador.getPartidasGanadas()-1);
						jugador.setNumTotalMovimientos(jugador.getNumTotalMovimientos()- (int) partida.getNumMovimientos());
						jugador.setTotalTiempoJugado(jugador.getTotalTiempoJugado().minusSeconds(diffInSeconds));
					}
					if(partida.getVictoria() == false){
						jugador.setPartidasNoGanadas(jugador.getPartidasNoGanadas()-1);
						jugador.setNumTotalMovimientos(jugador.getNumTotalMovimientos()- (int) partida.getNumMovimientos());
						jugador.setTotalTiempoJugado(jugador.getTotalTiempoJugado().minusSeconds(diffInSeconds));
					}
					/*List<Logros> logrosLista = logrosService.findById(jugador.getId());
					System.out.println(logrosLista);
					for(Logros logro : logrosLista){
						logrosService.delete(logro);
					}*/
					partidaService.deletePartida(partida);
					ModelAndView result = new ModelAndView("partidas/partidaListFinalizadas");
					result.addObject("partidas", (List<Partida>) partidaService.findPartidasFinalizadas());
					return result;
				}
			}
		}
		return new ModelAndView("exception");
	}

	
	@GetMapping(path = "/partidas/finish/{id}")
	public ModelAndView finishPartida(@PathVariable("id") int id, ModelMap modelMap) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth != null){
			org.springframework.security.core.userdetails.User currentUser =  (org.springframework.security.core.userdetails.User) auth.getPrincipal();
			Collection<GrantedAuthority> usuario = currentUser.getAuthorities();
			for (GrantedAuthority usuarioR : usuario){
				String credencial = usuarioR.getAuthority();
				if (credencial.equals("admin")) {  //SI ERES ADMIN PUEDES FINALIZAR CUALQUIER PARTIDA	
					establecerFinPartidaManual(id);
					ModelAndView result = new ModelAndView("partidas/partidaListFinalizadas");
					result.addObject("partidas", (List<Partida>) partidaService.findPartidasFinalizadas());
					return result;
					
				} else { //SI ERES JUGADOR PUEDES FINALIZAR SOLO TU PARTIDA	
					Partida partida = partidaService.findById(id);
					if(partida.getJugador().getUser().getUsername().equals(currentUser.getUsername())){
						establecerFinPartidaManual(id);
						ModelAndView result = new ModelAndView("welcome");
						result.addObject("message", "Partida acabada");
						result.addObject("partidas", (List<Partida>) partidaService.findPartidasFinalizadas());
						return result;
					} else {
						ModelAndView result = new ModelAndView("welcome");
						result.addObject("message", "No puedes finalizar esta partida");
						result.addObject("partidas", (List<Partida>) partidaService.findPartidasFinalizadas());
						return result;
					}
					
				}
			}
			
		} else {
			return new ModelAndView("exception");
		}
		return new ModelAndView("exception");
	}

	@GetMapping(path = "/partidas/finish2/{id}")
	public ModelAndView finishPartida2(@PathVariable("id") int id, ModelMap modelMap) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth != null){
			org.springframework.security.core.userdetails.User currentUser =  (org.springframework.security.core.userdetails.User) auth.getPrincipal();
			Collection<GrantedAuthority> usuario = currentUser.getAuthorities();
			for (GrantedAuthority usuarioR : usuario){
				String credencial = usuarioR.getAuthority();
				if (credencial.equals("admin")) {  //SI ERES ADMIN PUEDES FINALIZAR CUALQUIER PARTIDA	
					establecerFinPartidaManual2(id);
					ModelAndView result = new ModelAndView("partidas/partidaListFinalizadas");
					result.addObject("partidas", (List<Partida>) partidaService.findPartidasFinalizadas());
					return result;
					
				} else { //SI ERES JUGADOR PUEDES FINALIZAR SOLO TU PARTIDA	
					Partida partida = partidaService.findById(id);
					if(partida.getJugador().getUser().getUsername().equals(currentUser.getUsername())){
						establecerFinPartidaManual2(id);
						ModelAndView result = new ModelAndView("welcome");
						result.addObject("message", "Partida acabada");
						result.addObject("partidas", (List<Partida>) partidaService.findPartidasFinalizadas());
						return result;
					} else {
						ModelAndView result = new ModelAndView("welcome");
						result.addObject("message", "No puedes finalizar esta partida");
						result.addObject("partidas", (List<Partida>) partidaService.findPartidasFinalizadas());
						return result;
					}
					
				}
			}
			
		} else {
			return new ModelAndView("exception");
		}
		return new ModelAndView("exception");
	}

	@GetMapping(path = "partidas/prueba")
	public String prueba(Map<String,Object> model){

		cartasPartidaService.moverCartaInterFin(3, 1, 1);
		
		return "welcome";
		
	}

	//PARA ESTADÍSTICAS
	//ESTO FUNCIONA PERO SI ELIMINAMOS LAS PARTIDAS DE LA BASE DE DATOS, NO SE ACTUALIZAN LOS VALORES
	//DEBERÍAMOS PODER USAR ALGÚN TRIGGER QUE HAGA LA ACTUALIZACIÓN SOLA DE DATOS 
	
	public void establecerFinPartidaManual (Integer id){
		Partida partida = partidaService.findById(id);
		partida.setMomentoFin(LocalDateTime.now());
		partida.setVictoria(false);
		partida.setNumMovimientos(100);
		long diffInSeconds = ChronoUnit.SECONDS.between(partida.getMomentoInicio(), partida.getMomentoFin());
		Jugador player = partida.getJugador();//ESTO no tiene que actualizarse aqui, si no en el transcurso de la partida
		player.setNumTotalMovimientos(player.getNumTotalMovimientos()+(int) partida.getNumMovimientos());
		player.setNumTotalPuntos(player.getNumTotalPuntos()+(int) partida.puntos());
		player.setPartidasNoGanadas(player.getPartidasNoGanadas()+1);
		player.setTotalTiempoJugado(player.getTotalTiempoJugado().plusSeconds(diffInSeconds));
		//player.setMinTiempoPartidaGanada(null);
		//player.setMaxTiempoPartidaGanada(null);
	}

	public void establecerFinPartidaManual2(Integer id){
		Partida partida = partidaService.findById(id);
		partida.setMomentoFin(LocalDateTime.now());
		partida.setVictoria(true);
		partida.setNumMovimientos(100);
		long diffInSeconds = ChronoUnit.SECONDS.between(partida.getMomentoInicio(), partida.getMomentoFin());
		Jugador player = partida.getJugador();//ESTO no tiene que actualizarse aqui, si no en el transcurso de la partida
		player.setNumTotalMovimientos(player.getNumTotalMovimientos()+(int) partida.getNumMovimientos());
		player.setNumTotalPuntos(player.getNumTotalPuntos()+(int) partida.puntos());
		player.setPartidasGanadas(player.getPartidasGanadas()+1);
		player.setTotalTiempoJugado(player.getTotalTiempoJugado().plusSeconds(diffInSeconds));
		//player.setMinTiempoPartidaGanada(null);
		//player.setMaxTiempoPartidaGanada(null);
	}
}
	

