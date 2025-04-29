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
public class Account {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;


    @Column(columnDefinition = "varchar(20) not null")
    private String accountNumber;


    @Column(columnDefinition = "int not null")
    private Integer balance;


    @Column(columnDefinition = "boolean not null")
    private Boolean isActive;


    @ManyToOne
    @JsonIgnore
    private Customer customer;
}