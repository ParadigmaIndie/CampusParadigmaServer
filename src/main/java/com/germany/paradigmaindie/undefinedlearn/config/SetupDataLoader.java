package com.germany.paradigmaindie.undefinedlearn.config;

import com.germany.paradigmaindie.undefinedlearn.models.Privilege;
import com.germany.paradigmaindie.undefinedlearn.models.Role;
import com.germany.paradigmaindie.undefinedlearn.models.User;
import com.germany.paradigmaindie.undefinedlearn.repositories.PrivilegeRepository;
import com.germany.paradigmaindie.undefinedlearn.repositories.RoleRepository;
import com.germany.paradigmaindie.undefinedlearn.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class SetupDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    Privilege createPrivilegeIfNotFound(String name) {

        Optional<Privilege> privilege = privilegeRepository.findByName(name);
        if (!privilege.isPresent()) {

            Privilege privilegeUnpacket = new Privilege(name);
            privilegeRepository.save(privilegeUnpacket);
            return privilegeUnpacket;
        }
        return privilege.get();
    }

    @Transactional
    Role createRoleIfNotFound(
            String name, Collection<Privilege> privileges) {

        Optional<Role> role = roleRepository.findByName(name);
        if (!role.isPresent()) {

            Role uRole = new Role(name);
            uRole.setPrivileges(privileges);
            roleRepository.save(uRole);
            return uRole;
        }
        return role.get();
    }

    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup) {
            return;
        }
        Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

        List<Privilege> adminPrivileges = Arrays.asList(readPrivilege, writePrivilege);
        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));


        Optional<Role> adminRole = roleRepository.findByName("ROLE_ADMIN");

        User user = new User();
        user.setUsername("Test");
        user.setPassword(passwordEncoder.encode("123"));
        user.setEmail("t@t.com");
        user.setRoles(Arrays.asList(adminRole).stream().map(role -> role.get()).collect(Collectors.toSet()));
        user.setEnabled(true);
        user.setCredentialsNonExpired(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        userRepository.save(user);

        alreadySetup = true;
    }


}