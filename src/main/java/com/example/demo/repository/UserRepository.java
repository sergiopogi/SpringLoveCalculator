package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.History;
import com.example.demo.entities.Users;
@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

	public List<History> findHistoryById(int id);
	
	
	Users findByUsername(String name);
}
