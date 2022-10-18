package org.springframework.samples.petclinic.carta;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cartas")
@Getter
@Setter
public class Carta extends BaseEntity{
    
    @NotNull
    @Range(min = 1, max = 13)
    @Column(name = "valor")
    private Integer valor;

    @NotEmpty
    @Column(name = "palo")
    private Palo palo;
}
