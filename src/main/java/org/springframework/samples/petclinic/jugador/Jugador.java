package org.springframework.samples.petclinic.jugador;


import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.springframework.samples.petclinic.model.Person;
import org.springframework.samples.petclinic.user.User;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Jugador extends Person{

    private Integer partidasJugadas;

    private Integer partidasGanadas;

    private Integer partidasNoGanadas;

    private Integer totalTiempoJugado;
    
    private Integer numTotalMovimientos;

    private Integer numTotalPuntos;

    private Integer numMaxMovimientosPartidaGanada;

    private Integer numMinMovimientosPartidaGanada;

    private LocalTime maxTiempoPartidaGanada;

    private LocalTime minTiempoPartidaGanada;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username")
	private User user;


}
