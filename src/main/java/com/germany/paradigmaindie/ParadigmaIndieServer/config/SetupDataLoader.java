package com.germany.paradigmaindie.ParadigmaIndieServer.config;

import com.germany.paradigmaindie.ParadigmaIndieServer.models.*;
import com.germany.paradigmaindie.ParadigmaIndieServer.repositories.*;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.lang.NonNullApi;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class SetupDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;


    private CategoriesRepository categoriesRepository;

    private CourseRepository courseRepository;

    private PrivilegeRepository privilegeRepository;

    private RoleRepository roleRepository;

    private UserRepository userRepository;

    private VideoRepository videoRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public SetupDataLoader(CategoriesRepository categoriesRepository,
                           CourseRepository courseRepository,
                           PrivilegeRepository privilegeRepository,
                           RoleRepository roleRepository,
                           UserRepository userRepository,
                           VideoRepository videoRepository,
                           PasswordEncoder passwordEncoder) {
        this.categoriesRepository = categoriesRepository;
        this.courseRepository = courseRepository;
        this.privilegeRepository = privilegeRepository;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.videoRepository = videoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    Privilege createPrivilegeIfNotFound(String name) {

        Optional<Privilege> privilege = privilegeRepository.findByName(name);
        if (privilege.isEmpty()) {
            Privilege privilegeUnpacket = new Privilege(name);
            privilegeRepository.save(privilegeUnpacket);
            return privilegeUnpacket;
        }
        return privilege.get();
    }

    @Transactional
    Role createRoleIfNotFound(
            String name, Collection<Privilege> privileges) {

        Optional<Role> role = roleRepository.findByName(name);
        if (role.isEmpty()) {

            Role uRole = new Role(name);
            uRole.setPrivileges(privileges);
            roleRepository.save(uRole);
            return uRole;
        }
        return role.get();
    }

    @Transactional
    User createUser(Optional<Role> roles, Course coursesCreated){
        //USERS Test Creation
        User user = new User();
        user.setUsername("Test");
        user.setPassword(passwordEncoder.encode("123"));
        user.setEmail("t@t.com");
        user.setRoles(Stream.of(roles).map(role -> role.get()).collect(Collectors.toSet()));
        user.setCreatedCourses(Stream.of(coursesCreated).collect(Collectors.toSet()));
        user.setEnabled(true);
        user.setCredentialsNonExpired(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setFacebook("faceUrl");
        user.setTwitter("twitterurl");
        user.setGithub("gitUrl");
        user.setYoutube("youtubeUrl");
        return userRepository.save(user);
    }

    @Transactional
    Video creaVideo(){

        //VIDEO Test
        Video video = new Video();
        video.setName("testVideo");
        video.setUrl("VideUrl");
        video.setTags("testTags");
        video.setDescription("This is the description the video");

        //VIDEO Test
        Video video1 = new Video();
        video1.setName("testVideo");
        video1.setUrl("VideUrl");
        video1.setTags("testTags");
        video1.setDescription("This is the description the video");
        videoRepository.save(video1);

        //VIDEO Test
        Video video2 = new Video();
        video2.setName("testVideo");
        video2.setUrl("VideUrl");
        video2.setTags("testTags");
        video2.setDescription("This is the description the video");
        videoRepository.save(video2);
        return videoRepository.save(video);
    }

    @Transactional
    Category creacategorias(){
        //VIDEO Test
        Category category = new Category();
        category.setName("Docker");
        categoriesRepository.save(category);

        Category category1 = new Category();
        category1.setName("Git");
        categoriesRepository.save(category1);

        Category category2 = new Category();
        category2.setName("Fundamentos");
        return categoriesRepository.save(category2);
    }

    @Transactional
    Course creaCourses(Video video, Category category){
        Course course = new Course();
        course.setName("Course Name");
        course.setDescription("Course Description");
        course.setTags("course tag example");
        course.setVideos(Stream.of(video).collect(Collectors.toSet()));
        course.setCategorias(Stream.of(category).collect(Collectors.toSet()));
        return courseRepository.save(course);
    }

    @Transactional
    public void onApplicationEvent(@NotNull ContextRefreshedEvent event) {

        if (alreadySetup) {
            return;
        }
        Privilege readPrivilege = createPrivilegeIfNotFound("READ_USER_PRIVILEGE");
        Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_USER_PRIVILEGE");

        createRoleIfNotFound("ROLE_ADMIN", Arrays.asList(readPrivilege, writePrivilege));
        createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));

        Video video = creaVideo();
        Category category = creacategorias();
        Course courseCreated = creaCourses(video,category);

        createUser(roleRepository.findByName("ROLE_ADMIN"),courseCreated);

        alreadySetup = true;
    }


}