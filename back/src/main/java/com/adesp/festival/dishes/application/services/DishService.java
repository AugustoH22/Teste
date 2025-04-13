package com.adesp.festival.dishes.application.services;

import com.adesp.festival.dishes.domain.entities.Dish;
import com.adesp.festival.dishes.domain.exceptions.DishNameException;
import com.adesp.festival.dishes.domain.exceptions.NotFoundDishException;
import com.adesp.festival.dishes.domain.repositories.DishRepository;
import com.adesp.festival.restaurant.domain.entities.Restaurant;
import com.adesp.festival.restaurant.domain.exceptions.NotFoundRestaurantException;
import com.adesp.festival.restaurant.domain.repositories.RestaurantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class DishService {

    private final DishRepository dishRepository;
    private final RestaurantRepository restaurantRepository;

    public DishService(DishRepository dishRepository, RestaurantRepository restaurantRepository) {
        this.dishRepository = dishRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Transactional(readOnly = true)
    public Boolean isNameUsedByRestaurant(String name, Long restaurantId){
        Optional<Dish> storedDish = this.dishRepository.findActiveDishesInRestaurantByName(name, restaurantId);

        if(storedDish.isPresent()){
            throw new DishNameException();
        }

        return false;
    }

    @Transactional(readOnly = true)
    public Boolean isNameUsedByRestaurant(Long id, String name, Long restaurantId){
        Optional<Dish> storedDish = this.dishRepository.findActiveDishesInRestaurantByName(name, restaurantId);

        if(storedDish.isPresent()){
            if(storedDish.get().getId() != id){
                throw new DishNameException();
            }
        }

        return false;
    }

    @Transactional(readOnly = true)
    public Boolean dishExists(Long id){
        Dish storedDish = this.dishRepository.findById(id)
                .orElseThrow(() -> new NotFoundDishException());

        return true;
    }

    @Transactional(readOnly = true)
    public Boolean restaurantExists(Long id){
        Restaurant storedRestaurant = this.restaurantRepository.findById(id)
                .orElseThrow(() -> new NotFoundRestaurantException());

        return true;
    }
}
