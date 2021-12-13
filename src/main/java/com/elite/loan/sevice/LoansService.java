package com.elite.loan.sevice;

import java.util.List;

import com.elite.loan.model.Loan;


public interface LoansService {

	Loan loanInsert(Loan loans);
	
	Loan loanUpdate(Loan loans);

	List<Loan> getAllLoan();

	List<Loan> getLoanByCustomerId(String customer_id);

	Loan getByLoanAndCustomerId(String customer_id,Long loan_id);
	
	Loan getByLoanById(Long loan_id);
	
	List<Loan> getByLoanIdAndCustomerId(String customer_id,Long loan_id);
}

