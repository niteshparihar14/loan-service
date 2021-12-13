package com.elite.loan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elite.loan.kafka.source.LoanEventSource;
import com.elite.loan.model.Loan;
import com.elite.loan.model.LoanDeclineRequest;
import com.elite.loan.model.LoanResponse;
import com.elite.loan.sevice.LoansService;

@RestController
@RequestMapping("/api/v1/")
public class LoansController {
	@Autowired
	private LoansService loansService;

	@Autowired
	private LoanEventSource loanEventSource;

	/**
	 * Manish Kumar Verma
	 * 
	 * @param loans
	 * @return
	 */
	@PostMapping("/loans")
	public ResponseEntity<LoanResponse> loanData(@RequestBody Loan loans) {

		long intrestRate = 10;

		long balanceTenure = loans.getLoan_amount() * intrestRate * loans.getTenure() / 100;

		loans.setAccount_id(loans.getAccount_id());
		loans.setBalance_tenure(balanceTenure);
		loans.setAmount(loans.getLoan_amount() + balanceTenure);
		loans.setStatus("NEW");
		
		Loan loan = loansService.loanInsert(loans);
		
		if(loan == null) {
			throw new RuntimeException("Failed to process Loan request");
		}
		
		loanEventSource.publishLoanEvent(loan);
		
		LoanResponse response = new LoanResponse();
		response.setMessage("Success");
		response.setMessage("Loan request sent for processing ...");
		
		return ResponseEntity.ok().body(response);
	}
	
	@PostMapping("/loan/update")
	public Loan loanData(@RequestBody LoanDeclineRequest request) {
		Loan loan = loansService.getByLoanById(request.getLoanId());
		if(loan == null) {
			throw new RuntimeException("Loan not exist");
		}
		loan.setStatus(request.getStatus());
		loan.setCustomer_id(request.getCustomerId().toString());
		return loansService.loanUpdate(loan);
	}

	/**
	 * 
	 * @return
	 */
	@GetMapping("/all")
	public List<Loan> getLoan() {

		return loansService.getAllLoan();
	}

	/**
	 * 
	 * @param customerid
	 * @return
	 */
	@GetMapping("/{customer_id}/loans")
	public List<Loan> getLoanByCustomerId(@PathVariable("customer_id") String customer_id) {
		List<Loan> loanData = loansService.getLoanByCustomerId(customer_id);
		return loanData;

	}

	/**
	 * 
	 * @param loanid
	 * @return
	 */
	@GetMapping("/{customer_id}/loans/{loan_id}")
	public List<Loan> getByLoanIdAndCustId(@PathVariable("customer_id") String customer_id,
			@PathVariable("loan_id") Long loan_id) {
		List<Loan> loandataByLoanId = loansService.getByLoanIdAndCustomerId(customer_id, loan_id);
		return loandataByLoanId;
	}

	/**
	 * 
	 * 
	 * @param loans
	 * @param customerid
	 * @param loanid
	 * @return
	 */
	@PutMapping("update/{customer_id}/loans/{loan_id}")
	public Loan updateLoan(@RequestBody Loan loans, @PathVariable("customer_id") String customer_id,
			@PathVariable("loan_id") Long loan_id) {
		Loan update = loansService.getByLoanAndCustomerId(customer_id, loan_id);

		long intrestRate = 10;

		long tenure = 5;

		long balanceTenure = loans.getLoan_amount() * intrestRate * tenure / 100;

		update.setBalance_tenure(balanceTenure);
		update.setAmount(loans.getAmount() + balanceTenure);
		return loansService.loanInsert(update);
	}

}
