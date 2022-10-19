package org.springframework.samples.petclinic.jugador;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JugadorRepository extends CrudRepository<Jugador, Integer > {

    public Jugador findById(int id);

  
    

}
