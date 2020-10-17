package com.example.kafkaProducer.controllers;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kafkaProducer.model.PublisherModel;

@RestController
@RequestMapping("/kafka")
public class KafkaProducerController {

  @Autowired
  KafkaTemplate<String, Object> kafkaTemplate;

  public final String TOPIC = "quickstart-events";

  @PostMapping("/producer")
  public String producer() {

    PublisherModel publisherModel = PublisherModel.builder()
        .storeId("10001")
        .voucherCode(getVoucherCodes())
        .build();
    this.kafkaTemplate.send(TOPIC, publisherModel);
    return "Published Successfully";
  }

  @PostMapping("/producerWithKey")
  public String producerWithKey() {

    PublisherModel publisherModel = PublisherModel.builder()
        .storeId("10001")
        .voucherCode(getVoucherCodes())
        .build();
    this.kafkaTemplate.send(TOPIC, "merchantVoucher", publisherModel);
    return "Published Successfully";
  }

  private static Set<String> getVoucherCodes() {
    Set<String> voucherCodes = new HashSet<>();
    voucherCodes.add("MV-00001");
    voucherCodes.add("MV-00002");
    voucherCodes.add("MV-00003");
    voucherCodes.add("MV-00004");
    voucherCodes.add("MV-00005");
    voucherCodes.add("MV-00006");
    voucherCodes.add("MV-00007");
    voucherCodes.add("MV-00008");
    voucherCodes.add("MV-00009");
    voucherCodes.add("MV-00010");
    return voucherCodes;
  }

}
