package com.example.project3.Repository;


import com.example.project3.Model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<MyUser,Integer> {


    MyUser findMyUserByUsernameOrEmail(String username, String email);

    MyUser findMyUserByUsername(String username);
    MyUser findMyUserById(Integer id);

}
