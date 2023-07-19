package com.example.kafkaconnectapp.controller;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.kafkaconnectapp.account.AccountRequestDto;


@RestController
@RequestMapping("/api/kafka")
public class KafkaSampleController {

	final KafkaTemplate<String, Object> kafkaTemplate;

	Logger logger = LoggerFactory.getLogger(this.getClass());

	public KafkaSampleController(KafkaTemplate<String, Object> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	@PostMapping(value="")
	public String sendMessage(@RequestBody AccountRequestDto accountRequestDto) {
		logger.info("user sent message: " + accountRequestDto);
		this.kafkaTemplate.send("transaction-1", accountRequestDto);

		return "Hello World";
	}

	@KafkaListener(topics = "transaction-1")
	public void listener(@Payload AccountRequestDto accountRequestDto, ConsumerRecord<String, AccountRequestDto> cr) {
		logger.info("Topic [transaction-1] Received message from {} with {} PLN ", accountRequestDto.getHolder(), accountRequestDto.getFunds());
        logger.info(cr.toString());
	}
}
