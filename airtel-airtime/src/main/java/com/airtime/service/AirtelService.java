package com.airtime.service;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.airtime.dtos.Transaction;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AirtelService implements MessageListener{
	
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	@Value("${publisher.rabbitmq.exchange}")
	private String exchange;
	
	@Value("${publisher.rabbitmq.routingkey}")
	private String routingkey;
	
	public void onMessage(Message message) {
    	ObjectMapper mapper = new ObjectMapper();
        System.out.println("Consuming Message - " + new String(message.getBody()));
        String trxnString = new String(message.getBody());
        
        System.out.println("amqpTemplate is - " + amqpTemplate.toString());
        
        try {
			Transaction trxn = mapper.readValue(trxnString, Transaction.class);
			System.out.println("Account numberis :" + trxn.getAccountNumber());
			
			   //another one
			trxn.setStatus("processed");
			//System.out.println("amqpTemplate is - " + amqpTemplate.toString());
    		//amqpTemplate.convertAndSend(this.exchange, this.routingkey, trxn);
    		System.out.println("Publishing to queue with name processed = " + trxn);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}        
    }
	
	public void send(Transaction trxn) {
		amqpTemplate.convertAndSend(exchange, routingkey, trxn);
		System.out.println("Send msg = " + trxn);
	    
	}
	

}
