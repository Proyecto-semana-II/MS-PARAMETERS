package com.bootcamp.parameters.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.bootcamp.parameters.repository.ParameterRepository;
import com.bootcamp.parameters.service.KafkaListenerService;
import com.proyectobancario.personmicroservicios.model.dto.Parameter;

import reactor.core.publisher.Flux;

@Service
public class KafkaListenerServiceImpl implements KafkaListenerService {
	
	@Autowired
	private ParameterRepository repository;
	
	@KafkaListener(topics = "test1", groupId = "myGroup")
	public void listenTopic(Parameter parameter) {
		com.bootcamp.parameters.entity.Parameter p = new com.bootcamp.parameters.entity.Parameter();
		p.setName(parameter.getName());
		System.out.println("Ha llegado: "+parameter.getName());
		repository.save(p);
	}
	

	@Override
	public Flux<com.bootcamp.parameters.entity.Parameter> findAllKafka(String registrationStatus) {
		return repository.findAllByRegistrationStatus(registrationStatus);
	}

}
