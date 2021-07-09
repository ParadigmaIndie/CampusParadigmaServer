package com.germany.paradigmaindie.undefinedlearn.repositories;

import com.germany.paradigmaindie.undefinedlearn.models.Role;
import com.germany.paradigmaindie.undefinedlearn.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String name);
}
