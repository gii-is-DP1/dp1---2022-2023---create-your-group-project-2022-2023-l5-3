package org.springframework.samples.petclinic.jugador;


import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
	public Jugador findJugadorByUsername(String username) throws DataAccessException {
		return jugadorRepository.findByUsername(username);
	}
    
    @Transactional
	public void saveJugador(@Valid Jugador jugador) throws DataAccessException {
		//creating owner
		jugadorRepository.save(jugador);		
		//creating user
		userService.saveUser(jugador.getUser());
		//creating authorities
		authoritiesService.saveAuthorities(jugador.getUser().getUsername(), "jugador");
	}	


}
