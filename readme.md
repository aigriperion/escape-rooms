# Bestioles — TP Spring Data JPA

Exercice de manipulation d'**entités JPA** et de **repositories Spring Data** autour d'un petit modèle animalier.

## Modèle de données

- `Species` — espèce animale (ex. chat, chien). `@OneToMany` vers `Animal` (`mappedBy = "species"`).
- `Animal` — animal individuel rattaché à une espèce. `@ManyToMany` vers `Person` (`mappedBy = "animals"`).
- `Person` — personne pouvant adopter plusieurs animaux et porter plusieurs rôles.
- `Role` — rôle applicatif. `@ManyToMany` vers `Person` (`mappedBy = "roles"`).

Les côtés inverses des associations sont déclarés avec `mappedBy` ; le côté propriétaire porte la `@JoinTable` / `@JoinColumn`.

## Pré-requis

- Java 17
- MariaDB en local avec la base `bestioles` peuplée du schéma et de quelques données.

Configuration dans `src/main/resources/application.properties` :

```properties
spring.datasource.url=jdbc:mariadb://localhost:3306/bestioles
spring.datasource.username=esaip
spring.datasource.password=esaip
spring.jpa.hibernate.ddl-auto=validate
```

## Exécution

```bash
./mvnw spring-boot:run
```

Au démarrage, `BestiolesRunner` exécute une série de tests sur les repositories :

1. `findAll()` sur les animaux.
2. `save()` d'un nouvel animal lié à une espèce existante.
3. `findById()` pour le retrouver.
4. `delete()` puis vérification par `count()`.
5. Navigation inverse `Species → Animal` via `mappedBy`.
6. Navigation `Animal → Person` via la `@ManyToMany` bidirectionnelle.
7. Navigation `Role → Person` via `mappedBy`.
8. Vérification de cohérence bidirectionnelle `Person.animals` ↔ `Animal.persons`.

## Structure du projet

- `model/` — entités JPA (`Animal`, `Species`, `Person`, `Role`).
- `repository/` — interfaces `JpaRepository` correspondantes.
- `runner/BestiolesRunner.java` — `CommandLineRunner` qui exerce les repositories au démarrage.
