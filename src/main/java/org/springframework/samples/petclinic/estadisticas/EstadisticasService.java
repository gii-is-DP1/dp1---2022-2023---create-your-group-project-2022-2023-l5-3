package org.springframework.samples.petclinic.estadisticas;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.partida.Partida;
import org.springframework.samples.petclinic.partida.PartidaService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public class EstadisticasService {
    
    private PartidaService serv;

	@Autowired
    public EstadisticasService(PartidaService serv){
        this.serv = serv;
    }


    public List<Partida> findAll(){
        return serv.findAllPartidas();
    }

    public void setEstadisticasJugador(Jugador jugador){
		Collection<Partida> lista = serv.findPartidasFinalizadasPorJugador(jugador);
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

    public void setEstadisticasGenerales(ModelAndView result,Jugador jugador){
		List<Partida> listPartidas = serv.findAllPartidas();
		Integer ganadas = (int) listPartidas.stream().filter(x -> x.getVictoria()==true).count();
		Integer puntos = (int) listPartidas.stream().mapToLong(x -> x.puntos()).sum();
		Integer movimientos = (int) listPartidas.stream().mapToLong(x -> x.getNumMovimientos()).sum();
		
		if(listPartidas.size()==0){
			result.addObject("partidasTotalesJugadas", 0);
			result.addObject("partidasGanadasTotales", 0);
			result.addObject("partidasPerdidasTotales", 0);
			result.addObject("puntosPromedio", 0);
			result.addObject("movimientosPromedio", 0);
			result.addObject(jugador);
		}else {
			Integer puntosPromedio = puntos/listPartidas.size();
			Integer movPromedio = movimientos/listPartidas.size();
			result.addObject("partidasTotalesJugadas", listPartidas.size());
			result.addObject("partidasGanadasTotales", ganadas);
			result.addObject("partidasPerdidasTotales", listPartidas.size()-ganadas);
			result.addObject("puntosPromedio", puntosPromedio);
			result.addObject("movimientosPromedio",movPromedio);
			result.addObject(jugador);
		}
	}


	
}
