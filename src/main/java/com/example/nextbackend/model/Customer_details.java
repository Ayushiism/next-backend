package com.example.nextbackend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@ToString
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Customer_details")
public class Customer_details {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long user_id;

    @Column(name = "first_name")
    private String first_name;

    @Column(name = "last_name")
    private String last_name;


    @JsonFormat(pattern="dd-MM-yyyy" , shape = JsonFormat.Shape.STRING)
    @Column(name = "dob")
    private String dob;

    @Column(name = "gender")
    private String gender;

    @Column(name = "id_type")
    private String id_type;

    @Column(name = "id_number")
    private String id_number;

    // foreign key
    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "family id")
    private Family family;

}
