package com.example.cardapio.controller;


import java.util.List;

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
import com.example.cardapio.service.FoodService;



@RestController
@RequestMapping("/api/food")
public class FoodController {
	
	private FoodService foodServices;

	public FoodController(FoodService foodServices) {
		this.foodServices = foodServices;
	}

	@GetMapping
	List<FoodResponseDTO> getAll() {
		return foodServices.getAll();
	}
	
	@GetMapping("{id}")
	 ResponseEntity getOne(@PathVariable("id") Integer id) {
		return foodServices.getOne(id);
	}
	
	@PostMapping
	ResponseEntity<Food> create(@RequestBody FoodRequestDTO data) {
		return foodServices.create(data);
	}
	
	@PutMapping("{id}")
	ResponseEntity update(@PathVariable("id") Integer id, 
			@RequestBody FoodRequestDTO data) {
		return foodServices.update(id, data);
	}
	
	@DeleteMapping("{id}")
	ResponseEntity<MessageResponseDTO> delete(@PathVariable("id") Integer id) {
		return foodServices.delete(id);
	}
	
}






