package com.bootcamp.parameters.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.bootcamp.parameters.entity.DetailParameter;

import reactor.core.publisher.Flux;

@Repository
public interface DetailParameterRepository extends ReactiveMongoRepository<DetailParameter, String> {

	Flux<DetailParameter> findAllByIdParameterAndRegistrationStatus(String idParameter, String registrationStatus);

	Flux<DetailParameter> findAllByIdParameterAndIdDetailParameterAndRegistrationStatus(String idParameter,
			String idDetailParameter, String registrationStatus);

}
