package org.springframework.samples.petclinic.partida;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class PartidaService {
	private PartidaRepository partidaRepository;

	@Autowired
	public PartidaService(PartidaRepository partidaRepository) {
		this.partidaRepository = partidaRepository;
	}
	
	
	@Transactional
	public Collection<Partida> findPartidasEnCurso(){
		return partidaRepository.findBymomentoFinIsNull();
	}
	
	@Transactional
	public Collection<Partida> findPartidasFinalizadas(){
		return partidaRepository.findBymomentoFinIsNotNull();
	}

	/*@Transactional
	public List<Partida> findPartidasFinalizadasUsuario(){
		Collection<Partida> lista = partidaRepository.findBymomentoFinIsNotNull();
		List<Partida> res= new ArrayList<>();
		for (Partida partida:lista){
			if(partida.)
		}
	}*/
	

	@Transactional
	public void save(Partida partida) {
		partidaRepository.save(partida);
	}
	
	@Transactional
	public Partida findPartidaByUsername(String username) {
		return partidaRepository.findByUsername(username);
	}


	//ANTÍA
	@Transactional
	public Partida findById(Integer id) {
		return partidaRepository.findPartidayId(id);
	}

	@Transactional
	public void deletePartida(@Valid Partida partida) throws DataAccessException, DataIntegrityViolationException {
		partidaRepository.delete(partida);
	}
}
