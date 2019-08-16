package com.cenfotec.geography.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;

import com.cenfotec.geography.model.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {	
	
	@EntityGraph(value = "Country.politicalDivisions", type = EntityGraphType.FETCH)
	public Country findById(int id);
	
	@EntityGraph(value = "Country.biologicalDivisions", type = EntityGraphType.FETCH)
	public Country readById(int id);
}
