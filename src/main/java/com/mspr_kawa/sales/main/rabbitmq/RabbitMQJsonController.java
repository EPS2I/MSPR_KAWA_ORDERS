package com.mspr_kawa.sales.main.rabbitmq;

import com.mspr_kawa.sales.main.model.Sale;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/message")
public class RabbitMQJsonController {
    private final RabbitMQJsonProducer jsonProducer;
    public RabbitMQJsonController(RabbitMQJsonProducer jsonProducer) {
        this.jsonProducer = jsonProducer;
    }

    @PostMapping("/publish/json")
    public ResponseEntity<String> sendJsonMessage(@RequestBody Sale sale){
        jsonProducer.sendJsonMessage(sale);
        return ResponseEntity.ok(" Json  Product Message sent to RabbitMQ ....");
    }
}
