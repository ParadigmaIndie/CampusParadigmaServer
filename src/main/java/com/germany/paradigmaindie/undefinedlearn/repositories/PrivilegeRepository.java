package com.germany.paradigmaindie.undefinedlearn.repositories;

import com.germany.paradigmaindie.undefinedlearn.models.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

    Optional<Privilege> findByName(String name);


}
