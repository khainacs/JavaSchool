package com.example.demo.service.impl;


import com.example.demo.Enum.RoleName;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.user.WebUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl extends AbstractBaseServiceImpl<UserDTO>
        implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserMapper mapper;



    @Override
    public void setRepository() {
        AbstractBaseServiceImpl.setRepository(userRepository);
    }

    public UserServiceImpl() {
        super.setMapper(new UserMapper());
    }


    @Override
    public boolean deleteByUuid(String uuid) {
        User user = userRepository.findByUuid(uuid).orElseThrow();
        user.setDeleted(true);
        userRepository.saveAndFlush(user);
        return true;
    }

    @Override
    public List<UserDTO> findAllisNotDelete() {
        List<User> userList = userRepository.findAllisNotDelete();
        return (List<UserDTO>) userList.stream().map((entity) -> mapper.convertEntityToDTO(entity)).toList();
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        try {
            if (userRepository.existsByEmail(userDTO.getEmail())) {
                log.info("Email already exist: {}", userDTO.getEmail());
                return null;
            }
            User user = new User();
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setEmail(userDTO.getEmail());
            user.setPassword(userDTO.getPassword());
            // Set Role
            for (Role roleName : userDTO.getRoles()) {
                if (roleName.getRoleName().name().equals("ADMIN")) {
                    Role role = roleRepository.findByRoleName(RoleName.ADMIN);
                    user.getRoles().add(role);
                }
                if (roleName.getRoleName().name().equals("USER")) {
                    Role role = roleRepository.findByRoleName(RoleName.USER);
                    user.getRoles().add(role);
                }
            }
            return mapper.convertEntityToDTO(userRepository.saveAndFlush(user));
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    @Override
    public boolean updateInfo(UserDTO userDTO) {
        try {
            User user = userRepository.findByUuid(userDTO.getUuid()).get();

            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setEmail(userDTO.getEmail());
            user.setPassword(userDTO.getPassword());
            userRepository.saveAndFlush(user);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
