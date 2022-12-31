package org.springframework.samples.petclinic.logros;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.jugador.JugadorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LogrosService {
	
	private LogrosRepository logrosRepository;
		private JugadorRepository jugadorRepository;
	
	@Autowired
	public LogrosService(LogrosRepository logrosRepository, JugadorRepository jugadorRepository) {
		this.logrosRepository = logrosRepository;
			this.jugadorRepository=jugadorRepository;
	}

	@Transactional
	public List<Logros> findById(int id) throws DataAccessException{
		return logrosRepository.findLogrosByIdJugador(id);
	}

	@Transactional
	public void save(Logros logro) {
		logrosRepository.save(logro);
	}

	@Transactional
	public void delete(@Valid Logros logro) throws DataAccessException, DataIntegrityViolationException {
		logrosRepository.delete(logro);
	}

	@Transactional
	public List<Logros> findLogrosJugadorNull(){
		return logrosRepository.findByjugadorIsNull();
	}

	public void getLogrosDeCadaJugador(Integer idJugador) {
		Jugador player = jugadorRepository.findJugadorById(idJugador);
		List<Logros> logros = findById(player.getId());
		Integer primerId = logros.get(0).getId();
		
		for (Logros logro: logros){

			if (player.getPartidasJugadas() >= 5){
				if(logro.getId().equals(primerId)){
					logro.setIs_unlocked(true);
				}
			}
			if (player.getNumTotalPuntos() >= 100){
				if(logro.getId().equals(primerId+1)){
					logro.setIs_unlocked(true);
				}
			}
			if (player.getNumTotalMovimientos() >= 200 ){
				if(logro.getId().equals(primerId+2)){
					logro.setIs_unlocked(true);
				}
			} 
		}
	}
	
}