package com.germany.paradigmaindie.ParadigmaIndieServer.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

class UserTest {
    @Mock
    Set<Role> roles;
    @Mock
    Set<Course> waitingCourses;
    @Mock
    Set<Course> createdCourses;
    @Mock
    Set<Video> sawVideos;
    @InjectMocks
    User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @Disabled(value = "Return null pointer than we need research that")
    void testGetAuthorities() {
        when(user.getAuthorities()).thenAnswer(new Answer<Set<SimpleGrantedAuthority>>() {
            @Override
            public Set<SimpleGrantedAuthority> answer(InvocationOnMock invocation) throws Throwable {
                return Stream.of(new SimpleGrantedAuthority("ADMIN")).collect(Collectors.toSet());
            }
        });
        Set< GrantedAuthority> result = (Set<GrantedAuthority>) user.getAuthorities();
        Assertions.assertEquals(Arrays.asList(new SimpleGrantedAuthority("ADMIN")), result);
    }

    @Test
    void testSetId() {
        user.setId(0L);
    }

    @Test
    void testSetRoles() {
        user.setRoles(new HashSet<Role>(Arrays.asList(new Role("name", Arrays.<Privilege>asList(new Privilege("name"))))));
    }

    @Test
    void testSetWaitingCourses() {
        user.setWaitingCourses(new HashSet<Course>(Arrays.asList(new Course())));
    }

    @Test
    void testSetCreatedCourses() {
        user.setCreatedCourses(new HashSet<Course>(Arrays.asList(new Course())));
    }

    @Test
    void testSetSawVideos() {
        user.setSawVideos(new HashSet<Video>(Arrays.asList(new Video())));
    }

    @Test
    void testSetPassword() {
        user.setPassword("password");
    }

    @Test
    void testSetUsername() {
        user.setUsername("username");
    }

    @Test
    void testSetEmail() {
        user.setEmail("email");
    }

    @Test
    void testSetAccountNonExpired() {
        user.setAccountNonExpired(true);
    }

    @Test
    void testSetAccountNonLocked() {
        user.setAccountNonLocked(true);
    }

    @Test
    void testSetCredentialsNonExpired() {
        user.setCredentialsNonExpired(true);
    }

    @Test
    void testSetEnabled() {
        user.setEnabled(true);
    }

    @Test
    void testSetYoutube() {
        user.setYoutube("youtube");
    }

    @Test
    void testSetTwitter() {
        user.setTwitter("twitter");
    }

    @Test
    void testSetFacebook() {
        user.setFacebook("facebook");
    }

    @Test
    void testSetGithub() {
        user.setGithub("github");
    }

    @Test
    void testEquals() {
        boolean result = user.equals("o");
        Assertions.assertEquals(false, result);
    }

    @Test
    void testCanEqual() {
        boolean result = user.canEqual("");
        Assertions.assertEquals(false, result);
    }

    @Test
    @Disabled
    void testHashCode() {
        int result = user.hashCode();
        Assertions.assertEquals(anyInt(), result);
    }

    @Test
    void testToString() {
        String result = user.toString();
        Assertions.assertEquals(
                "User(id=0, roles=roles," +
                        " waitingCourses=waitingCourses, c" +
                        "reatedCourses=createdCourses, sawVideos=sawVideos," +
                        " password=null, username=null, email=null, isAccountNonExpired=false, " +
                        "isAccountNonLocked=false, isCredentialsNonExpired=false, " +
                        "isEnabled=false, youtube=null, twitter=null, " +
                        "facebook=null, github=null)", result);
    }
}

