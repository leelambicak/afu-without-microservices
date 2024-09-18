package com.imaginnovate.afu.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imaginnovate.afu.dto.UnregisteredUserDto;
import com.imaginnovate.afu.model.UnregisteredUsers;

public interface UnregisteredUsersRepo extends JpaRepository<UnregisteredUsers, Integer> {

    UnregisteredUsers save(UnregisteredUserDto unregisteredUsers);

}
