package com.example.nextbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Family")
public class Family {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "family_id")
    private long customer_id;

    @Column(name = "user_name")
    private String first_name;

    @Column(name = "password")
    private String last_name;

    @Column(name = "plan")
    private  String plan;
}
