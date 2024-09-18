package com.imaginnovate.afu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imaginnovate.afu.dto.UserDto;
import com.imaginnovate.afu.model.Users;
import com.imaginnovate.afu.services.UsersService;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/get-all")
    public List<UserDto> getAllUsers() {
        return usersService.getAllUsers();
    }

    @PostMapping("/create")
    public Users createUsers(@RequestBody UserDto users) {
        return usersService.createUser(users);
    }

    @PutMapping("/update/{id}")
    public Users updateUsers(@PathVariable Integer id, @RequestBody Users users) {
        return usersService.updateUsers(id, users);
    }

    @DeleteMapping("/delete/{id}")
    public Users deletUsers(@PathVariable Integer id) {
        return usersService.deleteUser(id);
    }

}
