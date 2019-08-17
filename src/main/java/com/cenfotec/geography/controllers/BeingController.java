package com.cenfotec.geography.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cenfotec.geography.model.Being;
import com.cenfotec.geography.repository.BeingRepository;

@RestController
@RequestMapping({ "v1/countries/beings" })
public class BeingController {

	@Autowired
	private BeingRepository repository;

	BeingController(BeingRepository beingRepository) {
		this.repository = beingRepository;
	}

	@GetMapping 
	public List<Being> findAll(){   
		return repository.findAll(); 
	}
	
	@GetMapping(path = { "/{id}" })
	public ResponseEntity<Being> findById(@PathVariable Long id) {
		return repository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping(path = { "/country/{id}" })
	public ResponseEntity<List<Being>> findByCountryId(@PathVariable Long id) {
		return repository.findByCountryId(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping(path = { "/characteristics" })
	public ResponseEntity<List<Being>> findBeingsByCharacteristcs(@RequestParam(value = "id", required = false) Long id, @RequestParam(value = "name", required = false) String name , @RequestParam("type") String type) {
		if(name == null) {
			name = "";
		}else if(id == null) {
			id = (long) 0;
		}
		return repository.findByCountryIdOrScientificNameContainingAndType(id, name, type).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());		
	}


	@PostMapping
	public Being create(@RequestBody Being being) {
		return repository.save(being);
	}
}
