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
	public List<Logros> findAll (){
		return logrosRepository.findAllLogros();
	}

	@Transactional
	public List<Logros> findById(int id) throws DataAccessException{
		return logrosRepository.findLogrosByIdJugador(id);
	}

	@Transactional
	public Logros findByIdlOGRO (int id) throws DataAccessException{
		return logrosRepository.findLogrosByIdLogro(id);
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

	@Transactional
	public List<Logros> findLogrosByName(String logroName){
		return logrosRepository.findLogrosByName(logroName);
	}

	//HAY QUE EDITARLO
	public void setLogrosDeCadaJugador() {
		List<Jugador> player = jugadorRepository.findAll();
		
		for (Jugador jugador : player){
			List<Logros> logros = findById(jugador.getId());
			Integer primerId = logros.get(0).getId();
			
			for (Logros logro: logros){
	
				if (jugador.getPartidasGanadas() >= logro.getNumCondicion()){
					if(logro.getId().equals(primerId)){
						logro.setIs_unlocked(true);
					}
				}
				if (jugador.getNumTotalPuntos() >= logro.getNumCondicion()){
					if(logro.getId().equals(primerId+1)){
						logro.setIs_unlocked(true);
					}
				}
				if (jugador.getNumTotalMovimientos() >= logro.getNumCondicion()){
					if(logro.getId().equals(primerId+2)){
						logro.setIs_unlocked(true);
					}
				} 
			}
		}

	}
	
}