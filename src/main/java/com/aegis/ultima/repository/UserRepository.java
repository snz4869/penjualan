package com.aegis.ultima.repository;

import com.aegis.ultima.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface UserRepository extends JpaRepository<User,String> {

    User findUserByUsername(String username);

    @Query("SELECT u.role FROM User u WHERE u.id = :userId")
    Set<String> findRoleNamesByUserId(@Param("userId") String userId);

}
