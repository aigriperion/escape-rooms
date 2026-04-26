package org.sebsy.demo.escaperooms.repository;

import org.sebsy.demo.escaperooms.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}