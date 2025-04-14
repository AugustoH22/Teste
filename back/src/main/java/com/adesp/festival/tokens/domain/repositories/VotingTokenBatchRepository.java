package com.adesp.festival.tokens.domain.repositories;

import com.adesp.festival.tokens.domain.entities.VotingTokenBatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotingTokenBatchRepository extends JpaRepository<VotingTokenBatch, Long> {
}
