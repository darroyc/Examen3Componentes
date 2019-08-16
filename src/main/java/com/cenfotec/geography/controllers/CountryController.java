package com.cenfotec.geography.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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

import com.cenfotec.geography.model.Country;
import com.cenfotec.geography.repository.CountryRepository;
import com.cenfotec.geography.services.CountryService;

@RestController
@RequestMapping({ "v1/countries" })
public class CountryController {

	@Autowired
	CountryService service;
	private CountryRepository repository;

	CountryController(CountryRepository countryRepository) {
		this.repository = countryRepository;
	}

	@GetMapping
	public ResponseEntity<List<Country>> findAll(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "id") String sortBy) {
		List<Country> list = service.getAllCountries(pageNo, pageSize, sortBy);

		return new ResponseEntity<List<Country>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity<Country> findById(@PathVariable long id) {
		return repository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public Country create(@RequestBody Country country) {
		return repository.save(country);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Country> update(@PathVariable("id") long id, @RequestBody Country country) {
		return repository.findById(id).map(record -> {
			record.setName(country.getName());
			record.setContinent(country.getContinent());
			record.setLandArea(country.getLandArea());
			record.setSeaArea(country.getSeaArea());
			Country updated = repository.save(record);
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
