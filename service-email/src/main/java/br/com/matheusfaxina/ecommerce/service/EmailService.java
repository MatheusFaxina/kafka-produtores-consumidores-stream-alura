package br.com.matheusfaxina.ecommerce.service;

import br.com.matheusfaxina.ecommerce.kafka.KafkaService;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.HashMap;

public class EmailService {

    public static void main(String[] args) {
        EmailService emailService = new EmailService();

        try (KafkaService kafkaService = new KafkaService<>(
                EmailService.class.getSimpleName(),
                "ECOMMERCE_SEND_EMAIL",
                emailService::parse,
                String.class,
                new HashMap<String, String>())) {
            kafkaService.run();
        }
    }

    private void parse(ConsumerRecord<String, String> record) {
        System.out.println("Sending email");
        System.out.println(record);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }

        System.out.println("Email sent");
    }

}
