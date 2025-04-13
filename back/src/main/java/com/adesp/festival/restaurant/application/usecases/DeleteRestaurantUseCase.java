package com.adesp.festival.restaurant.application.usecases;

import com.adesp.festival.UseCase;
import com.adesp.festival.restaurant.domain.entities.Restaurant;
import com.adesp.festival.restaurant.domain.exceptions.NotFoundRestaurantException;
import com.adesp.festival.restaurant.domain.repositories.RestaurantRepository;

@UseCase
public class DeleteRestaurantUseCase {

    private final RestaurantRepository restaurantRepository;

    public DeleteRestaurantUseCase(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public void execute(Long id){
        Restaurant restaurant = this.restaurantRepository.findById(id)
                .orElseThrow(() -> new NotFoundRestaurantException());

        this.restaurantRepository.deleteRestaurantById(id);
    }
}
