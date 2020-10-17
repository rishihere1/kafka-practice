package com.example.kafkaProducer.model;

import java.io.Serializable;
import java.util.Set;

import lombok.Builder;
import lombok.Data;

/**
 * @author rishi - created on 16/08/20
 **/
@Data
@Builder
public class PublisherModel implements Serializable {
  String storeId;
  Set<String> voucherCode;
}
