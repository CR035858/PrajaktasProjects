package com.Orders.SpringRestfulAPI.Service;

import java.util.Set;
import org.springframework.stereotype.Service;
import com.Orders.SpringRestfulAPI.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import com.Orders.SpringRestfulAPI.Model.User;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository userRepository;
	
	public Set<User> listUsers(){
		return Set.copyOf(this.userRepository.findAll());
	}
	
	public User getUserById(long id) {
		return this.userRepository
					.findById(id)
					.orElseThrow(() -> new IllegalArgumentException("invalid user id passed"));
	}/**/
	
	public User getUserByName(String name) {
		return this.userRepository
					.findByName(name)
					.orElseThrow(() -> new IllegalArgumentException("invalid user Name passed"));
	}
	
	public User saveUser(User user) {
		return this.userRepository.save(user);
	}
}

