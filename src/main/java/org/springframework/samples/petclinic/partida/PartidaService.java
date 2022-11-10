package org.springframework.samples.petclinic.partida;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class PartidaService {
	private PartidaRepository partidaRepository;

	@Autowired
	public PartidaService(PartidaRepository partidaRepository) {
		this.partidaRepository = partidaRepository;
	}
	
	@Transactional(readOnly = true)
	public Collection<Partida> findPartidas() throws DataAccessException {
		return partidaRepository.findAll();
	}
	
	@Transactional
	public void save(Partida partida) {
		partidaRepository.save(partida);
	}

}
