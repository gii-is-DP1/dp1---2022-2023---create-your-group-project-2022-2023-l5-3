package org.springframework.samples.petclinic.jugador;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Set;



import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


import org.springframework.samples.petclinic.model.Person;
import org.springframework.samples.petclinic.partida.Partida;
import org.springframework.samples.petclinic.user.User;

import lombok.Getter;
import lombok.Setter;
 
@Entity
@Getter
@Setter
public class Jugador extends Person{


    private Integer partidasGanadas;

    private Integer partidasNoGanadas;

    private LocalTime totalTiempoJugado;
    
    private Integer numTotalMovimientos;

    private Integer numTotalPuntos;

    private Integer numMaxMovimientosPartidaGanada;

    private Integer numMinMovimientosPartidaGanada;

    private LocalTime maxTiempoPartidaGanada;


    private LocalTime minTiempoPartidaGanada;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username")
	private User user;
    
    
    
    @OneToMany
    Set<Partida> partidasJugadas;

}
