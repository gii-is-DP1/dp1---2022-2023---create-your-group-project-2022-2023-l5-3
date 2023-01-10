package org.springframework.samples.petclinic.estadisticas;

import org.springframework.stereotype.Controller;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.jugador.JugadorService;
import org.springframework.security.core.Authentication;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EstadisticasController {
    

    private final EstadisticasService estadisticasService;
	private final JugadorService jugadorService;
    
	@Autowired
    public EstadisticasController(JugadorService jugadorService, EstadisticasService estadisticasService){
        this.jugadorService= jugadorService;
		this.estadisticasService=estadisticasService;
    }

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
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
					ModelAndView result = new ModelAndView("estadisticas/estadisticasJugador");
					Jugador jugador = jugadorService.findJugadorById(id);
					estadisticasService.setEstadisticasJugador(jugador);
					estadisticasService.setEstadisticasGenerales(result,jugador);
					return result;
				} else {
					ModelAndView result = new ModelAndView("welcome");
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
		ModelAndView result = new ModelAndView("estadisticas/estadisticasJugador");
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Jugador jugador = jugadorService.findJugadorByUsername(username);
		estadisticasService.setEstadisticasJugador(jugador);
		estadisticasService.setEstadisticasGenerales(result,jugador);
		return result;
	}

	@GetMapping("/ranking")
    public String showRanking(Map<String, Object> model){
        
			List<Jugador> jugadores = jugadorService.findAllPlayer();
			
			Comparator<Jugador> comparador= Comparator.comparing(Jugador::getPartidasGanadas);
			Comparator<Jugador> comparador2= Comparator.comparing(Jugador::getNumTotalPuntos);
			Comparator<Jugador> comparador3= Comparator.comparing(Jugador::getNumTotalMovimientos);
			
			List<Jugador> ranking1 = jugadores.stream().sorted(comparador.reversed()).collect(Collectors.toList());
			List<Jugador> ranking2 = jugadores.stream().sorted(comparador2.reversed()).collect(Collectors.toList());
			List<Jugador> ranking3 = jugadores.stream().sorted(comparador3.reversed()).collect(Collectors.toList());

			model.put("jugadoresWIN", ranking1);
			model.put("jugadoresPTN", ranking2);
			model.put("jugadoresMOV", ranking3);
			
			return "ranking/rankingGeneral";
	}

}



