package org.springframework.samples.petclinic.logros;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.jugador.Jugador;


import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

@Table(name = "logros")
public class Logros extends BaseEntity{
	
	@NotEmpty
	@Column(name="name")
	private String name;
	
	@NotEmpty
	@Column(name="description")
	private String description;
	
	@NotEmpty
	@Column(name="condition_unlocked")
	private String condition_unlocked;
	
	@NotEmpty
	@Column(name="is_unlocked")
	private Boolean is_unlocked;
	
	@NotEmpty
	@Column(name="image")
	private String image;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "jugador_id", referencedColumnName = "id")
	Jugador jugador;
}