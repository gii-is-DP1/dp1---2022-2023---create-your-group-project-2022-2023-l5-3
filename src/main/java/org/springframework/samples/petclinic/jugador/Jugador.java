package org.springframework.samples.petclinic.jugador;

import java.time.LocalTime;
import java.util.Set;



import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.samples.petclinic.logros.Logros;
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

    public void setPartidasGanadas() {
		this.partidasGanadas = 0;
	}
    public void setPartidasNoGanadas() {
		this.partidasNoGanadas = 0;
	}
    
    public void setTotalTiempoJugado() {
		this.totalTiempoJugado = LocalTime.of(0, 0, 0);
	}
    
    public void setNumTotalMovimientos() {
		this.numTotalMovimientos = 0;
	}
    public void setNumTotalPuntos() {
		this.numTotalPuntos = 0;
	}

    //private Integer numMaxMovimientosPartidaGanada;

    //private Integer numMinMovimientosPartidaGanada;

    //private LocalTime maxTiempoPartidaGanada;

    //private LocalTime minTiempoPartidaGanada;

    

    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = false)
    @JoinColumn(name = "username", referencedColumnName = "username")
	User user;  
    
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = false,mappedBy = "jugador")
    Set<Partida> partidasJugadas;

    public Integer getPartidasJugadas(){
        return partidasJugadas.size();
    }
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "jugador_id", referencedColumnName = "id")
    Set<Logros> logros;
    

}
