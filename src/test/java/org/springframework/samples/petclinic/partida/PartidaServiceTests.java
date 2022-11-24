package org.springframework.samples.petclinic.partida;



import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.jugador.JugadorService;
import org.springframework.samples.petclinic.user.Authorities;
import org.springframework.samples.petclinic.user.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* Integration test of the Service and the Repository layer.
* <p>
* ClinicServiceSpringDataJpaTests subclasses benefit from the following
* services provided
* by the Spring TestContext Framework:
* </p>
* <ul>
* <li><strong>Spring IoC container caching</strong> which spares us unnecessary
* set up
* time between test execution.</li>
* <li><strong>Dependency Injection</strong> of test fixture instances, meaning
* that we
* don't need to perform application context lookups. See the use of
* {@link Autowired @Autowired} on the <code>{@link
* JugadorServiceTests#clinicService clinicService}</code> instance variable,
* which uses
* autowiring <em>by type</em>.
* <li><strong>Transaction management</strong>, meaning each test method is
* executed in
* its own transaction, which is automatically rolled back by default. Thus,
* even if tests
* insert or otherwise change database state, there is no need for a teardown or
* cleanup
* script.
* <li>An {@link org.springframework.context.ApplicationContext
* ApplicationContext} is
* also inherited and can be used for explicit bean lookup if necessary.</li>
* </ul>
*
* @author Ken Krebs
* @author Rod Johnson
* @author Juergen Hoeller
* @author Sam Brannen
* @author Michael Isvy
* @author Dave Syer
*/

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
class PartidaServiceTests {
	
	@Autowired
	protected PartidaService ps;
	
	@MockBean
	protected JugadorService jugadorService;

	@BeforeEach
	void setup() {

		Jugador fekir = new Jugador();
		User user = new User();
		Authorities rol = new Authorities();
		rol.setAuthority("jugador");
		user.setEnabled(true);
		user.setUsername("Test");
		user.setPassword("123");
		fekir.setFirstName("Nabil");
		fekir.setLastName("Fekir");

	}
	
	@Test
	@Transactional
	public void shouldInsertPartida() {
		
		Collection<Partida> partidas = this.ps.findPartidas();
		int found = partidas.size();
		Partida p = new Partida();
		Jugador jugador = jugadorService.findJugadorByUsername("Test");
		p.setJugador(jugador);
		p.setMomentoInicio(LocalDateTime.now());
		p.setNumMovimientos(234);
		p.setVictoria(false);
		
		this.ps.save(p);
		assertThat(p.getId().longValue()).isNotEqualTo(0);
		
		
		partidas = this.ps.findPartidas();
		assertThat(partidas.size()).isEqualTo(found+1);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

	