package com.bootcamp.parameters.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.bootcamp.parameters.entity.Parameter;

import reactor.core.publisher.Flux;

@Repository
public interface ParameterRepository extends ReactiveMongoRepository<Parameter, String> {
	
	Flux<Parameter> findAllByRegistrationStatus(String registrationStatus);

}
