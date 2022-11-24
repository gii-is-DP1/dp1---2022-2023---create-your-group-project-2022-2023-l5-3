package org.springframework.samples.petclinic.partida;

import java.util.ArrayList;
import java.util.List;

import org.springframework.samples.petclinic.carta.Carta;
import org.springframework.samples.petclinic.carta.Palo;

public class PartidaBuilder {
    
/* 
    CartasPartida cartasPartida = new CartasPartida();
    cartasPartida.setPartida(p);
    for (Carta inicializar_cartas : inicializar_cartas()) {
        cartasPartida.setCarta(inicializar_cartas);
    }*/

	public List<Carta> inicializar_cartas(){
		List<Carta> res = new ArrayList<>();
		
		for (int palo = 1;palo<=4;palo++){
			for (int valor = 1;valor<=13;valor++){
				Carta carta = new Carta();
				carta.setValor(valor);
				if (palo==1) {
					
					carta.setPalo(Palo.CORAZONES);
				}
				if (palo==2) {
					
					carta.setPalo(Palo.DIAMANTES);
				}
				if (palo==3) {
					
					carta.setPalo(Palo.PICAS);
				}
				if (palo==4) {
					
					carta.setPalo(Palo.TREBOLES);
				}
				res.add(carta);

			}
		}
			return res;
	}



    

}
