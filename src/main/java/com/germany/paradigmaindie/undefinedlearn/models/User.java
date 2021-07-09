package com.germany.paradigmaindie.undefinedlearn.models;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;


    private String password;
    private String username;
    private String email;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;

    public User(Set<Role> roles,
                String password,
                String username,
                boolean isAccountNonExpired,
                String email,
                boolean isAccountNonLocked,
                boolean isCredentialsNonExpired,
                boolean isEnabled) {
        this.roles = roles;
        this.password = password;
        this.username = username;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
        this.email = email;
    }

    public User() {

    }
    public User(Set<Role> roles) {
        this.roles= roles;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        /*Set<SimpleGrantedAuthority> grantedautorities = getRoles().stream()
                .map(roles -> new SimpleGrantedAuthority(roles.getName())
                ).collect(Collectors.toSet());*/
        Set<SimpleGrantedAuthority> grantedautorities=new HashSet<>();
        for (Role r :
                getRoles()) {
            for (Privilege p:
                 r.getPrivileges()) {
                    grantedautorities.add(new SimpleGrantedAuthority(p.getName()));
            }
            grantedautorities.add(new SimpleGrantedAuthority(r.getName()));
        }
        return grantedautorities;
    }


    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
