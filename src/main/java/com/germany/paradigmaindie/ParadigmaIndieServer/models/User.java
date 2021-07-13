package com.germany.paradigmaindie.ParadigmaIndieServer.models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Setter
@Getter
@Entity
@Table(name = "Appuser")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Course> waitingCourses;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Course> createdCourses;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Video> sawVideos;

    private String password;
    private String username;
    private String email;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;
    //
    private String youtube;
    private String twitter;
    private String facebook;
    private String github;

    public User(Set<Role> roles,
                Set<Course> waitingCourses,
                Set<Course> createdCourses,
                Set<Video> sawVideos,
                String password,
                String username,
                String email,
                boolean isAccountNonExpired,
                boolean isAccountNonLocked,
                boolean isCredentialsNonExpired,
                boolean isEnabled,
                String youtube,
                String twitter,
                String facebook,
                String github) {
        this.roles = roles;
        this.waitingCourses = waitingCourses;
        this.createdCourses = createdCourses;
        this.sawVideos = sawVideos;
        this.password = password;
        this.username = username;
        this.email = email;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
        this.youtube = youtube;
        this.twitter = twitter;
        this.facebook = facebook;
        this.github = github;
    }

    public User() {

    }

    public User(String username, String password, String email, Role role_user) {
        this.username=username;
        this.password=password;
        this.email=email;
        this.roles= Stream.of(role_user).collect(Collectors.toSet());
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
