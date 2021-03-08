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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.customuserdetails.CustomUserDetails;
import com.example.demo.entities.History;
import com.example.demo.entities.Users;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.FlamesResultService;
import com.example.demo.service.HistoryService;
import com.example.demo.service.UserService;

@Controller
public class MainController {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private HistoryService historyRepo;
	@Autowired
	private UserService userService;
	
	@Autowired
	FlamesResultService resultService;
	
	@RequestMapping("/login")
	public String loginView () {
			
		return "LoginView";
	}
	@RequestMapping("/sign-up")
	public String signup(Users users , Model model) {

		model.addAttribute("users" , users);
		return "RegistrationView";
	}
	@PostMapping("/register")
	public String register (@Valid Users users , BindingResult result , RedirectAttributes redirectAttributes) {
		
		
		if(userService.existUser(users.getUsername()) || result.hasErrors())
		{
			result.addError(new FieldError("users", "username", "*Username already exist"));
			return "RegistrationView";
		}
	
			redirectAttributes.addFlashAttribute("message", "You successfully registered! You can now login");
			userService.save(users);
			return "redirect:/login";
	
	}
	@RequestMapping("/")
	public String mainView(@AuthenticationPrincipal CustomUserDetails user , History history , Model model , HttpSession session) {
		
		session.setAttribute("sessionid", user.getId());
		model.addAttribute("user" , user);
		model.addAttribute("history" , history);
		
		return "index";
	}
	
	
	
	@PostMapping("/submit")
	public String nameAndCnameResult(@Valid History history ,BindingResult results, Model model  , HttpSession session ) {
		
		if(results.hasErrors())
		{
			return "index";
		}
		
		String result = resultService.result(history.getName(), history.getCrushname());	
		History h = new History();
		h.setUsers((Integer) session.getAttribute("sessionid"));
		h.setName(history.getName());
		h.setCrushname(history.getCrushname());
		h.setResult(result);
		historyRepo.saveHistory(h);	

		model.addAttribute("history" ,h);
	
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
	
	
//	@InitBinder
//	public void initBinder(WebDataBinder binders) {
//		//binders.registerCustomEditor(String.class, new StringTrimmerEditor(true));
//		binders.addValidators(new UsernameValidators());
//	}
//


	

}
