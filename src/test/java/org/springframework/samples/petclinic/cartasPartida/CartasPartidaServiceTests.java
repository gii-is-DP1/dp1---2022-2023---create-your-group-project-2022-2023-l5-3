package org.springframework.samples.petclinic.cartasPartida;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.mazo.Mazo;
import org.springframework.samples.petclinic.mazo.MazoService;
import org.springframework.samples.petclinic.partida.Partida;
import org.springframework.samples.petclinic.partida.PartidaBuilder;
import org.springframework.samples.petclinic.partida.PartidaService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class CartasPartidaServiceTests {
    @Autowired
    protected CartasPartidaService cpService;

    @Autowired
    protected MazoService mazoService;

    @Autowired 
    protected PartidaBuilder pb;

    @Autowired
    PartidaService partidaService;

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
    public void testMoverCartaEntreMazosIntermedios(){
        List<CartasPartida> cartasOrigen = cpService.findCartasPartidaByMazoIdAndPartidaId(1, 1);
        CartasPartida cartaMovida = cartasOrigen.get(0);
        assertThat(cartaMovida.getMazo().getId()).isEqualTo(1);
        cpService.moverCartaInterInter(1, 2, 1, 1);
        List<CartasPartida> cartasDestino = cpService.findCartasPartidaByMazoIdAndPartidaId(1, 2);
        Collections.sort(cartasDestino, new ComparadorCartasPartidaPorPosCartaMazo());
        CartasPartida cartaMovidaNew = cartasDestino.get(cartasDestino.size()-1);
        assertThat(cartaMovidaNew.getMazo().getId()).isEqualTo(2);
    }

    @Test
    @Transactional
    public void testMoverCartaDeMazoIntermedioAMazoFinal(){
        List<CartasPartida> cartasOrigen = cpService.findCartasPartidaByMazoIdAndPartidaId(1, 1);
        CartasPartida cartaMovida = cartasOrigen.get(0);
        assertThat(cartaMovida.getMazo().getId()).isEqualTo(1);
        cpService.moverCartaInterFin(1, 1, 1);
        List<CartasPartida> cartasDestino = cpService.findCartasPartidaByMazoFinalIdAndPartidaId(1,1);
        Collections.sort(cartasDestino, new ComparadorCartasPartidaPorPosCartaMazo());
        CartasPartida cartaMovidaNew = cartasDestino.get(cartasDestino.size()-1);
        assertThat(cartaMovidaNew.getMazoFinal().getId()).isEqualTo(1);
        assertThat(cartaMovidaNew.getMazo().getId()).isEqualTo(null);
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
}
