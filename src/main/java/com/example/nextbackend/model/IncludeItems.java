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
@Table(name = "IncludeItems")
public class IncludeItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "info")
    private String info;


    // foreign key
    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "pid", referencedColumnName = "id" )
    private Packages packages;

}
