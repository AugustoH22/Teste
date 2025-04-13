package com.adesp.festival.dishes.domain.repositories;

import com.adesp.festival.dishes.domain.entities.Dish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {

    @Query(value = "SELECT * FROM dishes WHERE name = ?1 AND restaurant_id = ?2 AND is_active = TRUE", nativeQuery = true)
    Optional<Dish> findActiveDishesInRestaurantByName(String name, Long restaurantId);

    @Modifying
    @Query(value = "UPDATE dishes SET is_deleted = TRUE WHERE id = ?1", nativeQuery = true)
    void deleteDishById(Long id);

    @Query(value = "SELECT * FROM dishes WHERE is_active = TRUE", nativeQuery = true)
    List<Dish> findActiveDishes();

    @Query(value = "SELECT * FROM dishes WHERE is_active = TRUE", nativeQuery = true)
    Page<Dish> findActiveDishes(Pageable pagination);
}
