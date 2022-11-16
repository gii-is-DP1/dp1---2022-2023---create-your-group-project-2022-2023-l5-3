package org.springframework.samples.petclinic.jugador;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JugadorRepository extends CrudRepository<Jugador, Integer> {


    @Query("select j from Jugador j where j.user.username = ?1")
    public Jugador findByUsername(String username);

    @Query("select j from Jugador j where j.lastName =?1")
    public Collection<Jugador> findByLastName(String lastName);
    

    @Query("SELECT j FROM Jugador j WHERE j.id =:id")
	public Jugador findJugadorById(Integer id);


  
    

}
