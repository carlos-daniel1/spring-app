package com.example.cardapio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.cardapio.entity.user.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	UserDetails findByLogin(String login);
}
