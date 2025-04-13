package com.adesp.festival.restaurant.domain.repositories;

import com.adesp.festival.restaurant.domain.entities.Restaurant;
import com.adesp.festival.restaurant.domain.enums.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    @Query(value = "SELECT * FROM restaurants WHERE is_active = TRUE AND name = ?1", nativeQuery = true)
    Optional<Restaurant> findByNameAndStatus(String name);

    @Query(value = "SELECT * FROM restaurants WHERE is_active = TRUE AND category = ?1", nativeQuery = true)
    List<Restaurant> findAllRestaurantsByCategoryAndStatus(Category category);

    @Query(value = "SELECT * FROM restaurants WHERE is_active = TRUE AND category = ?1", nativeQuery = true)
    Page<Restaurant> findAllRestaurantsByCategoryAndStatus(Category category,Pageable pagination);

    @Query(value = "SELECT * FROM restaurants WHERE is_active = TRUE", nativeQuery = true)
    List<Restaurant> findAllActive();

    @Query(value = "SELECT * FROM restaurants WHERE is_active = TRUE", nativeQuery = true)
    Page<Restaurant> findAllActive(Pageable pagination);

    @Modifying
    @Transactional
    @Query(value = "UPDATE restaurants SET is_deleted = TRUE WHERE id = ?1", nativeQuery = true)
    void deleteRestaurantById(Long id);


}
