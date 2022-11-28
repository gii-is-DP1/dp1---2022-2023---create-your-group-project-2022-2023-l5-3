package org.springframework.samples.petclinic.partida;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.jugador.JugadorService;
import org.springframework.samples.petclinic.user.Authorities;
import org.springframework.samples.petclinic.user.User;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasProperty;


@WebMvcTest(controllers = PartidaController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)

public class PartidaControllerTests {
	
	@MockBean
	private JugadorService jugadorService;
	
	@MockBean
	private UserService userService;
	
	@MockBean
	private PartidaService partidaService;
	
	@Autowired
	private MockMvc mockMvc;
	
	
	@BeforeEach
	void setup() {

		Jugador fekir = new Jugador();
		User user = new User();
		Authorities rol = new Authorities();
		rol.setAuthority("jugador");
		user.setEnabled(true);
		user.setUsername("test");
		user.setPassword("123");
		fekir.setFirstName("Nabil");
		fekir.setLastName("Fekir");
		given(this.jugadorService.findJugadorByUsername("test")).willReturn(fekir);

	}
	
	

	
	@Test
	public void testPositive() {
		Partida p = new Partida();
		p.setJugador(jugadorService.findJugadorByUsername("test"));
		p.setMomentoInicio(LocalDateTime.now());
		p.setNumMovimientos(0);
		p.setVictoria(false);
		given(this.partidaService.findPartidaByUsername("test")).willReturn(p);
		
		
	}
	@Test
	public void testNegative(){
		Partida p1 = new Partida();
		p1.setJugador(jugadorService.findJugadorByUsername("test"));
		p1.setMomentoInicio(LocalDateTime.now());
		p1.setVictoria(false);
		p1.setNumMovimientos(-2);
		given(this.partidaService.findPartidaByUsername("test")).willReturn(null);
		
		Partida p2 = new Partida();
		p2.setMomentoInicio(LocalDateTime.now());
		p2.setVictoria(false);
		p2.setNumMovimientos(0);
		given(this.partidaService.findPartidaByUsername("test")).willReturn(null);
		
		
	}
	@WithMockUser(value = "spring")
	@Test
	void testFindPartidasPositive() throws Exception {
		mockMvc.perform(get("/partidas")).andExpect(status().isOk()).andExpect(model().attributeExists("partidas"))
				.andExpect(view().name("partidas/partidaList"));
	}
	
	@Test
	void testFindPartidasNegative() throws Exception {
		mockMvc.perform(get("/partidas")).andExpect(status().is4xxClientError());
	}


}
