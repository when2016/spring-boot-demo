package com.wanghongen.demo.kafka;

import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 * @author wanghongen
 * @date 7/28/18 10:43 AM
 */
public class KafkaProducerExample {
  public static void main(String[] args) {
    Properties props = new Properties();
    props.put("zk.connect","localhost:2181");
    props.put("metadata.broker.list","localhost:9092,localhost:9093,localhost:9094");
    props.put("serializer.class","kafka.serializer.StringEncoder");

    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

    Producer<String, String> producer = new KafkaProducer<>(props);
    for(int i = 0; i < 100; i++)
      producer.send(new ProducerRecord<>("test", Integer.toString(i), Integer.toString(i)));

    producer.close();
  }
}
