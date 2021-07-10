package com.germany.paradigmaindie.ParadigmaIndieServer.services;

import com.germany.paradigmaindie.ParadigmaIndieServer.models.Course;
import com.germany.paradigmaindie.ParadigmaIndieServer.repositories.CourseRepository;
import javassist.NotFoundException;
import javassist.bytecode.DuplicateMemberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
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

    public void deleteCourse(Long id){
        courseRepository.deleteById(id);
    }
}
