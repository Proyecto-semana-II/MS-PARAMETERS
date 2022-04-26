package com.bootcamp.parameters.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.parameters.entity.DetailParameter;
import com.bootcamp.parameters.service.DetailParameterService;
import com.bootcamp.parameters.util.Constants;
import com.bootcamp.parameters.util.Shared;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/detailParameter")
public class DetailParameterController extends Shared{

	@Autowired
	private DetailParameterService service;	
	
	@GetMapping
	public Mono<ResponseEntity<Flux<DetailParameter>>> getAll() {
		return Mono.just(ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(service.findAll())
				);
	}
	
	@GetMapping("/{idParameter}")
	public Mono<ResponseEntity<Flux<DetailParameter>>> getAllActivesbyIdParameter(@PathVariable String idParameter) {
		return Mono.just(ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(service.findAllByIdParameterAndRegistrationStatus(idParameter, Constants.ESTADO_VIGENTE))
				);
	}
	
	@GetMapping("/{idParameter}/{idDetailParameter}")
	public Mono<ResponseEntity<Flux<DetailParameter>>> getAllActivesbyIdParameterByIdDetailParameter(@PathVariable String idParameter, @PathVariable String idDetailParameter) {
		return Mono.just(ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(service.findAllByIdParameterAndIdDetailParameterAndRegistrationStatus(idParameter, idDetailParameter, Constants.ESTADO_VIGENTE))
				);
	}
	
	@PostMapping
	public Mono<ResponseEntity<DetailParameter>> createDetailParameter(@RequestBody DetailParameter detailParameter) {
		detailParameter.setRegistrationStatus(Constants.ESTADO_VIGENTE);
		detailParameter.setAudit(getBeanCreationParameters());
		return service.save(detailParameter)
				.map(p-> ResponseEntity
					.created(URI.create(Constants.URL_DETAIL_PARAMETER.concat(p.getIdParameter())))
					.contentType(MediaType.APPLICATION_JSON)
					.body(p));
	}
	
	@PutMapping("/{idDetailParameter}")
	public Mono<ResponseEntity<DetailParameter>> updateParameter(@RequestBody DetailParameter detailParameter, @PathVariable String idDetailParameter) {
		return service.findById(idDetailParameter).flatMap(p -> {
			p.setName(detailParameter.getName());
			p.setDescription(detailParameter.getDescription());
			p.setAbbreviation(detailParameter.getAbbreviation());
			p.setAudit(getBeanModificationParameters(p.getAudit()));
			return service.save(p);
		}).map(p -> ResponseEntity.created(URI.create(Constants.URL_DETAIL_PARAMETER.concat(p.getIdParameter())))
				.contentType(MediaType.APPLICATION_JSON)
				.body(p))
				.defaultIfEmpty(ResponseEntity.noContent().build());
				
	}
	
	@DeleteMapping("/{idDetailParameter}")
	public Mono<ResponseEntity<DetailParameter>> deleteTypeParameter(@PathVariable String idDetailParameter){
		return service.findById(idDetailParameter).flatMap(p -> {
			p.setRegistrationStatus(Constants.ESTADO_NO_VIGENTE);
			p.setAudit(getBeanModificationParameters(p.getAudit()));
			return service.save(p);
		}).map(p -> ResponseEntity.created(URI.create(Constants.URL_DETAIL_PARAMETER.concat(p.getIdParameter())))
				.contentType(MediaType.APPLICATION_JSON)
				.body(p))
				.defaultIfEmpty(ResponseEntity.noContent().build());
	}
}
