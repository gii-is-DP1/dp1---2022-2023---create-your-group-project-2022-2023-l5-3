package org.springframework.samples.petclinic.cartasPartida;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CartasPartidaService {
    
    private CartasPartidaRepository cartasPartidaRepository;

    @Autowired
    public CartasPartidaService(CartasPartidaRepository cartasPartidaRepository){
        this.cartasPartidaRepository = cartasPartidaRepository;
    }

    @Transactional(readOnly = true)
    public Optional<CartasPartida> findCartasPartidaById(Integer id) throws DataAccessException{
        return cartasPartidaRepository.findById(id);
    }


}