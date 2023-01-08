package org.springframework.samples.petclinic.partida;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.samples.petclinic.cartasPartida.CartasPartida;
import org.springframework.samples.petclinic.cartasPartida.CartasPartidaRepository;
import org.springframework.samples.petclinic.cartasPartida.CartasPartidaService;
import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class PartidaService {
	
	private PartidaRepository partidaRepository;
	private CartasPartidaService cpserv; 

	@Autowired
	public PartidaService(PartidaRepository partidaRepository, CartasPartidaService cpserv) {
		this.partidaRepository = partidaRepository;
		this.cpserv=cpserv;
	}
	
	@Transactional
	public List<Partida> findAllPartidas(){
		return partidaRepository.findAll();
	}

	@Transactional
	public Collection<Partida> findPartidasEnCurso(){
		return partidaRepository.findBymomentoFinIsNull();
	}

	@Transactional
	public Boolean jugadorTienePartidaEnCurso (Jugador jugador){
		return partidaRepository.findBymomentoFinIsNull().stream().anyMatch(x -> x.getJugador().equals(jugador));
	}
	
	@Transactional
	public Collection<Partida> findPartidasFinalizadas(){
		return partidaRepository.findBymomentoFinIsNotNull();
	}

	@Transactional
	public List<Partida> findPartidasFinalizadasPorJugador(Jugador jugador){
		Collection<Partida> lista = partidaRepository.findBymomentoFinIsNotNull();
		List<Partida> res= new ArrayList<>();
		for (Partida partida:lista){
			if(partida.getJugador().equals(jugador)){
				res.add(partida);
			}
		}
		return res;
	}
	

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

	@Transactional
	public Boolean noExisteMovimientoPosible (Integer idPartida){
		Boolean res = false;
		List<CartasPartida> cartasQueSePuedenMover = cpserv.findCartasPartidaByPartidaId(idPartida).stream().filter(x -> x.getIsShow() == true).collect(Collectors.toList());
		//AQUÍ IRÍAN LAS VALIDACIONES DE LOS MOVIMIENTOS
		if (cartasQueSePuedenMover.size() == 0){
			res = true;
		}
		return res;
	}
}
