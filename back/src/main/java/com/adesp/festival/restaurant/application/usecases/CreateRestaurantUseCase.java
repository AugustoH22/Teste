package com.adesp.festival.restaurant.application.usecases;

import com.adesp.festival.UseCase;
import com.adesp.festival.restaurant.application.services.RestaurantService;
import com.adesp.festival.restaurant.domain.entities.Restaurant;
import com.adesp.festival.restaurant.domain.repositories.RestaurantRepository;

@UseCase
public class CreateRestaurantUseCase {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantService restaurantService;

    public CreateRestaurantUseCase(RestaurantRepository restaurantRepository, RestaurantService restaurantService) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantService = restaurantService;
    }

    public Restaurant execute(Restaurant restaurant){
        this.restaurantService.isNameUsed(restaurant.getName());
        return this.restaurantRepository.save(restaurant);
    }
}
