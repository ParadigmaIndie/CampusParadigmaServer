package com.germany.paradigmaindie.undefinedlearn.controllers;


import com.germany.paradigmaindie.undefinedlearn.models.Video;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/videos")
public class VideoController {
    public List<Video> getAllVideos(){
        return null;
    }
}
