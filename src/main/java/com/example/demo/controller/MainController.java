package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.exceptions.TemplateInputException;

import com.example.demo.customuserdetails.CustomUserDetails;
import com.example.demo.entities.History;
import com.example.demo.entities.Users;
import com.example.demo.service.FlamesResultService;
import com.example.demo.service.HistoryService;
import com.example.demo.service.UserService;

@Controller
public class MainController {
	
	@Autowired
	private HistoryService historyRepo;
	@Autowired
	private UserService userService;
	@Autowired
	FlamesResultService resultService;
	
	@GetMapping("/")
	public String landingView() {
		return "LoginView";
	}
	
	@GetMapping("/login")
	public String loginView () {
			
		return "LoginView";
	}
	@RequestMapping("/sign-up")
	public String signup(Users users , Model model) {

		model.addAttribute("users" , users);
		return "RegisterView";
	}
	@PostMapping("/register")
	public String register (@Valid Users users , BindingResult result , @RequestParam("cbox")  boolean cb, RedirectAttributes redirectAttributes , Model model) {
		
		System.out.println("QQQ" +cb);
		
		
		if(result.hasErrors())
		{
			
			return "RegisterView";
		}else if(userService.existUser(users.getUsername()))
		{

			result.addError(new FieldError("users", "username", "Username is already taken !"));
			return "RegisterView";
		}
		System.out.println("QQQ" +cb);
			userService.save(users);
			redirectAttributes.addFlashAttribute("message", "You successfully registered! You can now login");
			
			return "redirect:/login";
	
	}
	@RequestMapping("/mainview")
	public String mainView(@AuthenticationPrincipal CustomUserDetails user , History history , Model model , HttpSession session) {
		
		session.setAttribute("sessionid", user.getId());
		model.addAttribute("user" , user);
		model.addAttribute("history" , history);
		
		return "MainView";
	}
	
	
	
	@PostMapping("/submit")
	public String nameAndCnameResult(@Valid History history ,BindingResult results, Model model  , HttpSession session ) {
		
		if(results.hasErrors())
		{
			return "mainview";
		}
		
		String result = resultService.result(history.getName(), history.getCrushname());	
		History h = new History();
		h.setUserid((Integer) session.getAttribute("sessionid"));
		h.setName(history.getName());
		h.setCrushname(history.getCrushname());
		h.setResult(result);
		historyRepo.saveHistory(h);	
		session.setAttribute("history", h);
	
		return "redirect:/resultview";	
	}
	@GetMapping("/resultview")
	public String resultView(Model model ,HttpSession session) {
		
		model.addAttribute("history", session.getAttribute("history"));
		
		return "resultview";	
		
	}
	
	
	
	@GetMapping("/history")
	public String historyView(Model model , HttpSession sess) {
		
		List<History> findHistoryById = historyRepo.findHistoryByUsers((int) sess.getAttribute("sessionid"));
		
		model.addAttribute("listHistory" ,findHistoryById );
		
		return "historyview";
	}
	@RequestMapping(value = "/delete-history")
	public String deleteHistory(@RequestParam("id") Integer  id) {
    	
	
    	historyRepo.deleteById(id);
		return "redirect:/history";
	}
	

	


	

}
