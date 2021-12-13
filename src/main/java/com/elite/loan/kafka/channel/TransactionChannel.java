package com.elite.loan.kafka.channel;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

@SuppressWarnings("deprecation")
public interface TransactionChannel {

	String OUTPUT = "loan-out";

	@Output(OUTPUT)
	MessageChannel outboundTransation();

}
