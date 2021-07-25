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
    Video creaVideo( String name, String url, String description){
        Video video = new Video();
        video.setName(name);
        video.setUrl(url);
        video.setTags("testTags");
        video.setDescription(description);
        return videoRepository.save(video);
    }

    @Transactional
    Category creacategorias(String name){
        Category category = new Category();
        category.setName(name);
        return categoriesRepository.save(category);
    }

    @Transactional
    Course creaCourses(String name, String description, Set<Video> videos, Category category){
        Course course = new Course();
        course.setName(name);
        course.setDescription(description);
        course.setTags("Git");
        course.setVideos(videos);
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

        Video video = creaVideo("Markdown", "https://www.youtube.com/watch?v=fxJk-AN7_-4","Markdown description");
        Video video1 = creaVideo("Cómo instalar Git en Mac y Linux", "https://www.youtube.com/watch?v=VY7foMQLsFQ&t=485s","Cómo instalar Git en Mac y Linux | Curso de Git y GitHub Desde Cero 2021");
        Video video2 = creaVideo("Cómo instalar Git en Windows", "https://www.youtube.com/watch?v=HZ4IjC3H2xw","Curso de Git y GitHub Desde Cero 2021");
        Video video3 = creaVideo("Cómo configurar Git ", "https://www.youtube.com/watch?v=ZOsAfmnBq60","Cómo configurar Git | Curso de Git y GitHub Desde Cero 2021");

        Category category = creacategorias("GIT");


        Course course1 = creaCourses("Git 2021", "Curso de git description esto es algo de lo que aprendera", Stream.of(video, video1, video2, video3).collect(Collectors.toSet()), category);

        createUser(roleRepository.findByName("ROLE_ADMIN"),course1);

        //Creacion curso 2

        Video videoM = creaVideo("Wordpress | Cap 1", "https://www.youtube.com/watch?v=K42aK_OkRyc&list=PLnrCmlT1pLaA5y4QmtHDjCLav6Dx3Z8v8","Desarrollando un portafolio en Wordpress | Cap 1");
        Video videoM1 = creaVideo("Wordpress | Cap 2", "https://www.youtube.com/watch?v=i98Ai7MGJ50&list=PLnrCmlT1pLaA5y4QmtHDjCLav6Dx3Z8v8&index=2","Desarrollando un portafolio en Wordpress | Cap 2");

        Category category1 = creacategorias("Wordpress");

        Course course2 = creaCourses("Wordpress 2020", "Curso de wordpress", Stream.of(videoM, videoM1).collect(Collectors.toSet()), category1);

        //Creation curso 3

        Video videoM01 = creaVideo("Actualizar pagina de carrito automáticamente", "https://www.youtube.com/watch?v=q10Pv7_nrjE&list=PLnrCmlT1pLaB_XloJLUwWEyUcjQEj7UR3","Desarrollando un portafolio en Wordpress | Cap 1");
        Video videoM11 = creaVideo("Actualizar icono del carrito con Ajax - Woocommerce", "https://www.youtube.com/watch?v=EndHG8rZn1g&list=PLnrCmlT1pLaB_XloJLUwWEyUcjQEj7UR3&index=2","Desarrollando un portafolio en Wordpress | Cap 2");

        Course course3 = creaCourses("WORDPRESS PARA DESARROLLADORES", "Curso de wordpress", Stream.of(videoM01, videoM11).collect(Collectors.toSet()), category1);

        //Creation curso 3

        Video videoP01 = creaVideo("Seguridad - Live de la comunidad Paradigma Indie", "https://www.youtube.com/watch?v=8UkHSV6Ydwg&t=4s","Desarrollando un portafolio en Wordpress | Cap 1");
        Video videoP11 = creaVideo("Influencers en la actualidad - Live de la comunidad Paradigma Indie", "https://www.youtube.com/watch?v=5PuVafHMCFk","Desarrollando un portafolio en Wordpress | Cap 2");

        Category category2 = creacategorias("Live");

        Course course4 = creaCourses("ParadigmaIndie Live", "Live", Stream.of(videoM01, videoM11).collect(Collectors.toSet()), category2);



        alreadySetup = true;
    }


}