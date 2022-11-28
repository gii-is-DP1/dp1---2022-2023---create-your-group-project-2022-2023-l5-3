package org.springframework.samples.petclinic.partida;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidaRepository  extends CrudRepository<Partida, Integer>{//NO FUNCIONA PORQUE NO DETECTA EL NULL
	
	@Query("SELECT p FROM Partida p")
	public Collection<Partida> findAllEnCurso(LocalDateTime fecha);

	@Query("SELECT p FROM Partida p")
	public Collection<Partida> findAllFinalizadas();

	@Query("SELECT p FROM Partida p WHERE p.jugador.user.username =?1")
	public Partida findByUsername(String username);
	
}
