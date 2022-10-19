package org.springframework.samples.petclinic.partida;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface PartidaRepository  extends CrudRepository<Partida, Integer> {
	Collection<Partida> findAll() throws DataAccessException;
	
}
