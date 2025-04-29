package com.example.project3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {

    @Id
    private Integer id;

    @Column(columnDefinition = "varchar(30) not null")
    private String position;


    @Column(columnDefinition = "int not null")
    private Integer salary;


    @OneToOne
    @MapsId
    private MyUser myUser;
}
