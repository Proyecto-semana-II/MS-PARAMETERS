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

import com.bootcamp.parameters.entity.Parameter;
import com.bootcamp.parameters.service.KafkaListenerService;
import com.bootcamp.parameters.service.ParameterService;
import com.bootcamp.parameters.util.Constants;
import com.bootcamp.parameters.util.Shared;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/parameter")
public class ParameterController extends Shared{
	
	@Autowired
	private ParameterService service;
	
	@Autowired
	KafkaListenerService listener;
	
	@GetMapping
	public Mono<ResponseEntity<Flux<Parameter>>> getAllActives() {
		return Mono.just(ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(service.findAllByRegistrationStatus(Constants.ESTADO_VIGENTE))
				);
	}
	
	@GetMapping("/kafka")
	public Mono<ResponseEntity<Flux<Parameter>>> getAllActivesKafka() {
		return Mono.just(ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(listener.findAllKafka(Constants.ESTADO_VIGENTE))
				);
	}
	
	@GetMapping("/{id}")
	public Mono<ResponseEntity<Parameter>> getById(@PathVariable String id) throws InterruptedException {
		return service.findById(id)
				.map(p -> ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(p))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public Mono<ResponseEntity<Parameter>> createParameter(@RequestBody Parameter parameter) {
		parameter.setRegistrationStatus(Constants.ESTADO_VIGENTE);
		parameter.setAudit(getBeanCreationParameters());
		return service.save(parameter)
				.map(p-> ResponseEntity
					.created(URI.create(Constants.URL_PARAMETER.concat(p.getIdParameter())))
					.contentType(MediaType.APPLICATION_JSON)
					.body(p));
	}
	
	@PutMapping("/{id}")
	public Mono<ResponseEntity<Parameter>> updateParameter(@RequestBody Parameter parameter, @PathVariable String id) {
		return service.findById(id).flatMap(p -> {
			p.setName(parameter.getName());
			p.setDescription(parameter.getDescription());
			p.setAbbreviation(parameter.getAbbreviation());
			p.setAudit(getBeanModificationParameters(p.getAudit()));
			return service.save(p);
		}).map(p -> ResponseEntity.created(URI.create(Constants.URL_PARAMETER.concat(p.getIdParameter())))
				.contentType(MediaType.APPLICATION_JSON)
				.body(p))
				.defaultIfEmpty(ResponseEntity.noContent().build());
				
	}
	
	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Parameter>> deleteTypeParameter(@PathVariable String id){
		return service.findById(id).flatMap(p -> {
			p.setRegistrationStatus(Constants.ESTADO_NO_VIGENTE);
			p.setAudit(getBeanModificationParameters(p.getAudit()));
			return service.save(p);
		}).map(p -> ResponseEntity.created(URI.create(Constants.URL_PARAMETER.concat(p.getIdParameter())))
				.contentType(MediaType.APPLICATION_JSON)
				.body(p))
				.defaultIfEmpty(ResponseEntity.noContent().build());
	}

}
