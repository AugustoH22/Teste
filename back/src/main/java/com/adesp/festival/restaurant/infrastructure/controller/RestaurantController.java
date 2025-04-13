package com.adesp.festival.restaurant.infrastructure.controller;

import com.adesp.festival.restaurant.application.dtos.request.CreateRestaurantRequest;
import com.adesp.festival.restaurant.application.dtos.request.UpdateRestaurantRequest;
import com.adesp.festival.restaurant.application.dtos.response.CreateRestaurantResponse;
import com.adesp.festival.restaurant.application.dtos.response.DeleteRestaurantResponse;
import com.adesp.festival.restaurant.application.dtos.response.GetRestaurantResponse;
import com.adesp.festival.restaurant.application.dtos.response.UpdateRestaurantResponse;
import com.adesp.festival.restaurant.application.usecases.*;
import com.adesp.festival.restaurant.domain.entities.Restaurant;
import com.adesp.festival.restaurant.domain.enums.Category;
import com.adesp.festival.restaurant.infrastructure.mappers.RestaurantMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestaurantController {

    private final CreateRestaurantUseCase createRestaurantUseCase;
    private final UpdateRestaurantUseCase updateRestaurantUseCase;
    private final DeleteRestaurantUseCase deleteRestaurantUseCase;
    private final FindActiveRestaurantsUseCase findActiveRestaurantsUseCase;
    private final FindRestaurantUseCase findRestaurantUseCase;
    private final FindAllRestaurantsUseCase findAllRestaurantsUseCase;
    private final RestaurantMapper restaurantMapper;
    private final FindAllRestaurantsByCategoryAndStatus findAllRestaurantsByCategoryAndStatus;

    public RestaurantController(FindAllRestaurantsByCategoryAndStatus findAllRestaurantsByCategoryAndStatus,CreateRestaurantUseCase createRestaurantUseCase, UpdateRestaurantUseCase updateRestaurantUseCase, DeleteRestaurantUseCase deleteRestaurantUseCase, FindActiveRestaurantsUseCase findActiveRestaurantsUseCase, FindRestaurantUseCase findRestaurantUseCase, FindAllRestaurantsUseCase findAllRestaurantsUseCase, RestaurantMapper restaurantMapper) {
        this.createRestaurantUseCase = createRestaurantUseCase;
        this.updateRestaurantUseCase = updateRestaurantUseCase;
        this.deleteRestaurantUseCase = deleteRestaurantUseCase;
        this.findActiveRestaurantsUseCase = findActiveRestaurantsUseCase;
        this.findRestaurantUseCase = findRestaurantUseCase;
        this.findAllRestaurantsUseCase = findAllRestaurantsUseCase;
        this.restaurantMapper = restaurantMapper;
        this.findAllRestaurantsByCategoryAndStatus = findAllRestaurantsByCategoryAndStatus;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/v1/restaurant")
    public ResponseEntity<CreateRestaurantResponse> post(@RequestBody CreateRestaurantRequest createRestaurantRequest, HttpServletRequest request){
        Restaurant restaurant = this.restaurantMapper.createRestaurantRequestToDomain(createRestaurantRequest);
        Restaurant storedRestaurant = this.createRestaurantUseCase.execute(restaurant);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.restaurantMapper.domainToCreateRestaurantResponse(storedRestaurant));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/v1/restaurant/{id}")
    public ResponseEntity<UpdateRestaurantResponse> put(@PathVariable Long id, @RequestBody UpdateRestaurantRequest updateRestaurantRequest){
        Restaurant restaurant = this.restaurantMapper.updateRestaurantRequestToDomain(updateRestaurantRequest);
        Restaurant storedRestaurant = this.updateRestaurantUseCase.execute(id, restaurant);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.restaurantMapper.domainToUpdateRestaurantResponse(storedRestaurant));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/v1/restaurant/{id}")
    public ResponseEntity<DeleteRestaurantResponse> delete(@PathVariable Long id){
        this.deleteRestaurantUseCase.execute(id);
        return ResponseEntity.ok(new DeleteRestaurantResponse("O Restaurante foi excluído com sucesso!"));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'CULINARY_JUDGE')")
    @GetMapping("/v1/restaurant")
    public ResponseEntity<List<GetRestaurantResponse>> getAllActive(){
        List<GetRestaurantResponse> restaurantResponses = this.findActiveRestaurantsUseCase.execute()
                .stream().map(this.restaurantMapper::domainToGetRestaurantResponse).toList();
        return ResponseEntity.ok(restaurantResponses);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'CULINARY_JUDGE')")
    @GetMapping("/v1/restaurant/paginate")
    public ResponseEntity<Page<GetRestaurantResponse>> getAllActivePaginate(@RequestParam Integer page, @RequestParam Integer items){
        Page<GetRestaurantResponse> restaurantResponses = this.findActiveRestaurantsUseCase.execute(page, items)
                .map(this.restaurantMapper::domainToGetRestaurantResponse);
        return ResponseEntity.ok(restaurantResponses);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'CULINARY_JUDGE')")
    @GetMapping("/v1/restaurant/all")
    public ResponseEntity<List<GetRestaurantResponse>> getAll(){
        List<GetRestaurantResponse> restaurantResponses = this.findAllRestaurantsUseCase.execute()
                .stream().map(this.restaurantMapper::domainToGetRestaurantResponse).toList();
        return ResponseEntity.ok(restaurantResponses);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'CULINARY_JUDGE')")
    @GetMapping("/v1/restaurant/all/paginate")
    public ResponseEntity<Page<GetRestaurantResponse>> getAll(@RequestParam Integer page, @RequestParam Integer items){
        Page<GetRestaurantResponse> restaurantResponses = this.findAllRestaurantsUseCase.execute(page, items)
                .map(this.restaurantMapper::domainToGetRestaurantResponse);
        return ResponseEntity.ok(restaurantResponses);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'CULINARY_JUDGE')")
    @GetMapping("/v1/restaurant/allByCategory")
    public ResponseEntity<List<GetRestaurantResponse>> getAllByCategory(@RequestParam Category category) {
        List<GetRestaurantResponse> restaurantResponses = this.findAllRestaurantsByCategoryAndStatus.execute(category)
                .stream().map(this.restaurantMapper::domainToGetRestaurantResponse).toList();
        return ResponseEntity.ok(restaurantResponses);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'CULINARY_JUDGE')")
    @GetMapping("/v1/restaurant/allByCategory/paginate")
    public ResponseEntity<Page<GetRestaurantResponse>> getAllByCategory(@RequestParam Integer page, @RequestParam Integer items, Category category){
        Page<GetRestaurantResponse> restaurantResponses = this.findAllRestaurantsByCategoryAndStatus.execute(page, items, category)
                .map(this.restaurantMapper::domainToGetRestaurantResponse);
        return ResponseEntity.ok(restaurantResponses);
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'CULINARY_JUDGE')")
    @GetMapping("/v1/restaurant/{id}")
    public ResponseEntity<GetRestaurantResponse> getById(@PathVariable Long id){
        return ResponseEntity.ok(this.restaurantMapper.domainToGetRestaurantResponse(this.findRestaurantUseCase.execute(id)));
    }
}
