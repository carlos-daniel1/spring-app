package com.example.cardapio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.cardapio.DTO.FoodRequestDTO;
import com.example.cardapio.DTO.FoodResponseDTO;
import com.example.cardapio.DTO.MessageResponseDTO;
import com.example.cardapio.entity.Food;
import com.example.cardapio.repository.FoodRepository;

@RestController
@RequestMapping("food")
public class FoodController {
	
	@Autowired
	private FoodRepository repository;

	@GetMapping
	public List<FoodResponseDTO> getAll() {
		
		List<FoodResponseDTO> foodList = repository.findAll().stream()
				.map(FoodResponseDTO::new)
				.toList();
		
		return foodList;
	}
	
	@PostMapping
	public ResponseEntity addFood(@RequestBody FoodRequestDTO data) {
		Food foodData = new Food(data);
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(foodData));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity deleteFood(@PathVariable(value = "id") Integer id) {
		repository.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponseDTO("Food deleted"));
	}
}






