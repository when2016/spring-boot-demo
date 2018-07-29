package com.wanghongen.demo.kafka;

import java.io.IOException;
import java.util.Properties;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

/**
 * @author wanghongen
 * @date 7/29/18 10:33 AM
 */
public class SendDataToKafka {

  public static void main(String[] args) {
    SendDataToKafka s = new SendDataToKafka();
    try {
      s.send("kafkatest", "jack", "rose");
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public void send(String topic, String key, String data) throws IOException {
    Properties props = new Properties();
    props.put("metadata.broker.list", "localhost:9092");
    props.put("serializer.class", "kafka.serializer.StringEncoder");
    // key.serializer.class默认为serializer.class
    props.put("key.serializer.class", "kafka.serializer.StringEncoder");
    props.put("request.required.acks", "1");
    ProducerConfig config = new ProducerConfig(props);

    Producer<String, String> producer = new Producer<String, String>(config);
    for (int i = 0; i < 1000; i++) {
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      producer.send(new KeyedMessage<String, String>(topic, key, data + i));

    }

    producer.close();
  }
}
