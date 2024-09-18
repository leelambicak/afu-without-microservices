package com.imaginnovate.afu.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import com.imaginnovate.afu.dto.UserDto;
import com.imaginnovate.afu.model.Users;

public interface UsersRepo extends JpaRepository<Users, Integer> {

    ResponseEntity<Users> save(UserDto users);

    Users deleteById(Optional<Users> user);

    Optional<Users> findByEmail(String email);

}
