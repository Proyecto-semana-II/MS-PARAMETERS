package com.bootcamp.parameters.service;

import org.springframework.stereotype.Service;

import com.bootcamp.parameters.entity.DetailParameter;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface DetailParameterService {

	public Flux<DetailParameter> findAll();

	public Flux<DetailParameter> findAllByIdParameterAndRegistrationStatus(String idParameter,
			String registrationStatus);

	public Flux<DetailParameter> findAllByIdParameterAndIdDetailParameterAndRegistrationStatus(String idParameter,
			String idDetailParameter, String registrationStatus);

	public Mono<DetailParameter> findById(String id);

	public Mono<DetailParameter> save(DetailParameter detailParameter);

	public Mono<Void> delete(DetailParameter detailParameter);
}
