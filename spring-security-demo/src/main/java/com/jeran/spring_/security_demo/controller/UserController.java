package com.jeran.spring_.security_demo.controller;

import com.jeran.spring_.security_demo.model.User;
import com.jeran.spring_.security_demo.deo.UserRepo;
import com.jeran.spring_.security_demo.servince.JwtService;
import com.jeran.spring_.security_demo.servince.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.pulsar.PulsarProperties;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

            @GetMapping("getallUsers")
            public List<User> getallUsers(){
                return userService.getAllUsers();
            }
            @PostMapping("addUser")
            public User addUser(@RequestBody User user){
             return userService.addUser(user);
            }

            @PostMapping("login")
            public String login(@RequestBody User user){
                    Authentication authentication = authenticationManager
                            .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

                    if (authentication.isAuthenticated())
                        return jwtService.generateToken(user.getUsername());
                    else
                     return "Login Failed";
                }
            @DeleteMapping("user/{userId}")
            public String deleteUser(@PathVariable int id){
            return  userService.deleteUser(id);
            }
}
