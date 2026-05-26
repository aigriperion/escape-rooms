# Escape Rooms — TP Spring Core (IoC & DI)

Exercice 5 du TP Spring Core. L'objectif est de mettre en application les concepts d'**injection de dépendance** et d'**inversion de contrôle** avec Spring.

## Règles de l'exercice

- Les classes `EscapeRoomsApplication` et `Startup` ne doivent pas être modifiées.
- Toutes les classes des packages `org.sebsy.demo.escaperooms.controller` et `org.sebsy.demo.escaperooms.bll` doivent être déclarées comme des beans/composants Spring.
- Seules les annotations Spring (`@Component`, `@Service`, `@Profile`, `@Autowired`) peuvent être ajoutées ou modifiées.

## Profils Spring

Trois profils sont disponibles pour l'application :

| Profil | Effet |
|--------|-------|
| `passage` | Chemin valide pour accéder aux chambres |
| `trap` | Vous êtes dans une impasse |
| `treasure` | Vous accédez au trésor |

La combinaison active est définie dans `application.properties` :

```properties
spring.profiles.active=passage,treasure
```

Chaque classe `@Service` doit être complétée avec un des profils — ils sont tous utilisés.

## Exécution

```bash
./mvnw spring-boot:run
```

Sortie attendue dans la console :

```
Entrez dans la salle 1 :
Bravo, vous avez trouvé la première salle !
Entrez dans la salle 2 :
Bravo, vous avez trouvé la deuxième salle !
Entrez dans la salle du trésor :
Gagné, vous avez trouvé la salle du trésor !
```

## Structure du projet

- `boot/Startup.java` — point d'entrée qui appelle successivement les contrôleurs des trois salles.
- `controller/` — contrôleurs des salles (salle 1, salle 2, salle du trésor).
- `bll/` — services métier annotés avec les profils correspondants.
