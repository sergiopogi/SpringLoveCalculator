package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.History;
import com.example.demo.repository.HistoryRepository;

@Service
public class HistoryServiceImpl implements HistoryService {

	@Autowired
	HistoryRepository historyRepository;
	
	@Override
	public void saveHistory(History history) {
	
		historyRepository.save(history);

	}

	@Override
	public List<History> findHistoryByUsers(int id) {
		
		return historyRepository.findHistoryByUsers(id);
	}

	@Override
	public void deleteById(int id) {
		historyRepository.deleteById(id);

	}

}
