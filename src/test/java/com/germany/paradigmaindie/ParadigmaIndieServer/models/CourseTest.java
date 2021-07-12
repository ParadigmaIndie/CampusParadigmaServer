package com.germany.paradigmaindie.ParadigmaIndieServer.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;

class CourseTest {
    @Mock
    Set<Video> videos;
    @Mock
    Set<Category> categorias;
    @InjectMocks
    Course course;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSetId() {
        course.setId(0L);
    }

    @Test
    void testSetName() {
        course.setName("name");
    }

    @Test
    void testSetDescription() {
        course.setDescription("description");
    }

    @Test
    void testSetTags() {
        course.setTags("tags");
    }

    @Test
    void testSetVideos() {
        course.setVideos(new HashSet<Video>(Arrays.asList(new Video())));
    }

    @Test
    void testSetCategorias() {
        course.setCategorias(new HashSet<Category>(Arrays.asList(new Category())));
    }
}

