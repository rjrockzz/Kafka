package arjun.kafka.lab;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import java.util.Properties;
public class ProducerAsync
{
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String, String> producer = new KafkaProducer<>(props);
        ProducerRecord<String, String> record = new ProducerRecord<>("producer", "String Key", "Hello! Async Send");
        try {
            producer.send(record, (recordMetadata, e) -> {
                if (e != null)
                    e.printStackTrace();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        producer.close();
    }
}