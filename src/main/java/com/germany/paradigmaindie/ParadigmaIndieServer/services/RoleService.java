package com.germany.paradigmaindie.ParadigmaIndieServer.services;

import com.germany.paradigmaindie.ParadigmaIndieServer.models.Role;
import com.germany.paradigmaindie.ParadigmaIndieServer.repositories.RoleRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findRoleByName(String roleName) throws NotFoundException {
        return roleRepository.findByName(roleName).orElseThrow(()->new NotFoundException(String.format("Role %s not found", roleName)));
    }
}
