package com.project.messagequeue.controller;

import com.project.messagequeue.model.PostRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.LogManager;
import java.util.logging.Logger;

@RequestMapping("/messagequeue")
@Controller
public class MessageQueueController {

    Logger logger = LogManager.getLogManager().getLogger("MessageQueueController");

    @Autowired
    KafkaTemplate<String, PostRequestBody> kafkaTemplate;

    @PostMapping("/send/")
    public ResponseEntity<PostRequestBody> sendMessage(@RequestBody PostRequestBody postRequestBody){

        logger.info(postRequestBody.getType());

        try {
            kafkaTemplate.send("messagequeue", postRequestBody);

            return ResponseEntity.ok(postRequestBody);
        }catch(Exception ex){
            return ResponseEntity.badRequest().build();
        }
    }
}
