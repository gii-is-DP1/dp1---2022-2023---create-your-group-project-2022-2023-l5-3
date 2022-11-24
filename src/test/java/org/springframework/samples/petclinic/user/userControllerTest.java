package org.springframework.samples.petclinic.user;

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
class userControllerTest {
    
    @Autowired
	private MockMvc mockMvc;
    
    @MockBean
	private UserService userService;

    @Autowired
    private UserController userController;
    
    @BeforeEach
    void setup(){
        User userNew = new User();
        Authorities rol = new Authorities();
        rol.setAuthority("jugador");
        userNew.setEnabled(true);
        userNew.setUsername("UsuarioNuevo");
        userNew.setPassword("contraseña");
        given(this.userService.findUser("UsuarioNuevo")).willReturn(userNew);
    }

    @WithMockUser(value = "spring")
	@Test
	void testShowJugador() throws Exception {
		mockMvc.perform(get("/jugador/perfil")).andExpect(status().isOk())
				.andExpect(view().name("jugador/showJugador"));
	}
}
