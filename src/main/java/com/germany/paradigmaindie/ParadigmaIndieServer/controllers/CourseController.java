package com.germany.paradigmaindie.ParadigmaIndieServer.controllers;


import com.germany.paradigmaindie.ParadigmaIndieServer.models.Course;
import com.germany.paradigmaindie.ParadigmaIndieServer.models.User;
import com.germany.paradigmaindie.ParadigmaIndieServer.services.CourseService;
import com.sun.istack.NotNull;
import javassist.NotFoundException;
import javassist.bytecode.DuplicateMemberException;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.*;


import java.net.http.HttpResponse;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {

    private CourseService courseService;

    @Autowired

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    @CrossOrigin
    public List<Course> getAllCourses(){
        return courseService.allCourses();
    }

    @PostMapping
    public Course createCourse(@RequestBody @NotNull Course course) throws DuplicateMemberException {
        return courseService.createCourse(course);
    }

    @PutMapping(path = "{courseid}")
    public Course updatedCourse(@PathVariable("courseid") @NotNull Long id, @NotNull @RequestBody Course course) throws NotFoundException {
        return courseService.updatedCourse(course, id);

    }

    @CrossOrigin
    @DeleteMapping(path= "{courseid}")
    public Set<Course> deleteCourse(@PathVariable("courseid") Long id){
        Set<Course> courses = courseService.deleteCourse(id);
        return courses;
    }
    @CrossOrigin
    @GetMapping(path= "/tosee/{email}")
    public Set<Course> getCoursesToSeebyUser(@PathVariable("email") @NotNull String email){
        return courseService.getAllCoursesToSeeByUser(email);
    }

    @CrossOrigin
    @GetMapping(path= "/made/{email}")
    public Set<Course> getAllCoursesMadeByUser(@PathVariable("email") @NotNull String email){
        return courseService.getAllCoursesMadeByUser(email);
    }


}
