package com.germany.paradigmaindie.undefinedlearn.repositories;

import com.germany.paradigmaindie.undefinedlearn.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);

}
