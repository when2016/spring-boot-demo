package com.wanghongen.demo.kafka;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.io.IOException;
import java.util.Properties;

/**
 * @author wanghongen
 * @date 7/29/18 10:53 AM
 */
public class TestKafkaProducer implements AutoCloseable {
  private Producer<String, String> producer;

  private TestKafkaProducer() throws IOException {
    Properties props = new Properties();
    props.load(ClassLoader.getSystemResourceAsStream("producer.properties"));
    ProducerConfig config = new ProducerConfig(props);
    producer = new Producer<>(config);
  }

  private void send(String topicName, String message) {
    if (null == topicName || null == message) {
      return;
    }
    KeyedMessage<String, String> km = new KeyedMessage<>(topicName, message);
    producer.send(km);
  }

  @Override
  public void close() {
    if (null != producer) {
      producer.close();
    }
  }

  public static void main(String[] args) {
    try (TestKafkaProducer kafka = new TestKafkaProducer()) {
      int i = 0;
      while (true) {
        kafka.send("kafkatest", "Hello Java Appliaction!!");
        i++;
        Thread.sleep(2000);
      }
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
  }
}
