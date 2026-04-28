package org.sebsy.demo.escaperooms.runner;

import org.sebsy.demo.escaperooms.model.Animal;
import org.sebsy.demo.escaperooms.model.Person;
import org.sebsy.demo.escaperooms.model.Role;
import org.sebsy.demo.escaperooms.model.Species;
import org.sebsy.demo.escaperooms.repository.AnimalRepository;
import org.sebsy.demo.escaperooms.repository.PersonRepository;
import org.sebsy.demo.escaperooms.repository.RoleRepository;
import org.sebsy.demo.escaperooms.repository.SpeciesRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class BestiolesRunner implements CommandLineRunner {

    private final AnimalRepository animalRepository;
    private final SpeciesRepository speciesRepository;
    private final PersonRepository personRepository;
    private final RoleRepository roleRepository;

    public BestiolesRunner(AnimalRepository animalRepository,
                           SpeciesRepository speciesRepository,
                           PersonRepository personRepository,
                           RoleRepository roleRepository) {
        this.animalRepository = animalRepository;
        this.speciesRepository = speciesRepository;
        this.personRepository = personRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        System.out.println("\nTESTS REPOSITORIES BESTIOLES\n");

        // 1. findAll : afficher tous les animaux existants
        System.out.println("--- 1. findAll() : tous les animaux ---");
        List<Animal> animaux = animalRepository.findAll();
        animaux.forEach(a -> System.out.println("  " + a));
        System.out.println("Total : " + animaux.size() + " animal(aux)");

        // 2. save : créer un nouvel animal lié à une espèce existante
        System.out.println("\n--- 2. save() : création d'un nouvel animal ---");
        Species chat = speciesRepository.findById(1)
                .orElseThrow(() -> new RuntimeException("Espèce 'Chat' introuvable"));

        Animal nouveau = new Animal();
        nouveau.setName("Garfield");
        nouveau.setColor("Orange");
        nouveau.setSex("M");
        nouveau.setSpecies(chat);

        Animal sauvegarde = animalRepository.save(nouveau);
        System.out.println("Animal créé avec l'id : " + sauvegarde.getId());
        System.out.println("Détails : " + sauvegarde);

        // 3. findById : retrouver l'animal créé
        System.out.println("\n--- 3. findById() ---");
        Optional<Animal> retrouve = animalRepository.findById(sauvegarde.getId());
        retrouve.ifPresentOrElse(
                a -> System.out.println("  Trouvé : " + a),
                () -> System.out.println("  Aucun animal trouvé avec cet id")
        );

        // 4. delete : supprimer l'animal et vérifier
        System.out.println("\n--- 4. delete() ---");
        long avant = animalRepository.count();
        System.out.println("Nombre d'animaux avant suppression : " + avant);

        animalRepository.delete(sauvegarde);

        long apres = animalRepository.count();
        System.out.println("Nombre d'animaux après suppression : " + apres);
        System.out.println("-> " + (avant - apres) + " animal supprimé");

        // 5. Navigation inverse via mappedBy
        System.out.println("\n--- 5. Navigation inverse : animaux par espèce ---");
        List<Species> especes = speciesRepository.findAll();
        for (Species esp : especes) {
            System.out.println("  " + esp.getCommonName() + " : "
                    + esp.getAnimals().size() + " animal(aux)");
            esp.getAnimals().forEach(a -> System.out.println("    - " + a.getName()));
        }

        // 6. Animal -> Persons : pour chaque animal, qui l'a adopté ?
        System.out.println("--- 6. Animal -> Persons (mappedBy = \"animals\") ---");
        List<Animal> animauxNav = animalRepository.findAll();
        for (Animal a : animauxNav) {
            System.out.println("  " + a.getName() + " est lié à "
                    + a.getPersons().size() + " personne(s) :");
            a.getPersons().forEach(p ->
                    System.out.println("    - " + p.getFirstname() + " " + p.getLastname()));
        }

        // 7. Role -> Persons : pour chaque rôle, qui le possède ?
        System.out.println("\n--- 7. Role -> Persons (mappedBy = \"roles\") ---");
        List<Role> roles = roleRepository.findAll();
        for (Role r : roles) {
            System.out.println("  Rôle '" + r.getLabel() + "' porté par "
                    + r.getPersons().size() + " personne(s) :");
            r.getPersons().forEach(p ->
                    System.out.println("    - " + p.getLogin()));
        }

        // 8. Vérification de cohérence : Person.animals <-> Animal.persons
        System.out.println("\n--- 8. Cohérence bidirectionnelle ---");
        List<Person> personnes = personRepository.findAll();
        for (Person p : personnes) {
            for (Animal a : p.getAnimals()) {
                boolean coherent = a.getPersons().contains(p);
                System.out.println("  " + p.getLogin() + " <-> " + a.getName()
                        + " : " + (coherent ? "OK" : "INCOHÉRENT"));
            }
        }

        System.out.println("\nFIN DES TESTS BESTIOLES\n");
    }
}