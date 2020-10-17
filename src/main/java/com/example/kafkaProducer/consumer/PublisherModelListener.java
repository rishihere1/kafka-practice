package com.example.kafkaProducer.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.kafkaProducer.model.PublisherModel;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author rishi - created on 16/08/20
 **/
@Service
public class PublisherModelListener {

  @Autowired
  ObjectMapper objectMapper;

  @Async(value = "kafkaFixedThreadPool")
  @KafkaListener(topics = "quickstart-events", groupId = "group_Id")
  public void consume(ConsumerRecord<String, String> record) {
    try {
      PublisherModel publisherModel = objectMapper.readValue(record.value(), PublisherModel.class);
      System.out.println(record.key());
      System.out.println(publisherModel.getVoucherCode());
      System.out.println(publisherModel.getStoreId());
    } catch (Exception e) {
      System.out.println("Some problem occured while listening " + e);
    }
  }
}
