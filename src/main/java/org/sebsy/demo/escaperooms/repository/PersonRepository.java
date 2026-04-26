package org.sebsy.demo.escaperooms.repository;

import org.sebsy.demo.escaperooms.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
}