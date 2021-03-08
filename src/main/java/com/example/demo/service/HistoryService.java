package com.example.demo.service;

import java.util.List;

import com.example.demo.entities.History;


public interface HistoryService {

	public void saveHistory(History history);
	public List<History>  findHistoryByUsers(int id);
	public void deleteById(int id);

}
