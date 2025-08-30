package com.HelpCalc.HelpCalc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.HelpCalc.HelpCalc.config.ResponseStructure;
import com.HelpCalc.HelpCalc.dao.UserDao;
import com.HelpCalc.HelpCalc.dto.User;

@Service
public class UserService {
	
	@Autowired
	UserDao dao;
	
	public ResponseEntity<ResponseStructure<User>> saveUser(User user) {
		ResponseStructure<User> response = new ResponseStructure<>();
		if(user == null || user.getUsername() == null || user.getPassword() == null) {
			response.setMessage("Invalid user data");
			response.setStatusCode(400);
			return ResponseEntity.badRequest().body(response);
		}
		if(dao.findByUsername(user.getUsername()) != null) {
			response.setMessage("Username already exists");
			response.setStatusCode(409);
			return ResponseEntity.status(409).body(response);
		}
		User savedUser = dao.saveUser(user);
		if(savedUser != null) {
			response.setMessage("User saved successfully");
			response.setStatusCode(201);
			response.setData(savedUser);
			return ResponseEntity.status(201).body(response);
		} else {
			response.setMessage("Failed to save user");
			response.setStatusCode(500);
			return ResponseEntity.status(500).body(response);
		}
	}
	
	public ResponseEntity<ResponseStructure<User>> loginUser(User user) {
		ResponseStructure<User> response = new ResponseStructure<>();
		if(user == null || user.getUsername() == null || user.getPassword() == null) {
			response.setMessage("Invalid user data");
			response.setStatusCode(400);
			return ResponseEntity.badRequest().body(response);
		}
		User exuser = dao.findByUsername(user.getUsername());
		if(exuser != null) {
			if(exuser.getUsername().equalsIgnoreCase(user.getUsername()) && 
			   exuser.getPassword().equals(user.getPassword())) {
				response.setMessage("Login successfully");
				response.setStatusCode(200);
				response.setData(exuser);
				return ResponseEntity.ok(response);
			} else {
				response.setMessage("Invalid username or password");
				response.setStatusCode(401);
				return ResponseEntity.status(401).body(response);
			}
		} else {
			response.setMessage("User not found");
			response.setStatusCode(404);
			return ResponseEntity.status(404).body(response);
		}
	}
	
}
