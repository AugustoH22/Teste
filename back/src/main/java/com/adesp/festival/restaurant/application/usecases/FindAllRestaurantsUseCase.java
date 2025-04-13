package com.adesp.festival.restaurant.application.usecases;

import com.adesp.festival.UseCase;
import com.adesp.festival.restaurant.domain.entities.Restaurant;
import com.adesp.festival.restaurant.domain.repositories.RestaurantRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@UseCase
public class FindAllRestaurantsUseCase {

    public final RestaurantRepository restaurantRepository;

    public FindAllRestaurantsUseCase(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public List<Restaurant> execute(){
        return this.restaurantRepository.findAll();
    }

    public Page<Restaurant> execute(Integer page, Integer items){
        return this.restaurantRepository.findAll(PageRequest.of(page, items));
    }
}
