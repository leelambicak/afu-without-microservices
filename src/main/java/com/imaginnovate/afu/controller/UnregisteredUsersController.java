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

import com.imaginnovate.afu.dto.UnregisteredUserDto;
import com.imaginnovate.afu.model.UnregisteredUsers;
import com.imaginnovate.afu.services.UnregisteredUsersService;

@RestController
@RequestMapping("/unregistered-users")
public class UnregisteredUsersController {

    @Autowired
    private UnregisteredUsersService unregisteredUsersService;

    @GetMapping("/get-all")
    public List<UnregisteredUserDto> getAllUnregisteredUsers() {
        return unregisteredUsersService.getAllUnregisteredUsers();
    }

    @PostMapping("/create")
    public UnregisteredUsers createUnregisteredUser(@RequestBody UnregisteredUserDto user) {
        return unregisteredUsersService.createUnregisteredUser(user);
    }

    @PutMapping("/update/{id}")
    public UnregisteredUsers updateUnregisteredUser(@PathVariable Integer id, @RequestBody UnregisteredUsers user) {
        return unregisteredUsersService.updateUnregisteredUser(id, user);
    }

    @DeleteMapping("/delete/{id}")
    public UnregisteredUsers deleteUnregisteredUser(@PathVariable Integer id) {
        return unregisteredUsersService.deleteUnregisteredUser(id);
    }

}
