package com.adesp.festival.authentication.domain.repositories;

import com.adesp.festival.authentication.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM users u WHERE u.email = :identifier OR u.username = :identifier", nativeQuery = true)
    Optional<User> findByUsernameOrEmail(@Param("identifier") String identifier);

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);
}
