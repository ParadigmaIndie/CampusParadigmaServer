package com.germany.paradigmaindie.ParadigmaIndieServer.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String name;

    private String description;

    private String tags;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Video> videos;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Category> categorias;


}
