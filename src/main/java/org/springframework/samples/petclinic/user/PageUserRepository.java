package org.springframework.samples.petclinic.user;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface PageUserRepository extends PagingAndSortingRepository<Authorities, String>{

    
}
