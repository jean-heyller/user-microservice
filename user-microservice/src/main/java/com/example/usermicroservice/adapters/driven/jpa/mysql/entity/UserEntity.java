package com.example.usermicroservice.adapters.driven.jpa.mysql.entity;

import lombok.*;

import javax.persistence.*;

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

    @ManyToOne
    @JoinColumn(name = "id_rol")
    private RolEntity rol;

    @Column(name = "is_enabled")
    private Boolean isEnabled = true;

    @Column(name = "is_account_non_expired")
    private Boolean isAccountNonExpired = true;

    @Column(name = "is_account_non_locked")
    private Boolean isAccountNonLocked = true;

    @Column(name = "is_credentials_non_expired")
    private Boolean isCredentialsNonExpired = true;



}
