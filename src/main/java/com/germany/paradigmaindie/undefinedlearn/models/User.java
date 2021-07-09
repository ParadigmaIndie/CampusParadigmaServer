package com.germany.paradigmaindie.undefinedlearn.models;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "courses_to_see",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "course_id", referencedColumnName = "id"))
    private Set<Course> waiting;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "my_created_courses",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "course_id", referencedColumnName = "id"))
    private Set<Course> createdCurses;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "saw_videos_by_user",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "videos_id", referencedColumnName = "id"))
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

    public User(Set<Role> roles, String password, String username,
                String email, boolean isAccountNonExpired,
                boolean isAccountNonLocked, boolean isCredentialsNonExpired,
                boolean isEnabled, String youtube, String twitter,
                String facebook, String github) {
        this.roles = roles;
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
