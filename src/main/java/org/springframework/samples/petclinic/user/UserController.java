/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.jugador.JugadorService;
import org.springframework.samples.petclinic.logros.Logros;
import org.springframework.samples.petclinic.logros.LogrosService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Juergen Hoeller
 * @author Ken Krebs
 * @author Arjen Poutsma
 * @author Michael Isvy
 */
@Controller
public class UserController {

	private static final String VIEWS_JUGADOR_CREATE_FORM = "users/createJugadoresForm";

	private final JugadorService jugadorService;
	private final LogrosService logrosService;
	private final UserServicePageable pageUser;
	
	@Autowired
	public UserController(JugadorService jugadorService, LogrosService logrosService, UserServicePageable up) {
		this.jugadorService = jugadorService;
		this.logrosService = logrosService;
		this.pageUser=up;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping(value = "/users/new")
	public String initCreationForm(Map<String, Object> model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth != null){
			org.springframework.security.core.userdetails.User currentUser =  (org.springframework.security.core.userdetails.User) auth.getPrincipal();
			Collection<GrantedAuthority> usuario = currentUser.getAuthorities();
			for (GrantedAuthority usuarioR : usuario){
				String credencial = usuarioR.getAuthority();
				if (credencial.equals("admin")) {
					Jugador jugador = new Jugador();
					model.put("jugador", jugador);
					return VIEWS_JUGADOR_CREATE_FORM;
				} else {
					return "welcome";
				}
			}
		} else {
			return "welcome";
		}
		return "exception";
	}
	
	@PostMapping(value = "/users/new")
	public String processCreationForm(@Valid Jugador jugador, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_JUGADOR_CREATE_FORM;
		}
		else {
			jugador.setImage("");		
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
			
					return "jugador/showJugador";
		}
	}
	
	@GetMapping("/users/all")
    public String showUsersList(Map<String, Object> model, @RequestParam(defaultValue="0") int page){
        
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){
			org.springframework.security.core.userdetails.User currentUser =  (org.springframework.security.core.userdetails.User) auth.getPrincipal();
			Collection<GrantedAuthority> usuario = currentUser.getAuthorities();
			for (GrantedAuthority usuarioR : usuario){
				String credencial = usuarioR.getAuthority();
				if (credencial.equals("admin")) {
					//List<Authorities> usuarios = authoritiesservice.findAllUsers();
					Pageable pageable = PageRequest.of(page,4);
					Page<User> users = pageUser.findAllUsers(pageable);
					
					model.put("users", users);
					return "users/UsersList";
				} else {
					return "welcome";
				}
			
			}
		} else {
			return "welcome";
		}

		return "welcome";
	}

	
}
