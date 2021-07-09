package com.germany.paradigmaindie.undefinedlearn.services;

import com.germany.paradigmaindie.undefinedlearn.models.User;
import com.germany.paradigmaindie.undefinedlearn.repositories.RoleRepository;
import com.germany.paradigmaindie.undefinedlearn.repositories.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service("userDetailsService")
@Transactional
public class AppUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public AppUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    //Load by Email
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserDetails userDetails = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found", email)));
        return userDetails;
    }

    public List<User> getAllUsers() throws NotFoundException {
        Optional<List<User>> users=Optional.of(userRepository.findAll());
        if(users.isPresent()){
            return users.get();
        }
        throw new NotFoundException("Not list found services");
    }
}
