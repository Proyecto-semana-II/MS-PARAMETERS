package com.bootcamp.parameters.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.parameters.entity.DetailParameter;
import com.bootcamp.parameters.repository.DetailParameterRepository;
import com.bootcamp.parameters.service.DetailParameterService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DetailParameterServiceImpl implements DetailParameterService {

	@Autowired
	private DetailParameterRepository repository;

	@Override
	public Flux<DetailParameter> findAll() {
		return repository.findAll();
	}

	@Override
	public Flux<DetailParameter> findAllByIdParameterAndRegistrationStatus(String idParameter,
			String registrationStatus) {
		return repository.findAllByIdParameterAndRegistrationStatus(idParameter, registrationStatus);
	}

	@Override
	public Flux<DetailParameter> findAllByIdParameterAndIdDetailParameterAndRegistrationStatus(String idParameter,
			String idDetailParameter, String registrationStatus) {
		return repository.findAllByIdParameterAndIdDetailParameterAndRegistrationStatus(idParameter, idDetailParameter,
				registrationStatus);
	}

	@Override
	public Mono<DetailParameter> findById(String id) {
		return repository.findById(id);
	}

	@Override
	public Mono<DetailParameter> save(DetailParameter detailParameter) {
		return repository.save(detailParameter);
	}

	@Override
	public Mono<Void> delete(DetailParameter detailParameter) {
		return repository.delete(detailParameter);
	}

}
