package com.cenfotec.geography.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class Country {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String continent;
	private Long landArea;
	private Long seaArea;

	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY, mappedBy="country")
	private Set<PoliticalDivision> politicalDivisions;
	
	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY, mappedBy="country")
	private Set<BiologicalDivision> biologicalDivisions;
	
	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY, mappedBy="country")
	private Set<Being> beings;
}