package com.germany.paradigmaindie.ParadigmaIndieServer.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@Data
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long Id;

    private String name;

    private String description;

    private String tags;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "courses_videos",
            joinColumns = @JoinColumn(
                    name = "course_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "video_id", referencedColumnName = "id"))
    private Set<Video> videos;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "courses_categories",
            joinColumns = @JoinColumn(
                    name = "course_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "categorie_id", referencedColumnName = "id"))
    private Set<Category> categorias;


}
