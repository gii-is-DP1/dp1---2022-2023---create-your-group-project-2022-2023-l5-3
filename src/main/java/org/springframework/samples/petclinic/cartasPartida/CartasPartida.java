package org.springframework.samples.petclinic.cartasPartida;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.carta.Carta;
import org.springframework.samples.petclinic.mazo.Mazo;
import org.springframework.samples.petclinic.mazoFinal.MazoFinal;
import org.springframework.samples.petclinic.mazoInicial.MazoInicial;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.partida.Partida;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cartasPartida",
uniqueConstraints = {@UniqueConstraint(columnNames = {"mazo","carta"}),
@UniqueConstraint(columnNames = {"mazo_final,carta"}),
@UniqueConstraint(columnNames = {"mazo_inicial,carta"})
})
public class CartasPartida extends BaseEntity {
    
    @NotNull
    private Integer posCartaMazo;

    @ManyToOne(optional=false)
    private Partida partida;

    @ManyToOne(optional=false)
    private Mazo mazo;

    @ManyToOne(optional=false)
    private MazoInicial mazoInicial;

    @ManyToOne(optional=false)
    private MazoFinal mazoFinal;

    @OneToOne
    private Carta carta;


    
}
