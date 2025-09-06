package com.HelpCalc.HelpCalc.repo;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.HelpCalc.HelpCalc.dto.Expense;

public interface ExpenseRepo extends JpaRepository<Expense, Long>{

	@Query(value = "SELECT * FROM expense WHERE userid = :userid ORDER BY expense_name ASC", nativeQuery = true)
	ArrayList<Expense> findAllByUserid(Long userid);
	
	Expense findByExpenseName(String expenseName);

}
