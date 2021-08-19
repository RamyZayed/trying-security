package com.example.security.Controller;

import com.example.security.entity.User;
import com.example.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/{id}")
    public User findById(@PathVariable int id){
        return userRepository.findById(id).get();
    }


    @GetMapping("/")
    public List<User> findAll(){
        return (List<User>) userRepository.findAll();
    }

    @PostMapping("/")
    public User create (@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);

    }


    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id){
        userRepository.deleteById(id);
    }



}
