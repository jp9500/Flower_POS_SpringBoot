package com.HelpCalc.HelpCalc.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.HelpCalc.HelpCalc.dto.Expense;

public interface ExpenseRepo extends JpaRepository<Expense, Long>{

	Expense findByExpenseName(String expenseName);

}
