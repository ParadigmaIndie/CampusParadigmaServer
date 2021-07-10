package com.germany.paradigmaindie.ParadigmaIndieServer.services;

import com.germany.paradigmaindie.ParadigmaIndieServer.models.Video;
import com.germany.paradigmaindie.ParadigmaIndieServer.repositories.VideoRepository;
import javassist.NotFoundException;
import javassist.bytecode.DuplicateMemberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoService {

    private final VideoRepository videoRepository;

    @Autowired
    public VideoService(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
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
    public void deleteVideo(Long id){
        videoRepository.deleteById(id);
    }
}
