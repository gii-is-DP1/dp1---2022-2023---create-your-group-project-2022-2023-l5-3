package org.springframework.samples.petclinic.jugador;

import java.time.LocalTime;
import java.util.Set;



import javax.persistence.CascadeType;
import javax.persistence.Column;
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

    @Column(name = "win")
    private Integer partidasGanadas;
    @Column(name = "lost")
    private Integer partidasNoGanadas;
    @Column(name = "time")
    private LocalTime totalTiempoJugado;
    @Column(name = "mov")
    private Integer numTotalMovimientos;
    @Column(name = "points")
    private Integer numTotalPuntos;
    @Column(name = "max_movs")
    private Long numMaxMovimientosPartidaGanada;
    @Column(name = "min_movs")
    private Long numMinMovimientosPartidaGanada;
    @Column(name = "max_time")
    private String maxTiempoPartidaGanada;
    @Column(name = "min_time")
    private String minTiempoPartidaGanada;

    public void setAllStats0() {
		this.partidasGanadas = 0;
        this.partidasNoGanadas = 0;
        this.totalTiempoJugado = LocalTime.of(0, 0, 0);
        this.numTotalMovimientos = 0;
        this.numTotalPuntos = 0;
        this.numMaxMovimientosPartidaGanada=(long) 0;
        this.numMinMovimientosPartidaGanada=(long) 0;
        this.maxTiempoPartidaGanada="";
        this.minTiempoPartidaGanada="";
	}
    

    

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
