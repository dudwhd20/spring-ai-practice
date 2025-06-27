package com.triger.trigeragentdemo.postgretool.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "leave")
public class Leave {

    @Id
    private Long id;

    private String userName;

    private int leaveCount;

    private String year;

}
