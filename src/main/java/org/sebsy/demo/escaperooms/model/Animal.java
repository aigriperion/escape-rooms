package org.sebsy.demo.escaperooms.model;

import jakarta.persistence.*;

@Entity
@Table(name = "animal")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50)
    private String color;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false)
    private String sex;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "species_id", nullable = false)
    private Species species;

    // Getters & setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSex() { return sex; }
    public void setSex(String sex) { this.sex = sex; }

    public Species getSpecies() { return species; }
    public void setSpecies(Species species) { this.species = species; }

    @Override
    public String toString() {
        return "Animal{id=" + id + ", name='" + name + "', color='" + color + "', sex='" + sex + "'}";
    }
}