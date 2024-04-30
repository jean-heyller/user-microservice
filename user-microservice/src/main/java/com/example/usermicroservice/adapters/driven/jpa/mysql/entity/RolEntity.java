package com.example.usermicroservice.adapters.driven.jpa.mysql.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "rol")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RolEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(length = 50)
        private String name;
        @Column(length = 90)
        private String description;

}
