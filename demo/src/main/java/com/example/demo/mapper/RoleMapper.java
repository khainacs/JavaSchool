package com.example.demo.mapper;

import com.example.demo.dto.RoleDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import org.springframework.stereotype.Service;

@Service
public class RoleMapper extends AbstractMapper<Role, RoleDTO> {
   public RoleMapper() {
       super(User.class, UserDTO.class);
   }
}
