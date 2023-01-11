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

	public List<Logros> setLogrosJugadorCreado (List<Logros> lista, Jugador jugador){
		for (Logros logro : lista) {
			if (lista.get(0).equals(logro)) {
				logro.setName("Máquina de jugar");
				logro.setDescription("Has ganado 5 partidas");
				logro.setNumCondicion(5);
			} else if (lista.get(1).equals(logro)) {
				logro.setName("No se te da nada mal");
				logro.setDescription("Has alcanzado los 100 puntos");
				logro.setNumCondicion(100);

			} else {
				logro.setName("¡Estás on fire!");
				logro.setDescription("Has alcanzado los 200 movimientos");
				logro.setNumCondicion(200);
			}
			logro.setIs_unlocked(false);
			logro.setImage("https://cdn-icons-png.flaticon.com/512/4319/4319081.png");
			logro.setJugador(jugador);	
		}
		return lista;
	}
	
}