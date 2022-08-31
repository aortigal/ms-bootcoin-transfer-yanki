package com.bank.msbootcointransferyankibatch;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
		"spring.kafka.bootstrap-servers=127.0.0.1:9092",
		"kafka.topic.yanki=bootcoin-yanki-topic",
		"spring.kafka.consumer.group-id=bootcoin-yanki_id",
		"kafka.topic.transactionYanki=transactionYanki",
		"kafka.topic.transactionBootCoin=transactionBootCoin",
		"kafka.bootstrapAddress=127.0.0.1:9092",
})
class MsBootcoinTransferYankibatchApplicationTests {

	@Test
	void contextLoads() {
	}

}
