package com.project.messagequeue;

import com.project.messagequeue.model.PostRequestBody;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
public class MessagequeueApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessagequeueApplication.class, args);
	}

	@KafkaListener(topics = "messagequeue", groupId = "messageQueueGroup")
	public void listen(PostRequestBody message){
		System.out.println(message.getType() + ": " + message.getMessage());
	}
}
