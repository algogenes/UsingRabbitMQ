package com.airtime.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class publisher {
    @Autowired
    AmqpTemplate amqpTemplate;

    @Value("${publisher.rabbitmq.exchange}")
    String exchange;

    @Value("${publisher.rabbitmq.routingkey}")
    String routingkey;


//    @PostConstruct
//    void setUp(){
//        this.publish("Hello World ");
//    }

    public void publish(String message){

        amqpTemplate.convertAndSend(this.exchange, this.routingkey, message);
    }
}
