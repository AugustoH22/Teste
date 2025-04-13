package com.adesp.festival.authentication.domain.repositories;

import com.adesp.festival.authentication.domain.entities.Invite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InviteRepository extends JpaRepository<Invite, Long> {

    Optional<Invite> findByInviteToken(String inviteToken);

    @Query(value = "SELECT * FROM invites WHERE is_active = TRUE AND expires_in > NOW() AND invite_token = ?1", nativeQuery = true)
    Optional<Invite> findActiveInviteToken(String inviteToken);

    List<Invite> findByDestinationEmail(String destinationEmail);
}
