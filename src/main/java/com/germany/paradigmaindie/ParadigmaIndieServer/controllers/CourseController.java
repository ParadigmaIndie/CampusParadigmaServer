package com.germany.paradigmaindie.ParadigmaIndieServer.controllers;

import com.germany.paradigmaindie.ParadigmaIndieServer.models.Course;
import com.germany.paradigmaindie.ParadigmaIndieServer.services.CourseService;
import com.sun.istack.NotNull;
import javassist.NotFoundException;
import javassist.bytecode.DuplicateMemberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {

    private CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
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

    @DeleteMapping(path= "{courseid}")
    public String deleteCourse(@PathVariable("courseid") Long id){
        courseService.deleteCourse(id);
        return "Delete Success";
    }
}
