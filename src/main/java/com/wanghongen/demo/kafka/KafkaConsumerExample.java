package com.wanghongen.demo.kafka;

import java.util.Arrays;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

/**
 * @author wanghongen
 * @date 7/28/18 10:44 AM
 */
public class KafkaConsumerExample {
  public static void main(String[] args) {
    Properties props = new Properties();
    props.put("zk.connect","localhost:2181");
    props.put("metadata.broker.list","localhost:9092,localhost:9093,localhost:9094");
    props.put("serializer.class","kafka.serializer.StringEncoder");
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
    consumer.subscribe(Arrays.asList("test"));
    while (true) {
      ConsumerRecords<String, String> records = consumer.poll(100);
      for (ConsumerRecord<String, String> record : records)
        System.out.printf("offset = %d, key = %s, value = %s\n", record.offset(), record.key(), record.value());
    }
  }
}
