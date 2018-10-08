package com.project.messagequeue.config;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.project.messagequeue.model.PostRequestBody;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Bean
    public ConsumerFactory<String, PostRequestBody> consumerFactory() {

        Map<String, Object> configProps = new HashMap<>(5, 1);

        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "messageQueueGroup");

        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        return new DefaultKafkaConsumerFactory<String, PostRequestBody>(configProps, new StringDeserializer(), new JsonDeserializer<>(PostRequestBody.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, PostRequestBody> kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String, PostRequestBody> factory =
                new ConcurrentKafkaListenerContainerFactory<String, PostRequestBody>();

        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
