package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Users;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	public Users save(Users user) {
    	user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

		
	public List<Users> getAllUsers() {
		List<Users> findAll = userRepository.findAll();	
		return findAll;
	}

	public boolean existUser(String username) {
	
		Users findByUsername = findByUsername(username);
		if(findByUsername == null)
		{
			return false;
		}
		return true;
	}

	
	public Users findByUsername(String username) {
		
		return userRepository.findByUsername(username);
	}

	
	

}
