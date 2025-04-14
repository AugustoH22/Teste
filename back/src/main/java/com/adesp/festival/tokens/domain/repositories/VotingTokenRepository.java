package com.adesp.festival.tokens.domain.repositories;

import com.adesp.festival.tokens.domain.entities.VotingToken;
import com.adesp.festival.tokens.domain.entities.VotingTokenBatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VotingTokenRepository extends JpaRepository<VotingToken, Long> {

    Optional<VotingToken> findByVotingToken(String votingToken);

    List<VotingToken> findByCpf(String cpf);

    List<VotingToken> findByBatchId(Long votingTokensBatchId);

}
