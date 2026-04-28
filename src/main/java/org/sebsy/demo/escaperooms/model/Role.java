package org.sebsy.demo.escaperooms.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int unsigned")
    private Integer id;

    @Column(nullable = false, length = 50)
    private String label;

    @ManyToMany(mappedBy = "roles")
    private Set<Person> persons = new HashSet<>();

    // Getters & setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }

    public Set<Person> getPersons() { return persons; }
    public void setPersons(Set<Person> persons) { this.persons = persons; }

    @Override
    public String toString() {
        return "Role{id=" + id + ", label='" + label + "'}";
    }
}