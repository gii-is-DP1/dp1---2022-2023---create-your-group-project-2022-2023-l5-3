package org.springframework.samples.petclinic.mazo;


import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class MazoService {
    
    private MazoRepository mazoRepository;

    
    public MazoService(MazoRepository mazoRepository){
        this.mazoRepository = mazoRepository;
    }

    @Transactional(readOnly = true)
    public Optional<Mazo> findMazoById(Integer id) throws DataAccessException{
        return mazoRepository.findById(id);
    }


}