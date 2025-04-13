package com.adesp.festival.dishes.infrastructure.mappers;

import com.adesp.festival.dishes.application.dtos.request.CreateDishRequest;
import com.adesp.festival.dishes.application.dtos.request.UpdateDishRequest;
import com.adesp.festival.dishes.application.dtos.response.CreateDishResponse;
import com.adesp.festival.dishes.application.dtos.response.FindDishResponse;
import com.adesp.festival.dishes.application.dtos.response.UpdateDishResponse;
import com.adesp.festival.dishes.domain.entities.Dish;
import com.adesp.festival.restaurant.domain.entities.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface DishMapper {

    @Mapping(target = "isActive", source = "active")
    @Mapping(target = "restaurant", source = "restaurant", qualifiedByName = "mapRestaurantToLong")
    CreateDishResponse domainToCreateDishResponse(Dish dish);

    @Mapping(target = "restaurant", source = "restaurant", qualifiedByName = "mapLongToRestaurant")
    Dish createDishRequestToDomain(CreateDishRequest createDishRequest);

    @Mapping(target = "isActive", source = "active")
    @Mapping(target = "restaurant", source = "restaurant", qualifiedByName = "mapRestaurantToLong")
    UpdateDishResponse domainToUpdateDishResponse(Dish dish);

    @Mapping(target = "restaurant", source = "restaurant", qualifiedByName = "mapLongToRestaurant")
    Dish updateDishRequestToDomain(UpdateDishRequest updateDishRequest);

    @Mapping(target = "isActive", source = "active")
    @Mapping(target = "restaurant", source = "restaurant", qualifiedByName = "mapRestaurantToLong")
    FindDishResponse domainToFindDishResponse(Dish dish);

    @Named("mapRestaurantToLong")
    default Long mapRestaurantToLong(Restaurant restaurant){
        return restaurant.getId();
    }

    @Named("mapLongToRestaurant")
    default Restaurant mapLongToRestaurant(Long restaurant){
        return new Restaurant(restaurant);
    }
}
