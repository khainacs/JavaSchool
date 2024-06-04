package com.example.demo.mapper;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper extends AbstractMapper<User, UserDTO> {
    public UserMapper() {
        super(User.class, UserDTO.class);
    }
}
