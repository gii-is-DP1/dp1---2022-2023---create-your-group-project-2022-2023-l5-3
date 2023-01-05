package org.springframework.samples.petclinic.jugador;


import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.samples.petclinic.partida.Partida;
import org.springframework.samples.petclinic.partida.PartidaService;
import org.springframework.samples.petclinic.user.AuthoritiesService;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.stereotype.Service;

@Service
public class JugadorService {

	@Autowired
    private JugadorRepository jugadorRepository;

	@Autowired
    private PartidaService partidaService;

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
	public Collection<Jugador> findJugadoresByLastName(String lastName) throws DataAccessException{
		return jugadorRepository.findByLastName(lastName);
	}

	@Transactional
	public Jugador findJugadorById(int id) throws DataAccessException {
		return jugadorRepository.findJugadorById(id);
	}
    
	
    @Transactional
	public void saveJugador(@Valid Jugador jugador) throws DataAccessException, DataIntegrityViolationException {
		
		jugadorRepository.save(jugador);
		userService.saveUser(jugador.getUser());
		authoritiesService.saveAuthorities(jugador.getUser().getUsername(), "jugador");
	}
	
	@Transactional
	public void deleteJugador(@Valid Jugador jugador) throws DataAccessException, DataIntegrityViolationException {
		jugadorRepository.delete(jugador);
	}


	@Transactional
	public Collection<Partida> findPartidasByJugadorId(int id ) throws DataAccessException{
		return jugadorRepository.findPartidasByJugador(id);
	}

	public List<Jugador> findAllPlayer() {
		return jugadorRepository.findAll();
	}

	public void setEstadisticasJugador(Jugador jugador){
		Collection<Partida> lista = partidaService.findPartidasFinalizadasPorJugador(jugador);
		if(lista.size()>0){
			List<Partida> partidasGanadas = lista.stream().filter(x -> x.getVictoria()==true).collect(Collectors.toList());
			Comparator<Partida> comparador= Comparator.comparing(Partida::getNumMovimientos);
			Comparator<Partida> comparador2= Comparator.comparing(Partida::getDuracionMaxMin);	
			List<Partida> numMovLista = partidasGanadas.stream().sorted(comparador.reversed()).collect(Collectors.toList());
			List<Partida> timeLista = partidasGanadas.stream().sorted(comparador2.reversed()).collect(Collectors.toList());
			Integer sumaPuntos = lista.stream().mapToInt(x -> (int) x.puntos()).sum();
			Integer sumaMovimientos = lista.stream().mapToInt(x -> (int) x.getNumMovimientos()).sum();
			Integer sumaGanadas = (lista.stream().filter(x -> x.getVictoria()==true).collect(Collectors.toList())).size();
			Integer sumaPerdidas = lista.size() - sumaGanadas;
			long tiempoJugado = lista.stream().mapToInt(x -> (int) x.getDuracionMaxMin()).sum();

			jugador.setPartidasGanadas(sumaGanadas);
			jugador.setPartidasNoGanadas(sumaPerdidas);
			jugador.setTotalTiempoJugado(jugador.getTotalTiempoJugado().plusSeconds(tiempoJugado));
			jugador.setNumTotalMovimientos(sumaMovimientos);
			jugador.setNumTotalPuntos(sumaPuntos);
			if(partidasGanadas.size()>0){
				jugador.setMaxTiempoPartidaGanada(timeLista.get(0).duracion());
				jugador.setMinTiempoPartidaGanada(timeLista.get(timeLista.size()-1).duracion());
				jugador.setNumMaxMovimientosPartidaGanada(numMovLista.get(0).getNumMovimientos());
				jugador.setNumMinMovimientosPartidaGanada(numMovLista.get(numMovLista.size()-1).getNumMovimientos());
			}
		}
	}

}
