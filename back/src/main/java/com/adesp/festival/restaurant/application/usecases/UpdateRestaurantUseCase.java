package com.adesp.festival.restaurant.application.usecases;

import com.adesp.festival.UseCase;
import com.adesp.festival.restaurant.application.services.RestaurantService;
import com.adesp.festival.restaurant.domain.entities.Restaurant;
import com.adesp.festival.restaurant.domain.exceptions.NotFoundRestaurantException;
import com.adesp.festival.restaurant.domain.repositories.RestaurantRepository;

@UseCase
public class UpdateRestaurantUseCase {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantService restaurantService;

    public UpdateRestaurantUseCase(RestaurantRepository restaurantRepository, RestaurantService restaurantService) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantService = restaurantService;
    }

    public Restaurant execute(Long id, Restaurant toUpdate){
        Restaurant stored = this.restaurantRepository.findById(id)
                .map(restaurant -> {
                    restaurant.setName(toUpdate.getName());
                    this.restaurantService.isNameUsed(id, toUpdate.getName());
                    restaurant.setResponsibleName(toUpdate.getResponsibleName());
                    restaurant.setCep(toUpdate.getCep());
                    restaurant.setAddress(toUpdate.getAddress());
                    restaurant.setNeighborhood(toUpdate.getNeighborhood());
                    restaurant.setNumber(toUpdate.getNumber());
                    restaurant.setActive(toUpdate.getActive());
                    restaurant.setCategory(toUpdate.getCategory());

                    return restaurant;
                })
                .orElseThrow(() -> new NotFoundRestaurantException());

        return this.restaurantRepository.save(stored);
    }
}
