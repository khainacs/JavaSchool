package com.example.demo.service;


import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.user.WebUser;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserService {
    boolean deleteByUuid(String uuid);

    List<UserDTO> findAllisNotDelete();

    UserDTO createUser(UserDTO userDTO);
    boolean updateInfo(UserDTO userDTO);
}
