package com.germany.paradigmaindie.undefinedlearn.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
public class Privilege {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;


    public Privilege(String name) {
        this.name=name;
    }

    public Privilege() {

    }
}
