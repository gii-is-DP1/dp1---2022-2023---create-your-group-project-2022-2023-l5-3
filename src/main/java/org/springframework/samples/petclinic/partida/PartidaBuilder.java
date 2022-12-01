package org.springframework.samples.petclinic.partida;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.carta.Carta;
import org.springframework.samples.petclinic.carta.CartaRepository;
import org.springframework.samples.petclinic.carta.Palo;
import org.springframework.samples.petclinic.cartasPartida.CartasPartida;
import org.springframework.samples.petclinic.cartasPartida.CartasPartidaRepository;
import org.springframework.samples.petclinic.mazo.Mazo;
import org.springframework.samples.petclinic.mazo.MazoRepository;
import org.springframework.samples.petclinic.mazoFinal.MazoFinal;
import org.springframework.samples.petclinic.mazoFinal.MazoFinalRepository;
import org.springframework.samples.petclinic.mazoInicial.MazoInicial;
import org.springframework.samples.petclinic.mazoInicial.MazoInicialRepository;
import org.springframework.stereotype.Component;

@Component
public class PartidaBuilder {

	/*
	 * CartasPartida cartasPartida = new CartasPartida();
	 * cartasPartida.setPartida(p);
	 * for (Carta inicializar_cartas : inicializar_cartas()) {
	 * cartasPartida.setCarta(inicializar_cartas);
	 * }
	 */

	public List<Carta> inicializarCartas() {
		List<Carta> res = new ArrayList<>();

		for (int palo = 1; palo <= 4; palo++) {
			for (int valor = 1; valor <= 13; valor++) {
				Carta carta = new Carta();
				carta.setValor(valor);
				if (palo == 1) {

					carta.setPalo(Palo.CORAZONES);
				}
				if (palo == 2) {

					carta.setPalo(Palo.DIAMANTES);
				}
				if (palo == 3) {

					carta.setPalo(Palo.PICAS);
				}
				if (palo == 4) {

					carta.setPalo(Palo.TREBOLES);
				}
				res.add(carta);

			}
		}
		return res;
	}

	public List<CartasPartida> inicializaCartasPartidas(Partida p) {
		List<CartasPartida> res = new ArrayList<>();
		List<Carta> cartas = inicializarCartas();
		for (Carta c : cartas) {
			cartaRepository.save(c);
			CartasPartida cp = new CartasPartida();
			cp.setCarta(c);
			cp.setPartida(p);
			cp.setPosCartaMazo(0);
			//cartasPartidaRepository.save(cp);
			res.add(cp);

		}
		return res;
	}

	// 24 cartas a mazo inicial
	// 28 cartas a mazo intermedio
	// mazo intermedio tiene 7 mazos con 7,6,5,4,3,2,1 cartas
	public List<Mazo> inicializaMazoIntermedio() {
		List<Mazo> res = new ArrayList<>();
		for (int i = 1; i <= 7; i++) {
			Mazo m = new Mazo();
			m.setPosicion(i);
			m.setCantidad(0);
			//m.setCartasPartida(cp);
			res.add(m);

		}
		return res;
	}

	public List<MazoFinal> inicializaMazoFinal() {
		List<MazoFinal> res = new ArrayList<>();

		for (int i = 0; i <= 3; i++) {
			MazoFinal mf = new MazoFinal();
			mf.setPosicion(i);
			mf.setCantidad(0);
			//mf.setCartasPartida(cp);
			mazoFinalRepository.save(mf);
			res.add(mf);
		}
		return res;
	}

	@Autowired
	private MazoRepository mazoRepository;


	@Autowired
	private MazoFinalRepository mazoFinalRepository;

	@Autowired
	private MazoInicialRepository mazoInicialRepository;


	@Autowired
	private CartaRepository cartaRepository;

	@Autowired
	private CartasPartidaRepository cartasPartidaRepository;

	// Añade cartas a los mazos intermedios, cada una a una posición
	public void crearMazosIntermedios(Partida p) {

		// Lista de cartasPartida que son 52
		List<CartasPartida> cartasP = inicializaCartasPartidas(p);
		// Lista de mazos que son 7
		List<Mazo> mazos = inicializaMazoIntermedio();
		// Lista mazos finales inicializados
		inicializaMazoFinal();

		MazoInicial mI = new MazoInicial();

		List<CartasPartida> auxCP = new ArrayList<>();
		// Obtengo uno de los mazos de la lista
		for (int j = 0; j < 7; j++) {
			Mazo mact = mazos.get(j);

			/*
			 * Recorro las posiciones del mazo para ir asignando
			 * a cada una de las posibles posiciones una carta
			 */
			Set<CartasPartida> setAux = new HashSet<>();
			for (int k = 0; k < mact.getPosicion() + 1; k++) {

				// Obterno un indice aleatorio entre 0 y 51
				int random = (int) Math.random() * (cartasP.size() - 1);

				// Obtengo la cartaPartida con el indice aleatorio anterior
				CartasPartida cp = cartasP.get(random);

				// Asigno la posición actual al mazo
				cp.setPosCartaMazo(k);
				// Indico la cantidad de cartas que hay ahora en el mazo
				mact.setCantidad(k + 1);

				mazoRepository.save(mact);
				// Asigno el mazo a la CartaPartida
				cp.setMazo(mact);
				cp.setPartida(p);
				cartasPartidaRepository.save(cp);
				auxCP.add(cp);
				setAux.add(cp);
				
				// cartasPartidaRepository.save(cp);
				// Borro la carta de la lista para que no se repita
				cartasP.remove(random);
				
			}
			//mact.setCartasPartida(setAux);
			
		}

		Set<CartasPartida> auxI = new HashSet<>();
		cartasP.forEach(c->auxI.add(c));
		mI.setCartasPartida(auxI);
		mazos.forEach(m->mazoRepository.save(m));
		auxCP.forEach(cp->cartasPartidaRepository.save(cp));
		mazoInicialRepository.save(mI);
		
	}

}
