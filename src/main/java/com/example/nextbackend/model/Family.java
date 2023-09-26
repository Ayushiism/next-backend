package com.example.nextbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

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
    private long family_id;

    @Column(name = "user_name", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "plan")
    private  String plan;

    @OneToMany(fetch = FetchType.EAGER,mappedBy="family",cascade = CascadeType.ALL)
//    @JoinColumn(name = "family_id", referencedColumnName = "user_id")
    private Set<Customer_details> customerDetailsSet;
}
