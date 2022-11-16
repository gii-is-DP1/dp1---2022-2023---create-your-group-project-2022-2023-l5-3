package org.springframework.samples.petclinic.user;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.samples.petclinic.jugador.Jugador;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity

@Table(name = "users")
public class User{
	@Id
	@Column(unique = true)

	@Length(min = 3, max = 20)
	@NotEmpty
	protected String username;
	
	@Length(min = 3, max = 20)
	@NotEmpty
	protected String password;
	
	boolean enabled;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Authorities> authorities;


	@OneToOne(cascade = CascadeType.PERSIST, mappedBy = "user")
	private Jugador jugador;
}

