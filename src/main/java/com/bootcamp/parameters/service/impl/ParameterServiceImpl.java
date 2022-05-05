package com.bootcamp.parameters.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.parameters.entity.Parameter;
import com.bootcamp.parameters.repository.ParameterRepository;
import com.bootcamp.parameters.service.ParameterService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ParameterServiceImpl implements ParameterService {

	@Autowired
	private ParameterRepository repository;

	@Override
	public Flux<Parameter> findAll() {
		return repository.findAll();
	}

	@Override
	public Flux<Parameter> findAllByRegistrationStatus(String registrationStatus) {
		//TEST CON EL CIRCUITBREAKER
		/*try {
			TimeUnit.SECONDS.sleep(5L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		return repository.findAllByRegistrationStatus(registrationStatus);
	}
	
	@Override
	public Mono<Parameter> findById(String id) {
		return repository.findById(id);
	}

	@Override
	public Mono<Parameter> save(Parameter parameter) {
		return repository.save(parameter);
	}

	@Override
	public Mono<Void> delete(Parameter parameter) {
		return repository.delete(parameter);
	}

}
