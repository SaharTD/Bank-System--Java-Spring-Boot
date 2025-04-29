package com.example.project3.Service;


import com.example.project3.Api.ApiException;
import com.example.project3.Model.MyUser;
import com.example.project3.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final AuthRepository authRepository;

//    @Override
//    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
//
//        /// check database for username or email
//        MyUser user = authRepository.findMyUserByUsernameOrEmail(identifier, identifier);
//        if (user == null) {
//            throw new ApiException("wrong username / email or password");
//        }
//        return user;
//    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        /// check database
        MyUser user =authRepository.findMyUserByUsername(username);
        if (user==null){

            throw  new ApiException("wrong username or password");
        }
        return user;
    }


}