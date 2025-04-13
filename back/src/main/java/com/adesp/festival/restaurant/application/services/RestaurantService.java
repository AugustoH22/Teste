package com.adesp.festival.restaurant.application.services;

import com.adesp.festival.restaurant.domain.entities.Restaurant;
import com.adesp.festival.restaurant.domain.exceptions.RestaurantNameException;
import com.adesp.festival.restaurant.domain.repositories.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Boolean isNameUsed(String name){
        Optional<Restaurant> storedRestaurant = this.restaurantRepository.findByNameAndStatus(name);

        if(storedRestaurant.isPresent()){
            throw new RestaurantNameException();
        }

        return false;
    }

    public Boolean isNameUsed(Long id, String name){
        Optional<Restaurant> storedRestaurant = this.restaurantRepository.findByNameAndStatus(name);

        if(storedRestaurant.isPresent()){
            if(storedRestaurant.get().getId() != id){
                throw new RestaurantNameException();
            }
        }

        return false;
    }
}
