package org.sebsy.demo.escaperooms.repository;

import org.sebsy.demo.escaperooms.model.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpeciesRepository extends JpaRepository<Species, Integer> {
}