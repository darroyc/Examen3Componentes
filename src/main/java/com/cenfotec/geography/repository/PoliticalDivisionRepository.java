package com.cenfotec.geography.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cenfotec.geography.model.PoliticalDivision;

public interface PoliticalDivisionRepository extends JpaRepository<PoliticalDivision, Long> {	
	
	Optional<List<PoliticalDivision>> findByCountryId(Long id);
	
	Optional<List<PoliticalDivision>> findByNameContaining(String name);
}
