package br.com.matheusfaxina.ecommerce.service;

import br.com.matheusfaxina.ecommerce.kafka.KafkaService;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.HashMap;

public class FraudDetectorService {

    public static void main(String[] args) {
        FraudDetectorService fraudeDetectorService = new FraudDetectorService();

        try(KafkaService kafkaService = new KafkaService<>(
                FraudDetectorService.class.getSimpleName(),
                "ECOMMERCE_NEW_ORDER",
                fraudeDetectorService::parse,
                Order.class,
                new HashMap<String, String>())) {
            kafkaService.run();
        }
    }

    private void parse(ConsumerRecord<String, Order> record) {
        System.out.println("Processing new order, new fraude");
        System.out.println(record);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }

        System.out.println("Order processed");
    }

}
