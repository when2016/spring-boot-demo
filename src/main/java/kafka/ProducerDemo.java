//package kafka;
//
//import java.text.DecimalFormat;
//import java.util.Date;
//import java.util.Properties;
//import java.util.Random;
//import lombok.val;
//import org.apache.kafka.clients.producer.KafkaProducer;
//import org.apache.kafka.clients.producer.Producer;
//import org.apache.kafka.clients.producer.ProducerRecord;
//
///**
// * @author wanghongen
// * @date 7/28/18 10:31 AM
// */
//public class ProducerDemo {
//
//  public static void main(String[] args) {
//    Properties props = new Properties();
//    props.put("bootstrap.servers", "localhost:9092");
//    props.put("acks", "all");
//    props.put("retries", 0);
//    props.put("batch.size", 16384);
//    props.put("linger.ms", 1);
//    props.put("buffer.memory", 33554432);
//    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//
//    String[] users = new String[]{"jack", "leo", "andy", "lucy", "jim", "smith", "iverson",
//        "andrew"};
//    String[] pages = new String[]{"iphone4.html", "huawei.html", "mi.html", "mac.html", "note.html",
//        "book.html", "fanlegefan.com"};
//    Random random = new Random();
//    int num = 10;
//    val df = new DecimalFormat("#.00");
//
//    Producer<String, String> producer = new KafkaProducer<>(props);
//    for (int i = 1; i <= num; i++) {
//
//
//      String message =
//          users[random.nextInt(users.length)] + "," + pages[random.nextInt(pages.length)] +
//              "," + df.format(random.nextDouble() * 1000) + "," + System.currentTimeMillis()+","+new Date().toInstant();
//
//      //producer.send(new ProducerRecord[String, String]("test", Integer.toString(i),message))
//
//      producer.send(new ProducerRecord<>("test", i+","+message));
//      //i--;
////      try {
////        Thread.sleep(1000);
////      } catch (InterruptedException e) {
////        e.printStackTrace();
////      }
//    }
//
//    producer.close();
//  }
//
//}
