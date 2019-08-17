package com.cenfotec.geography.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cenfotec.geography.model.Being;

public interface BeingRepository extends JpaRepository<Being, Long> {	
	
	Optional<List<Being>> findByCountryId(Long id);
	
	Optional<List<Being>> findByCountryIdOrScientificNameContainingAndType(Long id, String name, String type);
}
