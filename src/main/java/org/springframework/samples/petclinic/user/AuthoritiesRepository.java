package org.springframework.samples.petclinic.user;

import java.util.List;

import org.springframework.data.repository.CrudRepository;



public interface AuthoritiesRepository extends  CrudRepository<Authorities, String>{
	public List<Authorities> findAll();
}
