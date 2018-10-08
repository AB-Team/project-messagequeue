package com.project.messagequeue.config;

import org.springframework.kafka.support.serializer.JsonSerializer;
import com.project.messagequeue.model.PostRequestBody;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Bean
    public ProducerFactory<String, PostRequestBody> producerFactory(){

        Map<String, Object> configProps = new HashMap<>(4, 1);

        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<String, PostRequestBody>(configProps);
    }

    @Bean
    public KafkaTemplate<String, PostRequestBody> kafkaTemplate(){
        return new KafkaTemplate<>(producerFactory());
    }
}
