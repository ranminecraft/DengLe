package com.dengle.sql.repository;

import com.dengle.sql.entity.Students;
import com.dengle.sql.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentsRepository extends JpaRepository<Students, Long> {
    @Query
    Users findByName(@Param("name") String name);

}
