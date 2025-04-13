package com.adesp.festival.dishes.application.usecases;

import com.adesp.festival.UseCase;
import com.adesp.festival.dishes.application.services.DishService;
import com.adesp.festival.dishes.domain.repositories.DishRepository;

@UseCase
public class DeleteDishUseCase {

    private final DishRepository dishRepository;
    private final DishService dishService;

    public DeleteDishUseCase(DishRepository dishRepository, DishService dishService) {
        this.dishRepository = dishRepository;
        this.dishService = dishService;
    }

    public void execute(Long id){
        this.dishService.dishExists(id);
        this.dishRepository.deleteDishById(id);
    }
}
