package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entities.History;
import com.example.demo.entities.Users;
import com.example.demo.repository.HistoryRepository;
import com.example.demo.repository.UserRepository;

@SpringBootTest
class SpringbootpractApplicationTests {
	
	@Autowired
	private UserRepository repo;
	@Autowired
	private HistoryRepository hrepo;
	@Autowired
	private EntityManager em;

//	@Test
//	void contextLoads() {
//	}

//	@Test
//	public void addd() {
//		Users u = new Users();
//		u.setId(4);
//		u.setUsername("mangnay");
//		u.setPassword("pogi");
//		Users saves = repo.save(u);
//		
//		Users existUser = em.find(Users.class, saves.getId());
//		
//		assertThat(existUser.getUsername()).isEqualTo(u.getUsername());
//		
//	}
//	@Test
	public void histo() {
		
		History h = new History();
	//	h.setUser_id(2);
		//h.setId(2);
		
		h.setCrushname("Harden");
		//History saves = 
				hrepo.save(h);
		//History existUser = em.find(History.class, saves.getUser_id());
		
		//assertThat(existUser.getName()).isEqualTo(h.getName());
		
	}
//	@Test
//	public void s() {
//		
//		Users u = new  Users();
//		u.setId(2);
//		
//	List<History> findHistoryByUsersId = hrepo.findHistoryById(u.getId());
//	
//	for(History h : findHistoryByUsersId )
//	{
//		System.out.println(h);
//	}
//	
//	}
//	@Test
//	public void testFindUserByUsername () {
//		String username = "mangnay123";
//		
//		Users findByUsername = repo.findUsersByUsername(username);
//		assertThat(findByUsername).isNotNull();
//	}
	@Test
	public void findByUser() {
		//Users findByUsername = repo.findByUsername("mangnay");
			System.out.println(repo.findByUsername("mangnay"));
	//	System.out.println(findByUsername);
	}
	
}
