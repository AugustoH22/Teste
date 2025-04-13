package com.adesp.festival.restaurant.application.usecases;

import com.adesp.festival.UseCase;
import com.adesp.festival.restaurant.domain.entities.Restaurant;
import com.adesp.festival.restaurant.domain.enums.Category;
import com.adesp.festival.restaurant.domain.repositories.RestaurantRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
@UseCase
public class FindAllRestaurantsByCategoryAndStatus {
    public final RestaurantRepository restaurantRepository;

    public FindAllRestaurantsByCategoryAndStatus(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public List<Restaurant> execute(Category category){
        return this.restaurantRepository.findAllRestaurantsByCategoryAndStatus(category);
    }

    public Page<Restaurant> execute(Integer page, Integer items, Category category){
        return this.restaurantRepository.findAllRestaurantsByCategoryAndStatus(category, PageRequest.of(page, items));
    }
}
