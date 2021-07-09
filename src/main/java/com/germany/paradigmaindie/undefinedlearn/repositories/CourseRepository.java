package com.germany.paradigmaindie.undefinedlearn.repositories;

import com.germany.paradigmaindie.undefinedlearn.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course,Long> {
    Optional<Course> findByName(String name);
}
