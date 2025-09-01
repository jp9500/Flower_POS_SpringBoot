package com.HelpCalc.HelpCalc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HelpCalc.HelpCalc.config.ResponseStructure;
import com.HelpCalc.HelpCalc.dto.User;
import com.HelpCalc.HelpCalc.service.UserService;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "https://localhost"})
@RequestMapping
public class UserController {

	@Autowired
	UserService ser;
	
	@PostMapping("signup")
	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody User user) {
		return ser.saveUser(user);
	}
	
	@PostMapping("login")
	public ResponseEntity<ResponseStructure<User>> loginUser(@RequestBody User user) {
		return ser.loginUser(user);
	}
}
