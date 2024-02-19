package com.Orders.SpringRestfulAPI.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.Orders.SpringRestfulAPI.Model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	//Optional<User> findByEmail(String name);
	Optional<User> findByName(String name);

}

