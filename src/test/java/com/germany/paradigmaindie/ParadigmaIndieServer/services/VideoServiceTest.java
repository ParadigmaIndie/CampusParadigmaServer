package com.germany.paradigmaindie.ParadigmaIndieServer.services;

import com.germany.paradigmaindie.ParadigmaIndieServer.models.Video;
import com.germany.paradigmaindie.ParadigmaIndieServer.repositories.VideoRepository;
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

class VideoServiceTest {
    @Mock
    VideoRepository videoRepository;
    @InjectMocks
    VideoService videoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @Disabled
    void testGetAllVideo() {
        List<Video> result = videoService.getAllVideo();
        Assertions.assertEquals(Arrays.<Video>asList(new Video()), result);
    }

    @Test
    @Disabled
    void testCreateVideos() throws DuplicateMemberException {
        when(videoRepository.findByName(anyString())).thenReturn(null);

        Video result = videoService.createVideos(new Video());
        Assertions.assertEquals(new Video(), result);
    }

    @Test
    @Disabled
    void testUpdatedVideo() throws NotFoundException {
        Video result = videoService.updatedVideo(new Video(), Long.valueOf(1));
        Assertions.assertEquals(new Video(), result);
    }

    @Test
    void testDeleteVideo() {
        videoService.deleteVideo(Long.valueOf(1));
    }
}

