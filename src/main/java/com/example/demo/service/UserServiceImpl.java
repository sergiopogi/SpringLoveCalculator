package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Users;
import com.example.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public Users save(Users user) {
	user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override	
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

	@Override
	public Users findByUsername(String username) {
		
		return userRepository.findByUsername(username);
	}

	
	

}
