package org.springframework.samples.petclinic.logros;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LogrosService {
	
	private LogrosRepository repo;
	
	@Autowired
	public LogrosService( LogrosRepository repo) {
		this.repo = repo;
	}

	@Transactional
	public List<Logros> findById(int id) throws DataAccessException{
		return repo.findLogrosByIdJugador(id);
	}

	@Transactional
	public void save(Logros logro) {
		repo.save(logro);
	}

	@Transactional
	public void delete(@Valid Logros logro) throws DataAccessException, DataIntegrityViolationException {
		repo.delete(logro);
	}

	
}