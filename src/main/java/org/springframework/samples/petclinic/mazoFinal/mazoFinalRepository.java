package org.springframework.samples.petclinic.mazoFinal;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;

public interface mazoFinalRepository extends CrudRepository<MazoFinal, Integer>{
    Collection<MazoFinal> findAll() throws DataAccessException;
}
