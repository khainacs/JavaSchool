package com.example.demo.service.impl;


import com.example.demo.dto.RoleDTO;
import com.example.demo.mapper.RoleMapper;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RoleServiceImpl extends AbstractBaseServiceImpl<RoleDTO>
        implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    RoleMapper roleMapper;



    @Override
    public void setRepository() {
        AbstractBaseServiceImpl.setRepository(roleRepository);
    }

    public RoleServiceImpl() {
        super.setMapper(new RoleMapper());
    }


}