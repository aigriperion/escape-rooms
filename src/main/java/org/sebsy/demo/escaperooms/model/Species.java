package org.sebsy.demo.escaperooms.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "species")
public class Species {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "common_name", nullable = false, length = 50)
    private String commonName;

    @Column(name = "latin_name", nullable = false, length = 200)
    private String latinName;

    @OneToMany(mappedBy = "species")
    private Set<Animal> animals = new HashSet<>();

    // Getters & setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getCommonName() { return commonName; }
    public void setCommonName(String commonName) { this.commonName = commonName; }

    public String getLatinName() { return latinName; }
    public void setLatinName(String latinName) { this.latinName = latinName; }

    public Set<Animal> getAnimals() { return animals; }
    public void setAnimals(Set<Animal> animals) { this.animals = animals; }

    @Override
    public String toString() {
        return "Species{id=" + id + ", commonName='" + commonName + "', latinName='" + latinName + "'}";
    }
}