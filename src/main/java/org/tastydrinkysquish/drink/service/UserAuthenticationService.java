package org.tastydrinkysquish.drink.service;

import org.tastydrinkysquish.drink.entity.DrinkUser;
import org.tastydrinkysquish.drink.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserAuthenticationService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserAuthenticationService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        DrinkUser drinkUser = userRepository.findByUserName(username);
        if (drinkUser != null){
			return new User(drinkUser.getUserName(), drinkUser.getUserPassword(), Collections.emptyList());
        }
        else {
            throw new UsernameNotFoundException("user is not found");
        }
    }
}
