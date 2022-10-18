package org.springframework.samples.petclinic.partida;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
@Table(name = "partidas")
public class Partida extends BaseEntity {
	
	@NotNull
	@Column(name = "momento_inicio")
	private LocalDateTime momentoInicio;
	
	@NotNull
	@Column(name = "momento_fin")
	private LocalDateTime momentoFin;
	
	
	private int duracion() {
		int minutos = 0;
		if(momentoInicio.getMinute() > momentoFin.getMinute()) {
			minutos = 60 -  momentoInicio.getMinute() + momentoFin.getMinute(); 
		}
		else {
			minutos = momentoFin.getMinute() - momentoInicio.getMinute();
		}
		
		return minutos;
	}
	

}
