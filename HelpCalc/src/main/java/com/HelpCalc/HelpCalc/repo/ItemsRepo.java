package com.HelpCalc.HelpCalc.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.HelpCalc.HelpCalc.dto.Items;

public interface ItemsRepo extends JpaRepository<Items, Long> {

	Items findByItemName(String itemName);

}
