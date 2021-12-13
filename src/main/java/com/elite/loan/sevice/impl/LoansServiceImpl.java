package com.elite.loan.sevice.impl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elite.loan.dao.LoansDAO;
import com.elite.loan.model.Loan;
import com.elite.loan.sevice.LoansService;
@Service
public class LoansServiceImpl implements LoansService {
	@Autowired
	private LoansDAO loansDAO;
	@Override
	public Loan loanInsert(Loan loans) {
		return loansDAO.save(loans);
	}
	@Override
	public List<Loan> getAllLoan() {
		return loansDAO.findAll();
	}

	@Override
	public List<Loan> getLoanByCustomerId(String customer_id) {
		
		return loansDAO.getLoanByCustomerId(customer_id);
		
		
	}

	@Override
	public Loan getByLoanAndCustomerId(String customer_id, Long loan_id) {
		
		Optional<Loan> daata=loansDAO.getByLoanAndCustomerId( customer_id,loan_id);
		 return daata.get();
	}
	@Override
	public List<Loan> getByLoanIdAndCustomerId(String customer_id, Long loan_id) {
		// TODO Auto-generated method stub
		return loansDAO.getByLoanIdAndCustomerId(customer_id,loan_id);
	}
	@Override
	public Loan loanUpdate(Loan loans) {
		return loansDAO.save(loans);
	}
	@Override
	public Loan getByLoanById(Long loan_id) {
		return loansDAO.findById(loan_id).get();
	}
	
}

