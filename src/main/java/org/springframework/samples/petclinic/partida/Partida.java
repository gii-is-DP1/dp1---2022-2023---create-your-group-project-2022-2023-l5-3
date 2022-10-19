package org.springframework.samples.petclinic.partida;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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
	
	@NotNull
	@Column(name = "victoria")
	private Boolean victoria;
	
	
	@Column(name = "num_movimientos")
	private long numMovimientos;
	
	
	public long puntos() {
		if(numMovimientos==0) {
			return 0;
		}else {
			long diffInSeconds = ChronoUnit.SECONDS.between(momentoInicio, momentoFin);
			return diffInSeconds/numMovimientos;
		}
	}
	
	
	public String duracion() {
		
		long diffInSeconds = ChronoUnit.SECONDS.between(momentoInicio, momentoFin);
		long minutos = diffInSeconds/60;
		long segundos = diffInSeconds%60;
		
		return String.valueOf(minutos) + " minutos y " + String.valueOf(segundos) + " segundos";
		
		
		
		
	}
	
	
	
	
	
	
	

}
