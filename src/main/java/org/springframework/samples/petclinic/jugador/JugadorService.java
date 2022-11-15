package org.springframework.samples.petclinic.jugador;


import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.samples.petclinic.user.AuthoritiesService;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.stereotype.Service;

@Service
public class JugadorService {

    private JugadorRepository jugadorRepository;

    @Autowired
	private UserService userService;
	
	@Autowired
	private AuthoritiesService authoritiesService;

    @Autowired
    public JugadorService(JugadorRepository jugadorRepository){
        this.jugadorRepository = jugadorRepository;
    }

    @Transactional
	public Jugador findJugadorById(int id) throws DataAccessException {
		return jugadorRepository.findById(id);
	}
    
    @Transactional
	public void saveJugador(@Valid Jugador jugador) throws DataAccessException, DataIntegrityViolationException {
		
		jugadorRepository.save(jugador);		
		
		
		userService.saveUser(jugador.getUser());
		authoritiesService.saveAuthorities(jugador.getUser().getUsername(), "jugador");
	}	


}
