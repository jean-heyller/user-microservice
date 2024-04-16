package com.example.usermicroservice.adapters.driven.jpa.mysql.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String name;

    @Column(length = 50)
    private String lastName;


    @Column(length = 50)
    private String identification;

    @Column(length = 50)
    private String email;

    @Column(length = 50)
    private String password;

    @ManyToOne
    @JoinColumn(name = "id_rol")
    private RolEntity rol;


}
