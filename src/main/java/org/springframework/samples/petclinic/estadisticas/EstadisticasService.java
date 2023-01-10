package org.springframework.samples.petclinic.estadisticas;

import java.time.Duration;
import java.time.LocalTime;
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
			LocalTime totalJugado = LocalTime.of(0, 0, 0);

			jugador.setPartidasGanadas(sumaGanadas);
			jugador.setPartidasNoGanadas(sumaPerdidas);
			jugador.setTotalTiempoJugado(totalJugado.plusSeconds(tiempoJugado));
			jugador.setNumTotalMovimientos(sumaMovimientos);
			jugador.setNumTotalPuntos(sumaPuntos);
			if(partidasGanadas.size()==0){
				jugador.setNumMaxMovimientosPartidaGanada((long) 0);
				jugador.setNumMinMovimientosPartidaGanada((long) 0);
			}
			if(partidasGanadas.size()>0){
				jugador.setMaxTiempoPartidaGanada(timeLista.get(0).duracion());
				jugador.setMinTiempoPartidaGanada(timeLista.get(timeLista.size()-1).duracion());
				jugador.setNumMaxMovimientosPartidaGanada(numMovLista.get(0).getNumMovimientos());
				jugador.setNumMinMovimientosPartidaGanada(numMovLista.get(numMovLista.size()-1).getNumMovimientos());
			}
		} else {
			jugador.setAllStats0();
		}

    }

    public void setEstadisticasGenerales(ModelAndView result,Jugador jugador){
		List<Partida> listPartidas = serv.findAllPartidas().stream().filter(x -> x.getMomentoFin() != null).collect(Collectors.toList());
		Integer ganadas = (int) listPartidas.stream().filter(x -> x.getVictoria()==true).count();
		Integer perdidas = (int) listPartidas.stream().filter(x -> x.getVictoria()==false).count();
		Integer puntos = (int) listPartidas.stream().mapToLong(x -> x.puntos()).sum();
		Integer movimientos = (int) listPartidas.stream().mapToLong(x -> x.getNumMovimientos()).sum();
		long duracionTotal = listPartidas.stream().mapToInt(x -> (int) x.getDuracionMaxMin()).sum();
		Duration duration = Duration.ofSeconds(duracionTotal);
		long horas = duration.toHours();
		long minutos = duration.toMinutes() % 60;
		long segundos = duration.getSeconds() % 60;


		if(listPartidas.size()==0){
			result.addObject("partidasTotalesJugadas", 0);
			result.addObject("partidasGanadasTotales", 0);
			result.addObject("partidasPerdidasTotales", 0);
			result.addObject("puntosPromedio", 0);
			result.addObject("movimientosPromedio", 0);
			result.addObject("horas",0);
			result.addObject("minutos",0);
			result.addObject("segundos",0);
			result.addObject("horasPromedio",0);
			result.addObject("minutosPromedio",0);
			result.addObject("segundosPromedio",0);
			result.addObject(jugador);
		
		} else {
			long duracionPromedioTotal = duracionTotal / listPartidas.size();
			Duration durationPromedio = Duration.ofSeconds(duracionPromedioTotal);
			long horasPromedio = durationPromedio.toHours();
			long minutosPromedio = durationPromedio.toMinutes() % 60;
			long segundosPromedio = durationPromedio.getSeconds() % 60;
			Integer puntosPromedio = puntos/listPartidas.size();
			Integer movPromedio = movimientos/listPartidas.size();
			result.addObject("partidasTotalesJugadas", listPartidas.size());
			result.addObject("partidasGanadasTotales", ganadas);
			result.addObject("partidasPerdidasTotales", perdidas);
			result.addObject("puntosPromedio", puntosPromedio);
			result.addObject("movimientosPromedio",movPromedio);
			result.addObject("horas",horas);
			result.addObject("minutos",minutos);
			result.addObject("segundos",segundos);
			result.addObject("horasPromedio",horasPromedio);
			result.addObject("minutosPromedio",minutosPromedio);
			result.addObject("segundosPromedio",segundosPromedio);
			result.addObject(jugador);
		}
	}


	
}
