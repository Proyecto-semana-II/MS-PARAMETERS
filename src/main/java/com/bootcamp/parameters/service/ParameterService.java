package com.bootcamp.parameters.service;

import org.springframework.stereotype.Service;

import com.bootcamp.parameters.entity.Parameter;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface ParameterService {

	public Flux<Parameter> findAll();
	
	public Flux<Parameter> findAllByRegistrationStatus(String registrationStatus);

	public Mono<Parameter> findById(String id);

	public Mono<Parameter> save(Parameter parameter);

	public Mono<Void> delete(Parameter parameter);
}
