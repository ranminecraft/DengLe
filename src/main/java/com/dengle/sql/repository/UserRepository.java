package com.dengle.sql.repository;

import com.dengle.sql.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<Users, Long> {
    @Query
    Users findByEmail(@Param("email") String email);
    @Query
    Users findByUsername(@Param("username") String username);
}
