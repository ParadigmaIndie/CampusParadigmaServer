package com.germany.paradigmaindie.ParadigmaIndieServer.models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter

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
