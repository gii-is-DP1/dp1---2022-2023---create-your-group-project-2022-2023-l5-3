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
import org.springframework.samples.petclinic.mazo.Mazo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartasPartidaService {

    private CartasPartidaRepository cartasPartidaRepository;

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

    public List<CartasPartida> findCartasPartidaByMazoId(Integer mazoId){
        List<CartasPartida> res = cartasPartidaRepository.findCartasPartidaByMazoId(mazoId);
        return res;
    }
}