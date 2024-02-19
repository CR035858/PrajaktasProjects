package com.Orders.SpringRestfulAPI.Service;

import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.Orders.SpringRestfulAPI.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import com.Orders.SpringRestfulAPI.Model.DomainUserDetails;


@Service
@RequiredArgsConstructor
public class DomainUserDetailsService implements UserDetailsService {
	
	private final UserRepository repository;

/*	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return this.repository.findByEmail(email)
							  .map(DomainUserDetails::new)
							  .orElseThrow(() -> new UsernameNotFoundException("invalid email address passed"));
	}*/
	
	@Override
	public UserDetails loadUserByUsername(String uname) throws UsernameNotFoundException {
		return this.repository.findByName(uname)
							  .map(DomainUserDetails::new)
							  .orElseThrow(() -> new UsernameNotFoundException("invalid User Name passed"));
	}
}

