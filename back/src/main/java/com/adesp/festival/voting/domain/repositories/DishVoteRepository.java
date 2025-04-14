package com.adesp.festival.voting.domain.repositories;

import com.adesp.festival.voting.domain.entities.DishVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishVoteRepository extends JpaRepository<DishVote, Long> {
}
