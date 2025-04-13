package com.adesp.festival.restaurant.infrastructure.mappers;

import com.adesp.festival.authentication.domain.entities.User;
import com.adesp.festival.restaurant.application.dtos.request.CreateRestaurantRequest;
import com.adesp.festival.restaurant.application.dtos.request.UpdateRestaurantRequest;
import com.adesp.festival.restaurant.application.dtos.response.CreateRestaurantResponse;
import com.adesp.festival.restaurant.application.dtos.response.GetRestaurantResponse;
import com.adesp.festival.restaurant.application.dtos.response.UpdateRestaurantResponse;
import com.adesp.festival.restaurant.domain.entities.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {

    @Mapping(target = "active", source = "isActive")
    Restaurant createRestaurantRequestToDomain(CreateRestaurantRequest createRestaurantRequest);

    @Mapping(target = "isActive", source = "active")
    CreateRestaurantResponse domainToCreateRestaurantResponse(Restaurant restaurant);

    @Mapping(target = "active", source = "isActive")
    Restaurant updateRestaurantRequestToDomain(UpdateRestaurantRequest updateRestaurantRequest);

    @Mapping(target = "isActive", source = "active")
    UpdateRestaurantResponse domainToUpdateRestaurantResponse(Restaurant restaurant);

    GetRestaurantResponse domainToGetRestaurantResponse(Restaurant restaurant);

}
