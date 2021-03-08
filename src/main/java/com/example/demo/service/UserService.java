package com.example.demo.service;

import java.util.List;

import com.example.demo.entities.Users;

public interface UserService {

	
	Users save(Users user);
	List<Users> getAllUsers();
	
	Users findByUsername(String username);
	boolean existUser(String username);
}
