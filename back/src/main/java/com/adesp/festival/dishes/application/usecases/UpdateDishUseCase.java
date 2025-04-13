package com.adesp.festival.dishes.application.usecases;

import com.adesp.festival.UseCase;
import com.adesp.festival.dishes.application.services.DishService;
import com.adesp.festival.dishes.domain.entities.Dish;
import com.adesp.festival.dishes.domain.exceptions.NotFoundDishException;
import com.adesp.festival.dishes.domain.repositories.DishRepository;
import org.springframework.transaction.annotation.Transactional;

@UseCase
public class UpdateDishUseCase {

    private final DishRepository dishRepository;
    private final DishService dishService;

    public UpdateDishUseCase(DishRepository dishRepository, DishService dishService) {
        this.dishRepository = dishRepository;
        this.dishService = dishService;
    }

    @Transactional
    public Dish execute(Long id, Dish toUpdate){
        this.dishService.isNameUsedByRestaurant(id, toUpdate.getName(), toUpdate.getRestaurant().getId());
        Dish storedDish = this.dishRepository.findById(id)
                .map(dish -> {
                    this.dishService.restaurantExists(dish.getRestaurant().getId());
                    dish.setName(toUpdate.getName());
                    dish.setRestaurant(toUpdate.getRestaurant());
                    dish.setActive(toUpdate.getActive());

                    return dish;
                })
                .orElseThrow(() -> new NotFoundDishException());
        return this.dishRepository.save(storedDish);
    }
}
