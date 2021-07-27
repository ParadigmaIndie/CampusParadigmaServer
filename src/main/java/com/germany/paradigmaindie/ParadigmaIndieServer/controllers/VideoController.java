package com.germany.paradigmaindie.ParadigmaIndieServer.controllers;


import com.germany.paradigmaindie.ParadigmaIndieServer.models.Course;
import com.germany.paradigmaindie.ParadigmaIndieServer.models.Video;
import com.germany.paradigmaindie.ParadigmaIndieServer.services.VideoService;
import com.sun.istack.NotNull;
import javassist.NotFoundException;
import javassist.bytecode.DuplicateMemberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/videos")
public class VideoController {

    private final VideoService videoService;

    @Autowired
    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }
    @GetMapping
    public List<Video> getAllVideos(){
        return videoService.getAllVideo();
    }

    @PostMapping
    public Video createVideo(@RequestBody @NotNull Video video) throws DuplicateMemberException {
            return videoService.createVideos(video);
    }

    @PutMapping(path = "{videoid}")
    public Video updatedVideo(@PathVariable("videoid") Long id, @RequestBody Video video) throws NotFoundException {

            return videoService.updatedVideo(video, id);

    }

    @DeleteMapping(path= "{videoid}")
    public String deleteVideo(@PathVariable("videoid") Long id){
        videoService.deleteVideo(id);
        return "Delete Success";
    }

    @CrossOrigin
    @GetMapping(path= "/singel/{videoid}")
    public Video getVideoById(@PathVariable("videoid") String id) throws NotFoundException {
        return videoService.getVideoByID(Long.parseLong(id));
    }

    @CrossOrigin
    @GetMapping(path= "/{courseid}")
    public Set<Video> getCoursesToSeebyUser(@PathVariable("courseid") @NotNull String courseid) throws NotFoundException {

        return videoService.getAllVideosBycourse(Long.parseLong(courseid));
    }
}
