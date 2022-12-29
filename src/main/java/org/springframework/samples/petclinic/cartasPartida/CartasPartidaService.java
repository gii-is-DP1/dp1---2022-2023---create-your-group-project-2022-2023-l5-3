package org.springframework.samples.petclinic.cartasPartida;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.carta.Carta;
import org.springframework.samples.petclinic.mazo.Mazo;
import org.springframework.samples.petclinic.mazo.MazoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartasPartidaService {

    private CartasPartidaRepository cartasPartidaRepository;
    
    @Autowired
    private MazoService mazoService;



    @Autowired
    public CartasPartidaService(CartasPartidaRepository cartasPartidaRepository) {
        this.cartasPartidaRepository = cartasPartidaRepository;
    }

    @Transactional(readOnly = true)
    public Optional<CartasPartida> findCartasPartidaById(Integer id) throws DataAccessException {
        return cartasPartidaRepository.findById(id);
    }


    public List<Integer> getMazosIdSorted(Integer partidaId){
        List<CartasPartida> cp = cartasPartidaRepository.findCartasPartidaByPartidaId(partidaId);
        
        List<Integer> res= new ArrayList<>();
        for (CartasPartida cartaPartida : cp) {
            if (cartaPartida == null) {
                break;
            }else{
                
                if (cartaPartida.getMazo()!= null && !res.contains(cartaPartida.getMazo().getId()) ) {
                    res.add(cartaPartida.getMazo().getId());
                }    
            }
        }

        return res; 
    }


    public List<CartasPartida> findCartasPartidaByPartidaId(int partidaId){
        List<CartasPartida> res = cartasPartidaRepository.findCartasPartidaByPartidaId(partidaId);
        return res;
        
    }

    public CartasPartida findCartasPartidaByCartaId(int cartaId){
        return cartasPartidaRepository.findCartasPartidaByCartaId(cartaId);
    }

    public List<Carta> findCartasByMazoIdList(int mazoId){
        return cartasPartidaRepository.findCartasByMazoIdList(mazoId);
    }

    @Transactional
    public void moverCartaInterInter(int mazoOrigenId, int mazoDestinoId, int cantidadCartas){
        Mazo mazoOrigen = mazoService.findMazoById(mazoOrigenId);
        Mazo mazoDest = mazoService.findMazoById(mazoDestinoId);
        List<Carta> cartasMazoOrigenOld = findCartasByMazoIdList(mazoOrigenId);
        List<Carta> cartasMazoDestOld = findCartasByMazoIdList(mazoDestinoId);

        int startIndex = cartasMazoOrigenOld.size() - cantidadCartas;
        List<Carta> cartasMovidas = cartasMazoDestOld.subList(startIndex, cartasMazoDestOld.size());

        int indexUltCarta = cartasMazoOrigenOld.size();
        int i = 1;
        for (Carta carta : cartasMovidas) {
            CartasPartida cp = findCartasPartidaByCartaId(carta.getId());
            CartasPartida cpNew = new CartasPartida();
            cpNew.setId(cp.getId());
            cpNew.setMazo(mazoDest);
            cpNew.setCarta(cp.getCarta());
            cpNew.setPartida(cp.getPartida());
            cpNew.setPosCartaMazo(indexUltCarta+i);
            i++;
            cartasPartidaRepository.delete(cp);
            cartasPartidaRepository.save(cpNew);
        }


        List<Carta> destinoNew = findCartasByMazoIdList(mazoDestinoId);
        System.out.println(destinoNew.toString());


    }

    @Transactional
	public void saveCartasPartida(CartasPartida cp) throws DataAccessException {
		cartasPartidaRepository.save(cp);
		
	}	
}