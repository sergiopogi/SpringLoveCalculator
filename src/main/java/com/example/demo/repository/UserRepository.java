package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.History;
import com.example.demo.entities.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {

	public List<History> findHistoryById(int id);
	
	

	Users findUsersByUsername(String username);
	Users findByUsername(String name);
}
