package org.springframework.samples.petclinic.user;


/*import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;



import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;*/
import static org.mockito.BDDMockito.given;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.jugador.JugadorService;
import org.springframework.samples.petclinic.logros.LogrosService;
import org.springframework.samples.petclinic.user.Authorities;
import org.springframework.samples.petclinic.user.User;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.security.test.context.support.WithMockUser;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(controllers = UserController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)

public class UserControllerTest {
    
	private User userTest;
	private Jugador jugadorTest;
	private Authorities rolTest;
	private static final int PARTICIPANT_TEST_ID1 = 10;
	private static final int ROL_TEST_ID = 9;


    @Autowired
	private MockMvc mockMvc;
    
    @MockBean
	private AuthoritiesService authoritiesService;

    @MockBean
    private UserService userService;

    @MockBean
	private JugadorService jugadorService;

	@MockBean
	private LogrosService logrosService;

    @BeforeEach
    void setup(){
        Jugador player = new Jugador();
		User user = new User();
		Authorities rol = new Authorities();
		rol.setAuthority("jugador");
		user.setEnabled(true);
		user.setUsername("NuevoJugadorTest");
		user.setPassword("123");
		player.setFirstName("User");
		player.setLastName("Jugador");
		user.setJugador(player);
		given(this.userService.findByUser("NuevoJugadorTest")).willReturn(user);

		Jugador playerADMIN = new Jugador();
		User userADMIN = new User();
		Authorities rolADMIN = new Authorities();
		rolADMIN.setAuthority("admin");
		userADMIN.setEnabled(true);
		userADMIN.setUsername("NuevoAdminTest");
		userADMIN.setPassword("123");
		playerADMIN.setFirstName("User");
		playerADMIN.setLastName("AdminPrueba");
		userADMIN.setJugador(playerADMIN);
		given(this.userService.findByUser("NuevoAdminTest")).willReturn(userADMIN);
    }


    @WithMockUser(value = "spring", username = "NuevoJugadorTest", authorities = "admin")
	@Test
	public void testShowListaDeUsuariosPositive() throws Exception {
		mockMvc.perform(get("/users/all"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("users"))
				.andExpect(view().name("users/UsersList"));
	}

     @WithMockUser(value = "spring", username="barba", authorities = "jugador")
	 @Test
	 void testShowListaDeUsuariosNegative() throws Exception {
	 	mockMvc.perform(get("/users/all"))
	 			.andExpect(status().isOk())
	 			.andExpect(model().attributeDoesNotExist("users"))
	 			.andExpect(view().name("welcome"));
	 }

	 @WithMockUser(value = "spring",username = "NuevoAdminTest", authorities = "admin" )
	 @Test
	 @DisplayName("H13+E1 - Delete an user")
	 
	 public void testDeleteAdmin() throws Exception {
			
		userTest=new User();
		jugadorTest = new Jugador();
		rolTest = new Authorities();
		
		jugadorTest.setId(PARTICIPANT_TEST_ID1);
		jugadorTest.setAllStats0();
		jugadorTest.setFirstName("User");
		jugadorTest.setLastName("Jugador");

		userTest.setUsername("Elena");
		userTest.setPassword("elena123");
		userTest.setEnabled(true);
		userTest.setJugador(jugadorTest);

		rolTest.setAuthority("jugador");
		rolTest.setUser(userTest);
		rolTest.setId(ROL_TEST_ID);
		
 		
//users(username,password,enabled) VALUES ('marsannar2','mario',TRUE);
// authorities(id,username,authority) VALUES (8,'marsannar2','jugador');
// jugador (id,first_name,last_name,image,win,lost,time,mov,points,max_movs,min_movs,max_time,min_time,username)VALUES(2,'mario','sanchez','',0,0,'00:00:00',0,0,0,0,'','','marsannar2');


		 mockMvc.perform(get("/jugador/delete/" + userTest.getJugador().getId()))
		 .andExpect(status().isOk())
		 .andExpect(model().attributeExists("users"))
		 .andExpect(view().name("participants/participantsList"));
		 
		 
	 }


}
