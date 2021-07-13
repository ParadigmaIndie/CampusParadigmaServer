package com.germany.paradigmaindie.ParadigmaIndieServer.services;

import com.germany.paradigmaindie.ParadigmaIndieServer.models.ConfirmationToken;
import com.germany.paradigmaindie.ParadigmaIndieServer.models.Role;
import com.germany.paradigmaindie.ParadigmaIndieServer.models.User;
import com.germany.paradigmaindie.ParadigmaIndieServer.repositories.UserRepository;
import javassist.NotFoundException;
import javassist.bytecode.DuplicateMemberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service("userDetailsService")
@Transactional
public class AppUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    private RoleService roleService;

    private PasswordEncoder passwordEncoder;

    private final ConfirmationTokenServices confirmationTokenService;

    @Autowired
    public AppUserDetailsService(UserRepository userRepository, RoleService roleService, PasswordEncoder passwordEncoder, ConfirmationTokenServices confirmationTokenServices) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.confirmationTokenService = confirmationTokenServices;
    }
    //Load by Email
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found", email)));
    }

    public List<User> allCategories()  {
        return userRepository.findAll();
    }

    public User createUser(User user) throws DuplicateMemberException, NotFoundException {
        if(userRepository.findByEmail(user.getEmail()).isEmpty()) {
            Role rolUser = roleService.findRoleByName("ROLE_USER");
            user.setRoles(Stream.of(rolUser).collect(Collectors.toSet()));
            return userRepository.save(user);
        }
        throw new DuplicateMemberException(String.format("UserEmail %s duplicated", user.getUsername()));
    }


    public User updatedUser(User user, Long id) throws NotFoundException {
        userRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("User %s not found", user.getUsername())));
        if(userRepository.findByEmail(user.getEmail()).isEmpty()) {
            Role rolUser = roleService.findRoleByName("ROLE_USER");
            user.setRoles(Stream.of(rolUser).collect(Collectors.toSet()));
            userRepository.updateUser(id, user.getUsername());
            return userRepository.save(user);
        }


        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("User %s not found", user.getUsername())));
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public String signUpUser(User user){
        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new IllegalStateException("Email already taken");
        }else {

            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        }
        String token= UUID.randomUUID().toString();
        ConfirmationToken confirmationToken= new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );
        confirmationTokenService.saveConfirmationToken(
                confirmationToken);
        //TODO SEND MEAIL
        return token;
    }

    public int enableAppUser(String email) {
        userRepository.nonLocked(email);
        userRepository.nonExpired(email);
        userRepository.nonCredentialsExpired(email);
        return userRepository.enableAppUser(email);
    }
}
