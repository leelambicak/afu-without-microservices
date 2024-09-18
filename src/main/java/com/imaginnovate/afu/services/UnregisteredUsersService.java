package com.imaginnovate.afu.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.imaginnovate.afu.dto.UnregisteredUserDto;
import com.imaginnovate.afu.model.UnregisteredUsers;
import com.imaginnovate.afu.repo.UnregisteredUsersRepo;

@Service
public class UnregisteredUsersService {
    @Autowired
    private UnregisteredUsersRepo unregisteredUsersRepo;

    @Autowired
    private ModelMapper modelMapper;

    public UnregisteredUsers createUnregisteredUser(UnregisteredUserDto unregisteredUserDto) {
        try {
            UnregisteredUsers unregisteredUsers = modelMapper.map(unregisteredUserDto, UnregisteredUsers.class);
            return unregisteredUsersRepo.save(unregisteredUsers);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An Internal server error occurred.",
                    e);
        }
    }

    public List<UnregisteredUserDto> getAllUnregisteredUsers() {
        try {
            List<UnregisteredUsers> unregisteredUsers = unregisteredUsersRepo.findAll();
            return unregisteredUsers.stream()
                    .map(unregisteredUser -> modelMapper.map(unregisteredUser, UnregisteredUserDto.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An Internal server error occurred.",
                    e);
        }
    }

    public UnregisteredUsers updateUnregisteredUser(Integer id, UnregisteredUsers updatedUser) {
        try {
            UnregisteredUsers existingUser = unregisteredUsersRepo.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User  not found."));
            if (updatedUser.getName() != null) {
                existingUser.setName(updatedUser.getName());
            }
            if (updatedUser.getGender() != null) {
                existingUser.setGender(updatedUser.getGender());
            }
            if (updatedUser.getEmail() != null) {
                existingUser.setEmail(updatedUser.getEmail());
            }
            if (updatedUser.getPhoneNo() != null) {
                existingUser.setPhoneNo(updatedUser.getPhoneNo());
            }
            if (updatedUser.getAddress() != null) {
                existingUser.setAddress(updatedUser.getAddress());
            }
            return unregisteredUsersRepo.save(existingUser);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An Internal server error occurred.",
                    e);
        }
    }

    public UnregisteredUsers deleteUnregisteredUser(int id) {
        try {
            UnregisteredUsers existingUser = unregisteredUsersRepo.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User  not found."));

            unregisteredUsersRepo.deleteById(id);
            return existingUser;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An Internal server error occurred.",
                    e);
        }
    }

}
