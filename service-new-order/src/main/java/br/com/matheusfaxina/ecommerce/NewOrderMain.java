package br.com.matheusfaxina.ecommerce;

import br.com.matheusfaxina.ecommerce.kafka.KafkaDispatcher;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NewOrderMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        try(KafkaDispatcher orderKafkaDispatcher = new KafkaDispatcher<Order>()) {
            try(KafkaDispatcher emailOrderDispatcher = new KafkaDispatcher<String>()) {
                for (int i = 0; i < 10; i++) {
                    String userId = UUID.randomUUID().toString();
                    String orderId = UUID.randomUUID().toString();
                    BigDecimal amount = new BigDecimal(Math.random() * 5000 + 1);
                    Order order = new Order(userId, orderId, amount);
                    orderKafkaDispatcher.send("ECOMMERCE_NEW_ORDER", userId, order);

                    String email = "Email sent";
                    emailOrderDispatcher.send("ECOMMERCE_SEND_EMAIL", userId, email);
                }
            }
        }
    }

}
