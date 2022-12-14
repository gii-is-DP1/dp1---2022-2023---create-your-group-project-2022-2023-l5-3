package org.springframework.samples.petclinic.cartasPartida;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.carta.Carta;

public interface CartasPartidaRepository extends CrudRepository<CartasPartida,Integer> {

    @Query("select cp from CartasPartida cp where cp.partida.id = ?1")
    public List<CartasPartida>findCartasPartidaByPartidaId(Integer partidaId);

    @Query("select cp from CartasPartida cp WHERE cp.mazo.id = ?1")
    public List<CartasPartida> findCartasPartidaByMazoId(Integer mazoId);
    
}
