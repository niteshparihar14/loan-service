package com.elite.loan.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Loans")
public class Loan implements Serializable {
	private static final long serialVersionUID = -214486977079866701L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long loan_id;
	
	@Column(name="account_id")
	private String account_id;
	
	@Column(name="customer_id")
	private String customer_id;
	
	@Column(name="loan_amount")
    private long loan_amount;
	
	
	@Column(name="tenure")
    private long tenure;
	
	@Column(name="balance_tenure")
    private long balance_tenure;
	
	@Column(name="status")
    private String status;
	
	@Column(name="amount")
	private long amount;

	@Column(name="intrest_rate")
    private long intrestRate;
	
	public long getLoan_id() {
		return loan_id;
	}

	public void setLoan_id(long loan_id) {
		this.loan_id = loan_id;
	}

	public String getAccount_id() {
		return account_id;
	}

	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}

	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

	
	public long getTenure() {
		return tenure;
	}

	public void setTenure(long tenure) {
		this.tenure = tenure;
	}

	public long getBalance_tenure() {
		return balance_tenure;
	}

	public void setBalance_tenure(long balance_tenure) {
		this.balance_tenure = balance_tenure;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public long getLoan_amount() {
		return loan_amount;
	}

	public void setLoan_amount(long loan_amount) {
		this.loan_amount = loan_amount;
	}

	public long getIntrestRate() {
		return intrestRate;
	}

	public void setIntrestRate(long intrestRate) {
		this.intrestRate = intrestRate;
	}
	
}
