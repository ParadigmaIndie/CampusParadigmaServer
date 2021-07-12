package com.germany.paradigmaindie.ParadigmaIndieServer.models;

import org.junit.jupiter.api.Test;

class VideoTest {
    Video video = new Video();

    @Test
    void testSetId() {
        video.setId(0L);
    }

    @Test
    void testSetName() {
        video.setName("name");
    }

    @Test
    void testSetDescription() {
        video.setDescription("description");
    }

    @Test
    void testSetUrl() {
        video.setUrl("url");
    }

    @Test
    void testSetTags() {
        video.setTags("tags");
    }
}

