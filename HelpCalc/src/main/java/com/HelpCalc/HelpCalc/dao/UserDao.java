package com.HelpCalc.HelpCalc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.HelpCalc.HelpCalc.dto.User;
import com.HelpCalc.HelpCalc.repo.UserRepo;

@Repository
public class UserDao {
	
	@Autowired
	UserRepo ur;
	
	public User saveUser(User user) {
		return ur.save(user);
	}
	
	public User findByUsername(String username) {
		return ur.findByUsername(username);
	}

}
