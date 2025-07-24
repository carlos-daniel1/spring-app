package com.example.cardapio.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cardapio.DTO.FoodRequestDTO;
import com.example.cardapio.DTO.FoodResponseDTO;
import com.example.cardapio.DTO.MessageResponseDTO;
import com.example.cardapio.entity.Food;
import com.example.cardapio.repository.FoodRepository;

@RestController
@RequestMapping("/api/food")
public class FoodController {
	
	private FoodRepository repository;

	public FoodController(FoodRepository repository) {
		this.repository = repository;
	}

	@GetMapping
	public List<FoodResponseDTO> getAll() {
		
		List<FoodResponseDTO> foodList = repository.findAll().stream()
				.map(FoodResponseDTO::new)
				.toList();
		return foodList;
	}
	
	@GetMapping("{id}")
	public ResponseEntity getOne(@PathVariable("id") Integer id) {
		Optional<Food> checkFood = repository.findById(id);
		
		if (checkFood.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body(new MessageResponseDTO("Food not found"));
	    }
		
		Food food = checkFood.get();
		return ResponseEntity.status(HttpStatus.OK).body(food);
	}
	
	@PostMapping
	public ResponseEntity<Food> addFood(@RequestBody FoodRequestDTO data) {
		Food foodData = new Food(data);
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(foodData));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<MessageResponseDTO> deleteFood(@PathVariable("id") Integer id) {
		repository.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponseDTO("Food deleted"));
	}
	
	@PutMapping("{id}")
	public ResponseEntity updateFood(@PathVariable("id") Integer id, 
			@RequestBody FoodRequestDTO updatedFoodDTO) {
		
		Optional<Food> checkFood = repository.findById(id);
		
		if (checkFood.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body(new MessageResponseDTO("Food not found"));
	    }
		
		Food food = checkFood.get();

	    food.setTitle(updatedFoodDTO.title());
	    food.setImage(updatedFoodDTO.image());
	    food.setPrice(updatedFoodDTO.price());
		
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(food));
	}
}






