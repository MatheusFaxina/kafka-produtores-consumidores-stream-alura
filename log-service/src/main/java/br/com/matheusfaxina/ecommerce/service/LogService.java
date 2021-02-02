package br.com.matheusfaxina.ecommerce.service;

import br.com.matheusfaxina.ecommerce.kafka.KafkaService;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class LogService {

    public static void main(String[] args) {
        LogService logService = new LogService();

        Map map = new HashMap();
        map.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        try(KafkaService kafkaService = new KafkaService(
                LogService.class.getSimpleName(),
                Pattern.compile("ECOMMERCE.*"),
                logService::parse,
                String.class,
                map)) {
            kafkaService.run();
        }
    }

    private void parse(ConsumerRecord<String, String> consumerRecord) {
        System.out.println("LOG");
        System.out.println(consumerRecord);
    }

}
