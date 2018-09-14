package listener;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;

@SpringBootApplication
public class ReplyingDemo {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReplyingDemo.class);

    @Value("${app.topic.request_topic}")
    private String REQUEST_TOPIC;

    private static final String MESSAGE_RECEIVED_TEMPLATE =
            "\n**************************************************************************************************************************" +
            "\nServer received message: {}" +
            "\n**************************************************************************************************************************";

    public static void main(String[] args) {
        SpringApplication.run(ReplyingDemo.class, args);
    }

    @KafkaListener(id = "server", topics = "kRequests")
    @SendTo // use default replyTo expression
    public String listen(String in) {
        LOGGER.info(MESSAGE_RECEIVED_TEMPLATE, in);
        return in.toUpperCase();
    }

    @Bean
    public NewTopic kRequests() {
        return new NewTopic(REQUEST_TOPIC, 10, (short) 2);
    }
}