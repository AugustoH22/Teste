package com.adesp.festival.dishes.application.usecases;

import com.adesp.festival.UseCase;
import com.adesp.festival.dishes.application.services.DishService;
import com.adesp.festival.dishes.domain.entities.Dish;
import com.adesp.festival.dishes.domain.repositories.DishRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@UseCase
public class FindActiveDishesUseCase {

    private final DishRepository dishRepository;
    private final DishService dishService;

    public FindActiveDishesUseCase(DishRepository dishRepository, DishService dishService) {
        this.dishRepository = dishRepository;
        this.dishService = dishService;
    }

    public List<Dish> execute(){
        return this.dishRepository.findActiveDishes();
    }

    public Page<Dish> execute(Integer page, Integer items){
        return this.dishRepository.findActiveDishes(PageRequest.of(page, items));
    }
}
