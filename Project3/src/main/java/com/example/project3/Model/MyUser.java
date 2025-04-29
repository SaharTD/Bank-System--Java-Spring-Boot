package com.example.project3.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MyUser implements UserDetails {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Id
     private Integer id;


  @Column(columnDefinition = "varchar(30) not null unique")
  private String username;


     @Column(columnDefinition = "varchar(255) not null")
     private String password;



    @Column(columnDefinition = "varchar(30) not null")
    private String name;



    @Column(columnDefinition = "varchar(30) not null unique")
    private String email;


     @Column(columnDefinition = "varchar(30) not null")
     private String role;



    @OneToOne(cascade = CascadeType.ALL, mappedBy = "myUser")
    @PrimaryKeyJoinColumn
    @JsonIgnore
    private Employee employee;



    @OneToOne(cascade = CascadeType.ALL, mappedBy = "myUser")
    @PrimaryKeyJoinColumn
    @JsonIgnore
    private Customer customer;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
