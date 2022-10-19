package com.airtime.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airtime.dtos.Transaction;
import com.airtime.service.AirtelService;

@RestController
@RequestMapping(value = "/javainuserabbitmq/")
public class Controller {
	
	@Autowired
	AirtelService airtelService;

	@GetMapping(value = "/produce")
	public String producer() throws JsonProcessingException {
	Transaction emp=new Transaction();
	emp.setAccountNumber("abcde");
	emp.setAmount(500L);
	emp.setMobileNumber("987");
	emp.setStatus("done");
	airtelService.send(emp);

		return "Message sent to the RabbitMQ JavaInUse Successfully";
	}

}
