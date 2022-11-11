package org.springframework.samples.petclinic.jugador;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JugadorRepository extends CrudRepository<Jugador, Integer > {

    @Query("select u.username from User u where u.username = ?1")
    public Jugador findByUsername(String username);

  
    

}
