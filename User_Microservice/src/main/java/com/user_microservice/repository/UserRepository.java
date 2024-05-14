package com.user_microservice.repository;

import com.user_microservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    User findUserByUsername(String username);
    boolean existsByUsername(String username);

   // @Modifying
    //@Query(value = "UPDATE User p SET p.role= :role where p.username= :username ")
   // void updateRole(@Param("username")String username, @Param("role")String role);
}
