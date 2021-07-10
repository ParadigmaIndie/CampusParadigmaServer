package com.germany.paradigmaindie.ParadigmaIndieServer.repositories;

import com.germany.paradigmaindie.ParadigmaIndieServer.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;



@Transactional
public interface CategoriesRepository extends JpaRepository<Category,Long> {

    Optional<Category> findByName(String name);

    @Modifying
    @Query("UPDATE Category C set C.name = :#{#name} where C.id = :#{#id}")
    void updateCategory(@Param(value = "id") long id, @Param(value = "name") String name);

}
