package com.example.cardapio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cardapio.entity.Food;

public interface FoodRepository extends JpaRepository<Food, Integer> {

}
