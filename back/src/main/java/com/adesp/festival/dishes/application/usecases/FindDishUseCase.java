package com.adesp.festival.dishes.application.usecases;

import com.adesp.festival.UseCase;
import com.adesp.festival.dishes.application.services.DishService;
import com.adesp.festival.dishes.domain.entities.Dish;
import com.adesp.festival.dishes.domain.exceptions.NotFoundDishException;
import com.adesp.festival.dishes.domain.repositories.DishRepository;

@UseCase
public class FindDishUseCase {

    private final DishRepository dishRepository;
    private final DishService dishService;

    public FindDishUseCase(DishRepository dishRepository, DishService dishService) {
        this.dishRepository = dishRepository;
        this.dishService = dishService;
    }

    public Dish execute(Long id){
        return this.dishRepository.findById(id)
                .orElseThrow(() -> new NotFoundDishException());
    }
}
