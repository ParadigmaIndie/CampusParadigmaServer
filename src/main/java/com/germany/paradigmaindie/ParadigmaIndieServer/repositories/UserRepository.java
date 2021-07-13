package com.germany.paradigmaindie.ParadigmaIndieServer.repositories;

import com.germany.paradigmaindie.ParadigmaIndieServer.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String name);

    Optional<User> findByUsername(String name);

    @Modifying
    @Query("UPDATE User C set C.username = :#{#name} where C.id = :#{#id}")
    void updateUser(@Param(value = "id") long id, @Param(value = "name") String name);

    @Modifying
    @Query("UPDATE User a " +
            "SET a.isEnabled = TRUE WHERE a.email = ?1")
    int enableAppUser(String email);

    @Modifying
    @Query("UPDATE User a " +
            "SET a.isAccountNonExpired = TRUE WHERE a.email = ?1")
    int nonExpired(String email);

    @Modifying
    @Query("UPDATE User a " +
            "SET a.isAccountNonLocked = TRUE WHERE a.email = ?1")
    int nonLocked(String email);

    @Modifying
    @Query("UPDATE User a " +
            "SET a.isCredentialsNonExpired = TRUE WHERE a.email = ?1")
    int nonCredentialsExpired(String email);
}
