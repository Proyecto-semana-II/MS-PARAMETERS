package com.bootcamp.parameters.service;

import com.bootcamp.parameters.entity.Parameter;

import reactor.core.publisher.Flux;

public interface KafkaListenerService {
	
	public Flux<Parameter> findAllKafka(String registrationStatus);
}
