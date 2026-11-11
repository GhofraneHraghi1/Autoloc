package tn.esprit.autoloc.io;

import org.springframework.stereotype.Component;

@Component
public class RapportFactory {

    public IRapport creerRapport(String contenu) {
        // 1. Créer le rapport de base
        IRapport rapportDeBase = new RapportAgence(contenu);

        // 2. L'envelopper avec le Decorator pour qu'il s'affiche sur la console
        return new ConsoleRapportDecorator(rapportDeBase);
    }
}