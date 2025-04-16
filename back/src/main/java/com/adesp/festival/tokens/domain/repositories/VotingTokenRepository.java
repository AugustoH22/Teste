package com.adesp.festival.tokens.domain.repositories;

import com.adesp.festival.tokens.application.dtos.response.VotingInfosResponse;
import com.adesp.festival.tokens.domain.entities.VotingToken;
import com.adesp.festival.tokens.domain.entities.VotingTokenBatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VotingTokenRepository extends JpaRepository<VotingToken, Long> {

    Optional<VotingToken> findByVotingToken(String votingToken);

    List<VotingToken> findByCpf(String cpf);

    @Query(value = """
            SELECT
            	voting_tokens.id,
            	voting_tokens.voting_token,
            	voting_tokens.cpf,
            	voting_tokens.batch_id,
            	voting_tokens.is_active,
            	voting_tokens.is_deleted,
            	voting_tokens.created_at,
            	voting_tokens.updated_at
            FROM voting_tokens
            INNER JOIN voting_token_batches ON voting_token_batches.id = batch_id
            INNER JOIN dishes ON dishes.id = voting_token_batches.dish_id
            WHERE voting_tokens.cpf = ?1 AND dishes.id = ?2;
            """, nativeQuery = true)
    Optional<VotingToken> findVoteByCpfAndDish(String cpf, Long dishId);

    @Query(value = """
            SELECT
            restaurants.name as "restaurant_name",
            dishes.name as "dish_name"
            FROM voting_tokens
            INNER JOIN voting_token_batches ON voting_token_batches.id = batch_id
            INNER JOIN dishes ON dishes.id = voting_token_batches.dish_id
            INNER JOIN restaurants ON restaurants.id = dishes.restaurant_id
            WHERE voting_token = ?1 AND voting_tokens.is_active = true;
            """, nativeQuery = true)
    Optional<VotingInfosResponse> findVotingInfos(String votingToken);

    List<VotingToken> findByBatchId(Long votingTokensBatchId);

}
