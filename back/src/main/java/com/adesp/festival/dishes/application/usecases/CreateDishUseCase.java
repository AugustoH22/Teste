package com.adesp.festival.dishes.application.usecases;

import com.adesp.festival.UseCase;
import com.adesp.festival.dishes.application.services.DishService;
import com.adesp.festival.dishes.domain.entities.Dish;
import com.adesp.festival.dishes.domain.repositories.DishRepository;
import org.springframework.transaction.annotation.Transactional;

@UseCase
public class CreateDishUseCase {

    private final DishRepository dishRepository;
    private final DishService dishService;

    public CreateDishUseCase(DishRepository dishRepository, DishService dishService) {
        this.dishRepository = dishRepository;
        this.dishService = dishService;
    }

    @Transactional
    public Dish execute(Dish dish){
        this.dishService.isNameUsedByRestaurant(dish.getName(), dish.getRestaurant().getId());
        this.dishService.restaurantExists(dish.getRestaurant().getId());
        return this.dishRepository.save(dish);
    }
}
