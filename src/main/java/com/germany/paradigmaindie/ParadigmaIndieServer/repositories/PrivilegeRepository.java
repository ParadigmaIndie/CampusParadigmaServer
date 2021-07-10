package com.germany.paradigmaindie.ParadigmaIndieServer.repositories;

import com.germany.paradigmaindie.ParadigmaIndieServer.models.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
    Optional<Privilege> findByName(String name);
}
