package com.example.usermicroservice.domain.model;




public class User {
    private final Long id;
    private String name;
    private String lastName;

    private String identification;
    private String email;
    private String password;

    private final Rol role;


    public User(Long id, String name,String lastName, String email, String password,String identification,Rol role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.identification = identification;
        this.lastName = lastName;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public Rol getRole() {
        return role;
    }



    public String getIdentification() {
        return identification;
    }
}