package org.springframework.samples.petclinic.user.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Paginacion {

    private int numero;
    private boolean actual;

    public Paginacion(int numero, boolean actual){
        super();
        this.numero = numero;
        this.actual = actual;
    }

    
}
