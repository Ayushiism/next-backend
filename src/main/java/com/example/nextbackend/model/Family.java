package com.example.nextbackend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@ToString
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Family")
public class Family {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "familyid")
    private long family_id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;


    @ManyToOne(cascade = CascadeType.MERGE)
    private Plan plan;

    @OneToMany(cascade= CascadeType.ALL)
    private Set<FamilyMember> family_members = new HashSet<>();
}
