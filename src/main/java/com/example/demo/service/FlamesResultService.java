package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class FlamesResultService {

	public String result(String name , String crushname) {
		
		int nameAndCnameLength = (name + crushname).length();
		
		
		return finalResult(nameAndCnameLength);
		
	}
	
	private String finalResult(int nclength) {
		
		String result = null;
		
		switch(nclength % 5) {
		case 1 :
			result = FlamesRepo.friend;
			break;
		case 2:
			result = FlamesRepo.love;
			break;
		case 3:
			result = FlamesRepo.affair;
			break;
		case 4 :
			result = FlamesRepo.marriage;
			break;
		default  :
			result = FlamesRepo.enemy;
			break;
			
		}
		
		return result;
	}
}
