package com.germany.paradigmaindie.ParadigmaIndieServer.repositories;

import com.germany.paradigmaindie.ParadigmaIndieServer.models.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public interface VideoRepository extends JpaRepository<Video,Long> {

    Optional<Video> findByName(String name);

    @Modifying
    @Query("UPDATE Video V set V.name = :#{#name} where V.id = :#{#id}")
    void updateVideo(@Param(value = "id") long id, @Param(value = "name") String name);
}
