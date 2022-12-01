package org.springframework.samples.petclinic.user;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = UserController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class userControllerTest {
    
    @Autowired
	private MockMvc mockMvc;
    
    @MockBean
	private AuthoritiesService authoritiesService;

    @MockBean
    private UserService userService;

    @Autowired
    private UserController userController;
    
    @BeforeEach
    void setup(){
        User userNew = new User();
        Authorities rol = new Authorities();
        rol.setAuthority("admin");
        userNew.setEnabled(true);
        userNew.setUsername("UsuarioNuevo");
        userNew.setPassword("contraseña");
        given(this.userService.findUser("UsuarioNuevo").get()).willReturn(userNew);
    }

    // @WithMockUser(value = "spring")
	// @Test
	// void testShowUsuarios() throws Exception {
	// 	mockMvc.perform(get("/user/all")).andExpect(status().isOk())
	// 			.andExpect(view().name("jugador/showJugador"));
	// }

    @WithMockUser(value = "spring", authorities = "admin")
	@Test
	public void testShowListaDeUsuariosPositive() throws Exception {
		mockMvc.perform(get("/users/all"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("users"))
				.andExpect(view().name("users/UsersList"));
	}

    // @WithMockUser(value = "spring")
	// @Test
	// void testShowListaDeUsuariosNegative() throws Exception {
	// 	mockMvc.perform(get("/partidas/enCurso"))
	// 			.andExpect(status().isOk())
	// 			.andExpect(model().attributeDoesNotExist("partidas"))
	// 			.andExpect(view().name("welcome"));
	// }
}
