package org.springframework.samples.petclinic.user;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepositoryPageable extends PagingAndSortingRepository<User, Long> {

  Page<User> findAll(Pageable pageable);

}
