package com.adesp.festival.authentication.domain.repositories;

import com.adesp.festival.authentication.domain.entities.Session;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

    Optional<Session> findByRefreshToken(String refreshToken);

    void deleteByUserId(Long id);

    @Query(value = "SELECT * FROM sessions WHERE user_id = :id AND is_deleted <> true AND is_expired <> true", nativeQuery = true)
    List<Session> findSessionsByUser(@Param("id") Long userId);

    @Query(value = "SELECT * FROM sessions WHERE remote_address = :remoteAddress AND is_deleted <> true AND is_expired <> true", nativeQuery = true)
    Optional<Session> findByRemoteAddress(@Param("remoteAddress") String remoteAddress);

    @Modifying
    @Transactional
    @Query(value = "UPDATE sessions SET is_deleted = true WHERE id = :id", nativeQuery = true)
    void deleteSessionById(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE sessions SET is_expired = true WHERE id = :id", nativeQuery = true)
    void setSessionExpired(@Param("id") Long id);
}
