package com.adesp.festival.restaurant.application.usecases;

import com.adesp.festival.UseCase;
import com.adesp.festival.restaurant.domain.entities.Restaurant;
import com.adesp.festival.restaurant.domain.exceptions.NotFoundRestaurantException;
import com.adesp.festival.restaurant.domain.repositories.RestaurantRepository;

@UseCase
public class FindRestaurantUseCase {

    private final RestaurantRepository restaurantRepository;

    public FindRestaurantUseCase(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant execute(Long id){
        return this.restaurantRepository.findById(id)
                .orElseThrow(NotFoundRestaurantException::new);
    }
}
