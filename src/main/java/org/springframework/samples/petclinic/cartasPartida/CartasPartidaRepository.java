package org.springframework.samples.petclinic.cartasPartida;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.carta.Carta;
import org.springframework.samples.petclinic.mazo.Mazo;

public interface CartasPartidaRepository extends CrudRepository<CartasPartida,Integer> {

    @Query("select cp from CartasPartida cp where cp.partida.id = ?1")
    public List<CartasPartida>findCartasPartidaByPartidaId(Integer partidaId);

    @Query("select cp from CartasPartida cp WHERE cp.mazo.id = ?1")
    public List<CartasPartida> findCartasPartidaByMazoId(Integer mazoId);

    @Query("select m from Mazo m WHERE m.id = ?1")
    public List<Mazo> findMazoByMazoId(Integer mazoId);
    
    @Query("select cp.carta from CartasPartida cp where cp.mazo.id = ?1")
    public List<Carta> findCartasByMazoIdList(Integer mazoId);

    @Query("select cp from CartasPartida cp where cp.carta.id = ?1")
    public CartasPartida findCartasPartidaByCartaId(Integer cartaId);
}
