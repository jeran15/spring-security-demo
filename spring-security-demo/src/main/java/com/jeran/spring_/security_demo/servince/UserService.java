package com.jeran.spring_.security_demo.servince;

import com.jeran.spring_.security_demo.deo.UserRepo;
import com.jeran.spring_.security_demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;
    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);


    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User addUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        System.out.println(user.getPassword());
        return userRepo.save(user);
    }

    public String deleteUser( int id) {
        try{
        userRepo.deleteById(id);
        return "Deleted Sucessfully :" +id;
    }catch (Exception e) {
            e.printStackTrace();
            return "Error deleting user : " + e.getMessage();
        }
    }
}
