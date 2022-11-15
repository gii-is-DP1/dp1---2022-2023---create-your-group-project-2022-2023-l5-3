package org.springframework.samples.petclinic.cartasPartida;



import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Table;
import javax.validation.constraints.NotNull;


import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cartasPartida")
public class CartasPartida extends BaseEntity {
    
    @NotNull
    @Column(name = "posCartaMazo")
    private Integer posCartaMazo;
    
}
