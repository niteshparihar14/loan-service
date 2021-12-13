package com.elite.loan.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoanRequestEvent {

	private Long loanId; 
	
	private Long accountId;
	
	private Long customerId;
	
}
