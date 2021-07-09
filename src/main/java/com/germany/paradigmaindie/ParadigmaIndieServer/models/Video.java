package com.germany.paradigmaindie.ParadigmaIndieServer.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Data
public class Video {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long Id;

    private String name;

    private String description;

    private String url;

    private String tags;

}
