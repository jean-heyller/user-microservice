package com.example.usermicroservice.domain.model;

public class Rol {
    private final Long id;
    private  String name;
    private final String description;

    public Rol(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }



    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
