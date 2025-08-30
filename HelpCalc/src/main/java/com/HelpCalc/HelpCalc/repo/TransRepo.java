package com.HelpCalc.HelpCalc.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.HelpCalc.HelpCalc.dto.Transactions;

public interface TransRepo extends JpaRepository<Transactions, Long>{
	
}
