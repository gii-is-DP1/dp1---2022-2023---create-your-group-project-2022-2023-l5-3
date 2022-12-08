package org.springframework.samples.petclinic.partida;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;



public interface PartidaRepository  extends CrudRepository<Partida, Integer>{
	
	//NO FUNCIONA PORQUE NO DETECTA EL NULL
	
	List<Partida> findBymomentoFinIsNotNull();

	List<Partida> findBymomentoFinIsNull();

	@Query("SELECT p FROM Partida p WHERE p.jugador.user.username =?1")
	public Partida findByUsername(String username);
	
	@Query("SELECT p FROM Partida p WHERE p.id =?1")
	public Partida findPartidayId(Integer id);

}
