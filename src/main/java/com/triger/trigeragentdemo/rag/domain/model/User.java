package com.triger.trigeragentdemo.rag.domain.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

//@Entity
//@Table(name = "user")
public class User {

    @Id
    private Long id;

    private String name;

    private LocalDateTime createdAt;

}
