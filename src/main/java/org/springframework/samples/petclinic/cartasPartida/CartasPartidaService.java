package org.springframework.samples.petclinic.cartasPartida;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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


    public List<CartasPartida> findCartasMazoIntermedio(Integer partidaId, Integer mazoId) {
        List<CartasPartida> cartasPartida = cartasPartidaRepository.findCartasPartidaByPartidaId(partidaId);
        Set<Integer> mazosId = new HashSet<>();
        cartasPartida.forEach(cp -> mazosId.add(cp.getMazo().getId()));
        System.out.println("Hola"+cartasPartida);
        List<CartasPartida> res = new ArrayList<>();
        if (mazosId.contains(mazoId)) {
            res = cartasPartidaRepository.findCartasPartidaByMazoId(mazoId);
        }

        return res;

    }
}