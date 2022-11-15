package org.springframework.samples.petclinic.mazoInicial;

import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.cartasPartida.CartasPartida;
import org.springframework.samples.petclinic.mazo.Mazo;
import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "mazosiniciales")
@Getter
@Setter


public class MazoInicial extends BaseEntity{
    
    @Column(name = "cantidad")
    @NotNull
    private Integer cantidad = 24;
    //la baraja inicial contiene 24 cartas al inicio del juego
    @OneToMany
    private Collection<CartasPartida> cartasPartida;

}
