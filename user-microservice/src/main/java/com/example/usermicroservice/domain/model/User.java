package com.example.usermicroservice.domain.model;




public class User {
    private final Long id;
    private String name;
    private String lastName;

    private String identification;
    private String email;
    private String password;

    private  Rol rol;


    public User(Long id, String name,String lastName, String email, String password,String identification,Rol rol) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.identification = identification;
        this.lastName = lastName;
        this.rol = rol;
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



    public Rol getRol() {
        return rol;
    }



    public String getIdentification() {
        return identification;
    }

    public void  setRole(Rol rol){
        this.rol = rol;
    }
}