package com.bootcamp.parameters.service.impl;

import java.util.concurrent.TimeUnit;

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
		return repository.findAllByRegistrationStatus(registrationStatus);
	}
	
	@Override
	public Mono<Parameter> findById(String id) {
		if (id.equals("2")) {
			throw new IllegalStateException("NO HAY");
		}
		if (id.equals("1")) {
			try {
				TimeUnit.SECONDS.sleep(5L);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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
