package com.SpringBoot.JournalApp.controller;


import com.SpringBoot.JournalApp.entity.User;
import com.SpringBoot.JournalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/get-users")
    public ResponseEntity<?> getAllUsers(){
        List<User> all = userService.getAll();
        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>("No Users", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add-user")
    public ResponseEntity<?> postEntry(@RequestBody User entry){
        try{
            userService.saveEntry(entry);
            return new ResponseEntity<>("Created", HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("update-user/{u}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String u){
        User temp = userService.findByUserName(u);
        if(temp!=null) {
            if(user.getUserName() != null && !user.getUserName().isEmpty()){
                temp.setUserName(user.getUserName());
            }
            if(user.getPassword() != null && !user.getPassword().isEmpty()){
                temp.setPassword(user.getPassword());
            }
            userService.saveEntry(temp);
            return new ResponseEntity<>(temp, HttpStatus.OK);
        }
        return new ResponseEntity<>("Not Found",HttpStatus.NOT_FOUND);
    }
}
