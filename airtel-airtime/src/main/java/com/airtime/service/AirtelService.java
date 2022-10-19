package com.airtime.service;

import com.airtime.dtos.Transaction;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class AirtelService implements MessageListener {

	@Autowired
	publisher publisher;
	
//	public void onMessage(Message message) {
//    	ObjectMapper mapper = new ObjectMapper();
//        System.out.println("Consuming Message - " + new String(message.getBody()));
//        String trxnString = new String(message.getBody());
//		System.out.println("new String"+trxnString);
//		try {
//			Transaction trxn = mapper.readValue(message.getBody(), Transaction.class);
//			System.out.println("Account numberis :" + trxn.getAccountNumber());
//			trxn.setStatus("processed");
//			 publisher.publish(String.valueOf(trxn));
//    		System.out.println("Publishing to queue with name processed = " + trxn);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//    }
	
	public void send(Transaction trxn) throws JsonProcessingException {
		String res = new ObjectMapper().writeValueAsString(trxn);
		publisher.publish(res);
		System.out.println("Send msg = " + res);
	    
	}


	@Override
	public void onMessage(Message message) {
		ObjectMapper mapper = new ObjectMapper();
        System.out.println("Consuming Message - " + new String(message.getBody()));
        String trxnString = new String(message.getBody());
		System.out.println("new String"+trxnString);
		try {
			Transaction trxn = mapper.readValue(message.getBody(), Transaction.class);
			System.out.println("Account numberis :" + trxn.getAccountNumber());
			trxn.setStatus("processed");
			 publisher.publish(String.valueOf(trxn));
    		System.out.println("Publishing to queue with name processed = " + trxn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
