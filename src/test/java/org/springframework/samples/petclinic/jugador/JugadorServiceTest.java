package org.springframework.samples.petclinic.jugador;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import javax.transaction.Transaction;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.user.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class JugadorServiceTest {
    @Autowired
    protected JugadorService jugadorService;

    /*@Test
    void shouldFindJugadorByUsername(){
        Jugador jugadores = this.jugadorService.findJugadorByUsername("Jugador1");
        assertThat(jugadores
    }*/

    @Test
    @Transactional
    public void shouldInsertJugador(){
        Jugador jugador = new Jugador();
        jugador.setFirstName("JugadorNuevo");
        jugador.setLastName("Nuevo");
            User user= new User();
            user.setUsername("UsernameNuevo");
            user.setPassword("contraseña");
            user.setEnabled(true);
            jugador.setUser(user);

        this.jugadorService.saveJugador(jugador);
        assertThat(jugador.getId().longValue()).isNotEqualTo(0);
    }
}
