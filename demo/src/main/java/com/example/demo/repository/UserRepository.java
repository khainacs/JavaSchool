package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.uuid = :uuid")
    Optional<User> findByUuid(@Param("uuid") String uuid);

    @Query("SELECT u from User u WHERE u.isDeleted = false")
    List<User> findAllisNotDelete();

    boolean existsByEmail(String email);
}
