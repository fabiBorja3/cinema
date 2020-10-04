package co.edu.uniajc.cinema.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniajc.cinema.model.User;
import co.edu.uniajc.cinema.repository.UserRepository;

import java.util.Optional;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import org.springframework.security.core.authority.AuthorityUtils;

@Service
@Transactional
public class AuthService {
	
	@Autowired
	private UserRepository usuario_Repository;
	
	public String login(String username, String password) {
		Optional<User> customer = usuario_Repository.login(username,password);
        if(customer.isPresent()){
            String token = UUID.randomUUID().toString();
            User custom= customer.get();
            custom.setToken(token);
            usuario_Repository.save(custom);
            return token;
        }

        return StringUtils.EMPTY;
    }
	
	public Optional<org.springframework.security.core.userdetails.User> findByToken(String token) {
		Optional<User> customer = usuario_Repository.findByToken(token);
        if(customer.isPresent()){
        	User customer1 = customer.get();
        	org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(customer1.getNickname(), customer1.getPassword(), true, true, true, true,
                    AuthorityUtils.createAuthorityList("USER"));
            return Optional.of(user);
        }
        return  Optional.empty();
    }

}
