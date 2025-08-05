package com.example.cardapio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.cardapio.DTO.FoodRequestDTO;
import com.example.cardapio.DTO.FoodResponseDTO;
import com.example.cardapio.DTO.MessageResponseDTO;
import com.example.cardapio.entity.Food;
import com.example.cardapio.repository.FoodRepository;

@Service
public class FoodService {

	private FoodRepository repository;
	
	public FoodService(FoodRepository repository) {
		this.repository = repository;
	}

	
	public List<FoodResponseDTO> getAll() {
		
		List<FoodResponseDTO> foodList = repository.findAll().stream()
				.map(FoodResponseDTO::new)
				.toList();
		return foodList;
	}
	
	
	public ResponseEntity getOne(Integer id) {
		Optional<Food> checkFood = repository.findById(id);
		
		if (checkFood.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body(new MessageResponseDTO("Food not found"));
	    }
		
		Food food = checkFood.get();
		return ResponseEntity.status(HttpStatus.OK).body(food);
	}
	
	
	public ResponseEntity<Food> create(FoodRequestDTO data) {
		Food foodData = new Food(data);
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(foodData));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<MessageResponseDTO> delete(Integer id) {
		Optional<Food> checkFood = repository.findById(id);
		
		if (checkFood.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body(new MessageResponseDTO("Food not found"));
	    }
		
		repository.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponseDTO("Food deleted"));
	}
	
	public ResponseEntity update(Integer id, 
			FoodRequestDTO updatedFoodDTO) {
		
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
