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
import com.cenfotec.geography.repository.BiologicalDivisionRepository;

@RestController
@RequestMapping({ "v1/countries/biological_divisions" })
public class BiologicalDivisionController {

	@Autowired
	private BiologicalDivisionRepository repository;

	BiologicalDivisionController(BiologicalDivisionRepository biologicalRepository) {
		this.repository = biologicalRepository;
	}

	@GetMapping 
	public List<BiologicalDivision> findAll(){   
		return repository.findAll(); 
	}
	
	@GetMapping(path = { "/{id}" })
	public ResponseEntity<BiologicalDivision> findById(@PathVariable long id) {
		return repository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping(path = { "/country/{id}" })
	public ResponseEntity<List<BiologicalDivision>> findByCountryId(@PathVariable long id) {
		return repository.findByCountryId(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping(path = { "/characteristics" })
	public ResponseEntity<List<BiologicalDivision>> findByNameContaining(@RequestParam("name") String name) {
		return repository.findByNameContaining(name).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public BiologicalDivision create(@RequestBody BiologicalDivision biologicalDivision) {
		return repository.save(biologicalDivision);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<BiologicalDivision> update(@PathVariable("id") long id, @RequestBody BiologicalDivision biologicalDivision) {
		return repository.findById(id).map(record -> {
			record.setName(biologicalDivision.getName());
			record.setCountry(biologicalDivision.getCountry());
			BiologicalDivision updated = repository.save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(path = { "/{id}" })
	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		return repository.findById(id).map(record -> {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}
}
