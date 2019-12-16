package com.rocketmq;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

import java.util.ArrayList;
import java.util.Collection;

public class ProducerTest {
    public static void main(String[] args) throws MQClientException {
        DefaultMQProducer producer = new DefaultMQProducer("LongMaoAutoCheckGroup");
        producer.setNamesrvAddr("192.168.50.10:10001");

        producer.start();
        Collection<Message> messageList = new ArrayList();
        try {
            for (int i = 0; i < 1; i++) {
                Thread.sleep(5000); //每5秒发送一次MQ
                messageList.add(new Message("topic_auto_check", "TagsStudyAutoCheck", ("{\"id\":"+i+"}").getBytes()));
                //Message msg = new Message("topic_auto_check", "TagA", (new Date() + " Hello RocketMQ ,QuickStart" + i).getBytes());
            }
            producer.send(messageList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        producer.shutdown();
    }
}
