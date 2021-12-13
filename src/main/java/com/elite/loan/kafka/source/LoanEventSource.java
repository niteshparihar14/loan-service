package com.elite.loan.kafka.source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

import com.elite.loan.kafka.channel.TransactionChannel;
import com.elite.loan.model.Loan;
import com.elite.loan.model.LoanRequestEvent;

@Component
public class LoanEventSource {

	@Autowired
	private TransactionChannel transactionChannel;

	public void publishLoanEvent(Loan loan) {

		LoanRequestEvent message = new LoanRequestEvent(loan.getLoan_id(),
				Long.valueOf(loan.getAccount_id()), null);

		MessageChannel messageChannel = transactionChannel.outboundTransation();
		messageChannel.send(MessageBuilder.withPayload(message)
				.setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON).build());
	}
}
