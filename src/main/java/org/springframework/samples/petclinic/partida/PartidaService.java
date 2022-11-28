package org.springframework.samples.petclinic.partida;

import java.util.Collection;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class PartidaService {
	private PartidaRepository partidaRepository;

	@Autowired
	public PartidaService(PartidaRepository partidaRepository) {
		this.partidaRepository = partidaRepository;
	}
	
	//NO DETECTA EL NULL
	@Transactional
	public Collection<Partida> findPartidasEnCurso(){
		return partidaRepository.findAllEnCurso(null);
	}
	
	@Transactional
	public Collection<Partida> findPartidasFinalizadas(){
		return partidaRepository.findAllFinalizadas();
	
	}

	@Transactional
	public void save(Partida partida) {
		partidaRepository.save(partida);
	}
	
	@Transactional
	public Partida findPartidaByUsername(String username) {
		return partidaRepository.findByUsername(username);
	}

}
