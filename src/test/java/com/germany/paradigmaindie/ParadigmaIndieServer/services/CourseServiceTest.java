package com.germany.paradigmaindie.ParadigmaIndieServer.services;

import com.germany.paradigmaindie.ParadigmaIndieServer.models.Course;
import com.germany.paradigmaindie.ParadigmaIndieServer.repositories.CourseRepository;
import javassist.NotFoundException;
import javassist.bytecode.DuplicateMemberException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class CourseServiceTest {
    @Mock
    CourseRepository courseRepository;
    @InjectMocks
    CourseService courseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @Disabled
    void testAllCourses() {
        List<Course> result = courseService.allCourses();
        Assertions.assertEquals(Arrays.asList(new Course()), result);
    }

    @Test
    @Disabled
    void testCreateCourse() throws DuplicateMemberException {
        when(courseRepository.findByName(anyString())).thenReturn(null);
        Course result = courseService.createCourse(new Course());
        Assertions.assertEquals(new Course(), result);
    }

    @Test
    @Disabled
    void testUpdatedCourse() throws NotFoundException {
        Course result = courseService.updatedCourse(new Course(), Long.valueOf(1));
        Assertions.assertEquals(new Course(), result);
    }

    @Test
    void testDeleteCourse() {
        courseService.deleteCourse(Long.valueOf(1));
    }
}

