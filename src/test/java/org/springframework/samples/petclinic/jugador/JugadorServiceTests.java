package org.springframework.samples.petclinic.jugador;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.user.Authorities;
import org.springframework.samples.petclinic.user.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
class JugadorServiceTests {
	
	@Autowired
	protected JugadorService jugadorService;

	@BeforeEach
	void setup() {

		Jugador george = new Jugador();
		User user = new User();
		Authorities rol = new Authorities();
		rol.setAuthority("jugador");
		user.setEnabled(true);
		user.setUsername("Test");
		user.setPassword("123");
		george.setFirstName("George");
		george.setLastName("Davis");

	}

	
	@Test
	@Transactional
	public void shouldInsertOwner() {
		Collection<Jugador> jugadores = this.jugadorService.findJugadoresByLastName("Davis");
		int found = jugadores.size();

		Jugador george = new Jugador();
		User user = new User();
		Authorities rol = new Authorities();
		rol.setAuthority("jugador");
		user.setEnabled(true);
		user.setUsername("Test");
		user.setPassword("123");
		george.setFirstName("George");
		george.setLastName("Davis");
		george.setUser(user);
		rol.setUser(user);
		this.jugadorService.saveJugador(george);
		assertThat(george.getId().longValue()).isNotEqualTo(0);

		jugadores = this.jugadorService.findJugadoresByLastName("Davis");
		assertThat(jugadores.size()).isEqualTo(found + 1);
	}

	@Test
	public void shoulFindJugadorByUsername(){
		Jugador jugador = this.jugadorService.findJugadorByUsername("jorge");
		assertThat(jugador.getUser().getUsername().equals("jorge"));
		Jugador jugador1 = this.jugadorService.findJugadorByUsername("jor");
		assertThat(jugador1).isNull();
	}

	@Test
	public void shoulFindJugadorById(){
		Jugador jugador = this.jugadorService.findJugadorById(1);
		assertThat(jugador.getUser().getUsername().equals("jorge"));
		Jugador jugador1 = this.jugadorService.findJugadorById(154);
		assertThat(jugador1).isNull();
	}

	@Test
	public void shoulFindJugadoresByLastName(){
		Collection <Jugador> jugadores = this.jugadorService.findJugadoresByLastName("sillero");
		assertThat(jugadores.size()).isNotEqualTo(0);
		Collection <Jugador> jugadores1 = this.jugadorService.findJugadoresByLastName("silleto");
		assertThat(jugadores1.size()).isEqualTo(0);
	}

	
	



	/*
	 * @Test
	 * 
	 * @Transactional
	 * void shouldUpdateOwner() {
	 * Owner owner = this.ownerService.findOwnerById(1);
	 * String oldLastName = owner.getLastName();
	 * String newLastName = oldLastName + "X";
	 * 
	 * owner.setLastName(newLastName);
	 * this.ownerService.saveOwner(owner);
	 * 
	 * // retrieving new name from database
	 * owner = this.ownerService.findOwnerById(1);
	 * assertThat(owner.getLastName()).isEqualTo(newLastName);
	 * }
	 * 
	 */
}
