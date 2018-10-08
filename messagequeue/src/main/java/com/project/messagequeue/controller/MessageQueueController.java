package com.project.messagequeue.controller;

import com.project.messagequeue.model.PostRequestBody;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;

@RequestMapping("/messagequeue")
@Controller
public class MessageQueueController {

    Logger logger = LoggerFactory.getLogger(MessageQueueController.class);

    @Autowired
    KafkaTemplate<String, PostRequestBody> kafkaTemplate;

    @PostMapping("/send/")
    public ResponseEntity sendMessage(@RequestBody PostRequestBody postRequestBody){

        logger.info(postRequestBody.getType() + ": " + postRequestBody.getMessage());

        try {
            kafkaTemplate.send("messagequeue", postRequestBody);

            return ResponseEntity.ok(postRequestBody);
        }catch(Exception ex){
            return ResponseEntity.badRequest().body(ex.getMessage() + "cause: " + ex.getCause());
        }
    }
}
