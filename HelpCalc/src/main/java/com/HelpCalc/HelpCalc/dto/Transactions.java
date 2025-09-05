package com.HelpCalc.HelpCalc.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Transactions {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int transaction_id;
	private double subtotal;
	private double commission_total;
	private double grand_total;
	
	@CreationTimestamp
	private LocalDateTime  transactionDate;
	private int userid;
	private int comm_perc;
	
	@OneToMany(cascade = CascadeType.ALL , mappedBy = "transactions")
	private List<Transaction_smry> smry;
	
	private String type;
	
	
	public LocalDateTime  getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDateTime  transactionDate) {
		this.transactionDate = transactionDate;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getComm_perc() {
		return comm_perc;
	}

	public void setComm_perc(int comm_perc) {
		this.comm_perc = comm_perc;
	}
	
	public int getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public double getCommission_total() {
		return commission_total;
	}

	public void setCommission_total(double commission_total) {
		this.commission_total = commission_total;
	}

	public double getGrand_total() {
		return grand_total;
	}

	public void setGrand_total(double grand_total) {
		this.grand_total = grand_total;
	}
	
	public List<Transaction_smry> getSmry() {
		return smry;
	}
	
	public void setSmry(List<Transaction_smry> smry) {
		this.smry = smry;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


}
