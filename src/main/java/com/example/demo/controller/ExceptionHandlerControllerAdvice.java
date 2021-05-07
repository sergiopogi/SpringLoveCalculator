package com.example.demo.controller;

import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public String inputException(RedirectAttributes redirectAttributes) {
		
		redirectAttributes.addFlashAttribute("conditionerror", "*You must accept to our condition");
		
		return "redirect:/sign-up";
	}
}
