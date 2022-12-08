package org.springframework.samples.petclinic.mazoFinal;

import java.util.Collection;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.dao.DataAccessException;

public class MazoFinalService {
    private MazoFinalRepository mazofinalRepository;

    
    public MazoFinalService(MazoFinalRepository mazofinalRepository){
        this.mazofinalRepository = mazofinalRepository;
    }

    @Transactional(readOnly = true)
    public Collection<MazoFinal> findAllMazosFinales() throws DataAccessException{
        return mazofinalRepository.findAll();
    }
    
}
