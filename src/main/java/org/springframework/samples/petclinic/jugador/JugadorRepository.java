package org.springframework.samples.petclinic.jugador;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JugadorRepository extends CrudRepository<Jugador, Integer > {

    @Query("select j from Jugador j where j.user.username = ?1")
    public Jugador findByUsername(String username);

    @Query("select j FROM Jugador j where j.id= ?1")
    public Jugador findById(int id);

  
    

}
