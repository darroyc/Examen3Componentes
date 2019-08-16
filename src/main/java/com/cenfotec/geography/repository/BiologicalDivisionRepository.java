package com.cenfotec.geography.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cenfotec.geography.model.BiologicalDivision;

public interface BiologicalDivisionRepository extends JpaRepository<BiologicalDivision, Long> {

	Optional<List<BiologicalDivision>> findByCountryId(Long id);
	
	Optional<List<BiologicalDivision>> findByNameContaining(String name);
}
