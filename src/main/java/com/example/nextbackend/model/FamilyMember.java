package com.example.nextbackend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

@ToString
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "FamilyMembers")
public class FamilyMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "first_name")
    private String first_name;

    @Column(name = "last_name")
    private String last_name;

    @JsonFormat(pattern="dd-MM-yyyy" , shape = JsonFormat.Shape.STRING)
    @Column(name = "dob")
    private String dob;

    @Column(name = "gender")
    private String gender;

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "family_id")
    private Family family;

}
