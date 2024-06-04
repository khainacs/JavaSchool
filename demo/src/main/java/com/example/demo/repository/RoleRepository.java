package com.example.demo.repository;

import com.example.demo.Enum.RoleName;
import com.example.demo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(RoleName roleName);
    boolean existsByRoleName(RoleName roleName);
}
