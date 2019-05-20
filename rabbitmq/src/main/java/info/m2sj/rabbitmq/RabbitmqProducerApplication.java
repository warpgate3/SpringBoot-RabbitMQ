package info.m2sj.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.sql.Timestamp;

@SpringBootApplication
@Slf4j
@EnableScheduling
public class RabbitmqProducerApplication {
    private final RabbitTemplate rabbitTemplate;

    public RabbitmqProducerApplication(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqProducerApplication.class, args);
    }

    @Scheduled(cron = "0/10 * * * * *")
    public void sendMessage() {
        rabbitTemplate.convertAndSend("myrountingkey", new Timestamp(System.currentTimeMillis()).toString());
    }
}
