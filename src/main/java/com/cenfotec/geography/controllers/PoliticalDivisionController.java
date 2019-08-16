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

import com.cenfotec.geography.model.BiologicalDivision;
import com.cenfotec.geography.model.PoliticalDivision;
import com.cenfotec.geography.repository.PoliticalDivisionRepository;

@RestController
@RequestMapping({ "v1/countries/political_divisions" })
public class PoliticalDivisionController {

	@Autowired
	private PoliticalDivisionRepository repository;

	PoliticalDivisionController(PoliticalDivisionRepository politicalRepository) {
		this.repository = politicalRepository;
	}

	@GetMapping 
	public List findAll(){   
		return repository.findAll(); 
	}
	
	@GetMapping(path = { "/{id}" })
	public ResponseEntity<PoliticalDivision> findById(@PathVariable long id) {
		return repository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping(path = { "/country/{id}" })
	public ResponseEntity<List<PoliticalDivision>> findByCountryId(@PathVariable long id) {
		return repository.findByCountryId(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping(path = { "/characteristics" })
	public ResponseEntity<List<PoliticalDivision>> findByNameContaining(@RequestParam("name") String name) {
		return repository.findByNameContaining(name).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public PoliticalDivision create(@RequestBody PoliticalDivision politicalDivision) {
		return repository.save(politicalDivision);
	}
}
