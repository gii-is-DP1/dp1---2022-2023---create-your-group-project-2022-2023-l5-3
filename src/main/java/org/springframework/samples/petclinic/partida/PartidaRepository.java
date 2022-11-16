package org.springframework.samples.petclinic.partida;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidaRepository  extends CrudRepository<Partida, Integer> {
	Collection<Partida> findAll() throws DataAccessException;

	@Query("SELECT p FROM Partida p WHERE p.jugador.user.username =?1")
	public Partida findByUsername(String username);
	
}
