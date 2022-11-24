package org.springframework.samples.petclinic.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends  CrudRepository<User, String>{
	
    @Query ("SELECT u FROM User u WHERE u.username =?1")
    public User findByUsername(String username);
}
