package com.example.usermicroservice.adapters.driven.jpa.mysql.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
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

    @Column(length = 50, unique = true)
    private String email;

    private String password;

    @Column(length = 50)
    private String phone;

    @Column(length = 50)
    private LocalDate birthDate;

    @ManyToOne
    @JoinColumn(name = "id_rol")
    private RolEntity rol;


}
