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
package org.springframework.samples.petclinic.cartasPartida;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.mazo.MazoService;
import org.springframework.samples.petclinic.partida.Partida;
import org.springframework.samples.petclinic.partida.PartidaBuilder;
import org.springframework.samples.petclinic.partida.PartidaService;
import org.springframework.samples.petclinic.user.Authorities;
import org.springframework.samples.petclinic.user.User;
import org.springframework.security.test.context.support.WithMockUser;
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
 * CartasPartidaServiceTests#clinicService clinicService}</code> instance variable,
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
class CartasPartidaServiceTests {
	
	@Autowired
    protected CartasPartidaService cpService;

    @Autowired
    protected MazoService mazoService;

    @Autowired 
    protected PartidaBuilder pb;

    @Autowired
    PartidaService partidaService;

    @Autowired
    CartasPartidaRepository cpRepository;

    @WithMockUser(value = "spring")
    @BeforeEach
    void setup(){
        
        Partida partida = new Partida();
        partida.setId(1);
        partida.setNumMovimientos(0);
        partida.setMomentoInicio(LocalDateTime.now());
        partida.setVictoria(false);
        partidaService.save(partida);
		pb.crearMazosIntermedios(partida);
    }

    @Test
    @Transactional
    public void testMoverCartaEntreMazosIntermediosSuccess(){
        List<CartasPartida> cartasOrigen = cpService.findCartasPartidaByMazoIdAndPartidaId(3, 1);
        List<CartasPartida> cartasDestino = cpService.findCartasPartidaByMazoIdAndPartidaId(4, 1);
        
        assertThat(cartasOrigen.size()).isEqualTo(3);
        assertThat(cartasDestino.size()).isEqualTo(4);
        
        cpService.moverCartaInterInter(3, 4, 1, 1);
            
        cartasOrigen = cpService.findCartasPartidaByMazoIdAndPartidaId(3, 1);
        cartasDestino = cpService.findCartasPartidaByMazoIdAndPartidaId(4, 1);
        
        assertThat(cartasOrigen.size()).isEqualTo(2);
        assertThat(cartasDestino.size()).isEqualTo(5);
    }

    @Test
    @Transactional
    public void testMoverCartaEntreMazosIntermediosFail(){
        List<CartasPartida> cartasOrigen = cpService.findCartasPartidaByMazoIdAndPartidaId(3, 1);
        List<CartasPartida> cartasDestino = cpService.findCartasPartidaByMazoIdAndPartidaId(4, 1);
        
        assertThat(cartasOrigen.size()).isEqualTo(3);
        assertThat(cartasDestino.size()).isEqualTo(4);
        
        //AL INTENTAR MOVER CARTAS PERO INDICANDO CANTIDAD 0 NO SE MUEVE NADA
        cpService.moverCartaInterInter(3, 4, 0, 1);
        cartasOrigen = cpService.findCartasPartidaByMazoIdAndPartidaId(3, 1);
        cartasDestino = cpService.findCartasPartidaByMazoIdAndPartidaId(4, 1);
        assertThat(cartasOrigen.size()).isEqualTo(3);
        assertThat(cartasDestino.size()).isEqualTo(4);
    }
        


    @Test
    @Transactional
    public void testMoverCartaDeMazoIntermedioAMazoFinalSuccess(){
        List<CartasPartida> cartasOrigen = cpService.findCartasPartidaByMazoIdAndPartidaId(5, 1);
        List<CartasPartida> cartasDestino = cpService.findCartasPartidaByMazoFinalId(2);
        
        assertThat(cartasOrigen.size()).isEqualTo(5);
        assertThat(cartasDestino.size()).isEqualTo(0);

        cpService.moverCartaInterFin(5, 2, 1);

        cartasOrigen = cpService.findCartasPartidaByMazoIdAndPartidaId(5, 1);
        cartasDestino = cpService.findCartasPartidaByMazoFinalId(2);
        assertThat(cartasOrigen.size()).isEqualTo(4);
        assertThat(cartasDestino.size()).isEqualTo(1);

    }

    @Test
    @Transactional
    public void testMoverCartaDeMazoIntermedioAMazoFinalFail(){
        List<CartasPartida> cartasOrigen = cpService.findCartasPartidaByMazoIdAndPartidaId(5, 1);
        List<CartasPartida> cartasDestino = cpService.findCartasPartidaByMazoFinalId(6);
        
        assertThat(cartasOrigen.size()).isEqualTo(5);
        assertThat(cartasDestino.size()).isEqualTo(0);
        try{
            assertThat(cpService.moverCartaInterFin(5, 6, 1)).isNull();
            fail("Incorrecto");
        }catch(NullPointerException npe){
            fail("Correcto");

        }
        //cpService.moverCartaInterFin(5, 6, 1);


    }

    @Test
    @Transactional
    public void testMoverCartaMazoIncialMazoFinal(){
        List<CartasPartida> cartasOrigen = cpService.findCartasPartidaMazoInicialByPartidaId(1);
        CartasPartida cartaMovida = cartasOrigen.get(0);
        assertThat(cartaMovida.getMazoInicial().getId()).isEqualTo(1);
        cpService.moverCartaInicialFinal(1, 1, 1);
        List<CartasPartida> cartasDestino = cpService.findCartasPartidaByMazoFinalIdAndPartidaId(1,1);
        Collections.sort(cartasDestino, new ComparadorCartasPartidaPorPosCartaMazo());
        CartasPartida cartaMovidaNew = cartasDestino.get(cartasDestino.size()-1);
        assertThat(cartaMovidaNew.getMazoFinal().getId()).isEqualTo(1);
        assertThat(cartaMovidaNew.getMazoInicial().getId()).isEqualTo(null);

    }

    @Test
    @Transactional
    public void testMoverCartaMazoInicialMazoIntermedioSuccess(){
            List<CartasPartida> cartasOrigen = cpService.findCartasPartidaMazoInicialByPartidaId(1);
            CartasPartida cartaMovida = cartasOrigen.get(0);
            assertThat(cartasOrigen.size()).isEqualTo(24);
            assertThat(cartaMovida.getMazoInicial().getId()).isEqualTo(1);

            cpService.moverCartaInicialInter(1, 3, 1);

            List<CartasPartida> cartasOrigen2 = cpService.findCartasPartidaMazoInicialByPartidaId(1);
            List<CartasPartida> cartasDestino = cpService.findCartasPartidaByMazoId(3);
            assertThat(cartasOrigen2.size()).isEqualTo(23);
            assertThat(cartasDestino.size()).isEqualTo(4);
    }

    

}
