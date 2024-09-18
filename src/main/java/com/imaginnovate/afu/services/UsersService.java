package com.imaginnovate.afu.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.imaginnovate.afu.dto.UserDto;
import com.imaginnovate.afu.exception.ConflictException;
import com.imaginnovate.afu.exception.InternalServerErrorException;
import com.imaginnovate.afu.exception.NotFoundException;
import com.imaginnovate.afu.model.Users;
import com.imaginnovate.afu.repo.UsersRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UsersService {
    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private ModelMapper modelMapper;

    public Users createUser(UserDto userDto) {

        Optional<Users> existedUser = usersRepo.findByEmail(userDto.getEmail());
        if (existedUser.isPresent()) {
            throw new ConflictException("This user email already existed");
        } else {
            Users users = modelMapper.map(userDto, Users.class);
            return usersRepo.save(users);
        }
    }

    public List<UserDto> getAllUsers() {
        try {
            List<Users> allUsers = usersRepo.findAll();
            return allUsers.stream().map(users -> modelMapper.map(users, UserDto.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
        }   
    }


    public Users updateUsers(Integer id, Users user) {
        try {
            Users savedUser = usersRepo.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User  not found."));
            if (user.getId() != null) {
                savedUser.setId(user.getId());
            }
            if (user.getName() != null) {
                savedUser.setName(user.getName());
            }
            if (user.getEmail() != null) {
                savedUser.setEmail(user.getEmail());
            }
            if (user.getIsAdmin() != null) {
                savedUser.setIsAdmin(user.getIsAdmin());
            }
            if (user.getAddress() != null) {
                savedUser.setAddress(user.getAddress());
            }
            if (user.getGender() != null) {
                savedUser.setGender(user.getGender());
            }
            if (user.getPhoneNo() != null) {
                savedUser.setPhoneNo(user.getPhoneNo());
            }
            Users users = usersRepo.save(savedUser);
            return users;
        } catch (Exception e) {
            throw new InternalServerErrorException("An internal server error occured");
        }
    }

    public Users deleteUser(Integer id) {
        Optional<Users> user = usersRepo.findById(id);
        try {
            if (user.isEmpty()) {
                throw new NotFoundException("User not found.");
            }
            return usersRepo.deleteById(user);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
        }
    }
}
