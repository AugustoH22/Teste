package com.adesp.festival.dishes.infrastructure.controllers;

import com.adesp.festival.dishes.application.dtos.request.CreateDishRequest;
import com.adesp.festival.dishes.application.dtos.request.UpdateDishRequest;
import com.adesp.festival.dishes.application.dtos.response.CreateDishResponse;
import com.adesp.festival.dishes.application.dtos.response.DeleteDishResponse;
import com.adesp.festival.dishes.application.dtos.response.FindDishResponse;
import com.adesp.festival.dishes.application.dtos.response.UpdateDishResponse;
import com.adesp.festival.dishes.application.usecases.*;
import com.adesp.festival.dishes.domain.entities.Dish;
import com.adesp.festival.dishes.infrastructure.mappers.DishMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DishController {

    private final CreateDishUseCase createDishUseCase;
    private final UpdateDishUseCase updateDishUseCase;
    private final DeleteDishUseCase deleteDishUseCase;
    private final FindDishUseCase findDishUseCase;
    private final FindActiveDishesUseCase findActiveDishesUseCase;
    private final FindAllDishesUseCase findAllDishesUseCase;
    private final DishMapper dishMapper;

    public DishController(CreateDishUseCase createDishUseCase, UpdateDishUseCase updateDishUseCase, DeleteDishUseCase deleteDishUseCase, FindDishUseCase findDishUseCase, FindActiveDishesUseCase findActiveDishesUseCase, FindAllDishesUseCase findAllDishesUseCase, DishMapper dishMapper) {
        this.createDishUseCase = createDishUseCase;
        this.updateDishUseCase = updateDishUseCase;
        this.deleteDishUseCase = deleteDishUseCase;
        this.findDishUseCase = findDishUseCase;
        this.findActiveDishesUseCase = findActiveDishesUseCase;
        this.findAllDishesUseCase = findAllDishesUseCase;
        this.dishMapper = dishMapper;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/v1/dish")
    public ResponseEntity<CreateDishResponse> post(@RequestBody CreateDishRequest createDishRequest){
        Dish dish = this.dishMapper.createDishRequestToDomain(createDishRequest);
        Dish storedDish = this.createDishUseCase.execute(dish);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.dishMapper.domainToCreateDishResponse(storedDish));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/v1/dish/{id}")
    public ResponseEntity<UpdateDishResponse> put(@PathVariable Long id, @RequestBody UpdateDishRequest updateDishRequest){
        Dish dish = this.dishMapper.updateDishRequestToDomain(updateDishRequest);
        Dish storedDish = this.updateDishUseCase.execute(id, dish);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.dishMapper.domainToUpdateDishResponse(storedDish));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/v1/dish/{id}")
    public ResponseEntity<DeleteDishResponse> delete(@PathVariable Long id){
        this.deleteDishUseCase.execute(id);
        return ResponseEntity.ok(new DeleteDishResponse("O Prato foi excluído com sucesso!"));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'CULINARY_JUDGE')")
    @GetMapping("/v1/dish/all")
    public ResponseEntity<List<FindDishResponse>> getAll(){
        List<Dish> dishes = this.findAllDishesUseCase.execute();
        List<FindDishResponse> dishResponses = dishes.stream()
                .map(this.dishMapper::domainToFindDishResponse)
                .toList();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(dishResponses);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'CULINARY_JUDGE')")
    @GetMapping("/v1/dish/all/paginate")
    public ResponseEntity<Page<FindDishResponse>> getAllPaginate(@RequestParam("page") Integer page, @RequestParam("items") Integer items){
        Page<Dish> dishes = this.findAllDishesUseCase.execute(page, items);
        Page<FindDishResponse> dishResponses = dishes
                .map(this.dishMapper::domainToFindDishResponse);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(dishResponses);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'CULINARY_JUDGE')")
    @GetMapping("/v1/dish")
    public ResponseEntity<List<FindDishResponse>> getAllActive(){
        List<Dish> dishes = this.findActiveDishesUseCase.execute();
        List<FindDishResponse> dishResponses = dishes.stream()
                .map(this.dishMapper::domainToFindDishResponse)
                .toList();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(dishResponses);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'CULINARY_JUDGE')")
    @GetMapping("/v1/dish/paginate")
    public ResponseEntity<Page<FindDishResponse>> getAllActivePaginate(@RequestParam("page") Integer page, @RequestParam("items") Integer items){
        Page<Dish> dishes = this.findActiveDishesUseCase.execute(page, items);
        Page<FindDishResponse> dishResponses = dishes
                .map(this.dishMapper::domainToFindDishResponse);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(dishResponses);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'CULINARY_JUDGE')")
    @GetMapping("v1/dish/{id}")
    public ResponseEntity<FindDishResponse> getById(@PathVariable Long id){
        Dish dish = this.findDishUseCase.execute(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.dishMapper.domainToFindDishResponse(dish));
    }
}
