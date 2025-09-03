package com.HelpCalc.HelpCalc.repo;


import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.HelpCalc.HelpCalc.dto.Transactions;

public interface TransRepo extends JpaRepository<Transactions, Long>{

	@Query(value = "SELECT ROUND(SUM(subtotal)),ROUND(SUM(grand_total)),ROUND(SUM(commission_total)) FROM transactions "
			+ "WHERE DATE(transaction_date) BETWEEN :from AND :to ", nativeQuery = true)
	List<Object[]> overAllSalesInMonthly(String from, String to);
	
	@Query(value = "SELECT item_name,SUM(total)  "
			+ "FROM `transaction_smry` a  "
			+ "INNER JOIN `transactions` c ON a.transaction_id=c.transaction_id "
			+ "INNER JOIN `items` b ON a.item_id=b.item_id AND c.userid=b.userid "
			+ "WHERE DATE(transaction_date) BETWEEN :from AND :to "
			+ "GROUP BY item_name", nativeQuery = true)
	List<Object[]> itemWiseOverAllSales(String from, String to);
	
	@Query(value = "SELECT a.transaction_id AS id,DATE(transaction_date) AS DATE, COUNT(item_id) AS items, grand_total AS amount,commission_total as commTotal "
			+ " FROM transactions a "
			+ " INNER JOIN `user` c ON a.userid=c.userid "
			+ " INNER JOIN `transaction_smry` b ON a.transaction_id=b.transaction_id "
			+ " WHERE DATE(transaction_date) BETWEEN :from AND :to "
			+ " GROUP BY a.transaction_id "
			+ " ORDER BY DATE(transaction_date) DESC ", nativeQuery = true)
	List<Object[]> findAllTransactions(String from, String to);
	
	@Query(value = "SELECT b.item_name,a.quantity,a.price,a.total,b.uom "
			+ " FROM `transaction_smry` a "
			+ " INNER JOIN `transactions` c ON a. transaction_id=c.transaction_id "
			+ " INNER JOIN `items` b ON a.item_id=b.item_id and c.userid=b.userid "
			+ " WHERE a.transaction_id= :id ", nativeQuery = true)
	List<Object[]> findTransactionDetails(Long id);
}
