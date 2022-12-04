package org.springframework.samples.petclinic.logros;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LogrosService {
	
	private LogrosRepository repo;
	
	@Autowired
	public LogrosService( LogrosRepository repo) {
		this.repo = repo;
	}

	//PARA ASGINAR LOS LOGROS, DEBERÍA SER DIRECTAMENTE DESDE LA BASE DE DATOS, QUE DETECTE CIERTOS LÍMITES EN SQL PARA DAR LOS LOGROS

	@Transactional
	public List<Logros> findById(int id) throws DataAccessException{
		return repo.findLogrosByIdJugador(id);
	}
}