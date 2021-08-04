package com.germany.paradigmaindie.ParadigmaIndieServer.services;

import com.germany.paradigmaindie.ParadigmaIndieServer.models.Course;
import com.germany.paradigmaindie.ParadigmaIndieServer.models.User;
import com.germany.paradigmaindie.ParadigmaIndieServer.repositories.CourseRepository;
import com.germany.paradigmaindie.ParadigmaIndieServer.repositories.UserRepository;
import javassist.NotFoundException;
import javassist.bytecode.DuplicateMemberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final AppUserDetailsService appUserDetailsService;
    private final UserRepository userRepository;
    @Autowired
    public CourseService(CourseRepository courseRepository,
                         AppUserDetailsService appUserDetailsService,
                         UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.appUserDetailsService = appUserDetailsService;
        this.userRepository = userRepository;
    }


    public List<Course> allCourses()  {
        return courseRepository.findAll();
    }

    public Course createCourse(Course course) throws DuplicateMemberException {
        if(courseRepository.findByName(course.getName()).isEmpty()) {
            return courseRepository.save(course);
        }
        throw new DuplicateMemberException(String.format("Course %s duplicated", course.getName()));
    }

    public Course updatedCourse(Course course, Long id) throws NotFoundException {
        courseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Course %s not found", course.getName())));
        courseRepository.updateCourse(id, course.getName());
        return courseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Course %s not found", course.getName())));
    }

    public Set<Course> deleteCourse(Long id){
        Optional<User> user = userRepository.findByCreatedCourses(courseRepository.getById(id));
        Set<Course> coursesByUser = user.get().getCreatedCourses();
        coursesByUser.remove(courseRepository.getById(id));
        User userUpdated = userRepository.save(user.get());
        courseRepository.deleteById(id);
        return userUpdated.getCreatedCourses();
    }

    public Set<Course> getAllCoursesToSeeByUser(String email){
        User user = (User) appUserDetailsService.loadUserByUsername(email);
        return user.getWaitingCourses();
    }

    public Set<Course> getAllCoursesMadeByUser(String email){
        User user = (User) appUserDetailsService.loadUserByUsername(email);
        return user.getCreatedCourses();
    }
}
