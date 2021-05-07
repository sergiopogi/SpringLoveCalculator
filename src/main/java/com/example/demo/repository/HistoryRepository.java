package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.History;

@Repository
public interface HistoryRepository extends JpaRepository<History, Integer> {

	public List<History> findByUserid(int id);
	public void deleteById(int id);
	
	
	
}
