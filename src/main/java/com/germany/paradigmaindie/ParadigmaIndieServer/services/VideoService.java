package com.germany.paradigmaindie.ParadigmaIndieServer.services;

import com.germany.paradigmaindie.ParadigmaIndieServer.models.Course;
import com.germany.paradigmaindie.ParadigmaIndieServer.models.Video;
import com.germany.paradigmaindie.ParadigmaIndieServer.repositories.CourseRepository;
import com.germany.paradigmaindie.ParadigmaIndieServer.repositories.VideoRepository;
import javassist.NotFoundException;
import javassist.bytecode.DuplicateMemberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class VideoService {

    private final VideoRepository videoRepository;
    public final CourseRepository courseRepository;

    @Autowired
    public VideoService(VideoRepository videoRepository, CourseRepository courseRepository) {
        this.videoRepository = videoRepository;
        this.courseRepository = courseRepository;
    }



    public List<Video> getAllVideo() {
        return videoRepository.findAll();
    }

    public Video createVideos(Video video) throws DuplicateMemberException {
        if(videoRepository.findByName(video.getName()).isEmpty()) {
            return videoRepository.save(video);
        }
        throw new DuplicateMemberException(String.format("Video %s duplicated", video.getName()));
    }

    public Video updatedVideo(Video video, Long id) throws NotFoundException {

        videoRepository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException(String.format("Video %s not found", video.getName())));

        videoRepository.updateVideo(id, video.getName());

        return videoRepository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException(String.format("Video %s not found", video.getName())));
    }
    //TODO When a video is used by a course is not posible to delete
    // INFO https://fullstackdeveloper.guru/2020/08/17/what-are-the-different-ways-to-delete-a-child-entity-in-jpa-hibernate-through-spring-data/
    public void deleteVideo(long id){
        videoRepository.deleteById(id);
    }

    public Video getVideoByID(long id) throws NotFoundException {

        return videoRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Video %s not found", id)));
    }

    public Set<Video> getAllVideosBycourse(long courseid) throws NotFoundException {
        return Optional.of(courseRepository.getById(courseid))
                .orElseThrow(() -> new NotFoundException(String.format("Video from courses %s not found", courseid))).getVideos();

    }
}
