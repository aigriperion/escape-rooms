package org.sebsy.demo.escaperooms.runner;

import org.sebsy.demo.escaperooms.model.Animal;
import org.sebsy.demo.escaperooms.model.Species;
import org.sebsy.demo.escaperooms.repository.AnimalRepository;
import org.sebsy.demo.escaperooms.repository.SpeciesRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BestiolesRunner implements CommandLineRunner {

    private final AnimalRepository animalRepository;
    private final SpeciesRepository speciesRepository;

    public BestiolesRunner(AnimalRepository animalRepository,
                           SpeciesRepository speciesRepository) {
        this.animalRepository = animalRepository;
        this.speciesRepository = speciesRepository;
    }

    @Override
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

        System.out.println("\nFIN DES TESTS BESTIOLES\n");
    }
}