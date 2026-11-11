package tn.esprit.autoloc.io;

import org.springframework.stereotype.Component;

@Component // L'annotation demandée par l'atelier
public class ConsoleRapportDecorator implements IRapport {

    private IRapport rapport;

    // Constructeur vide pour Spring
    public ConsoleRapportDecorator() {
    }

    // Constructeur pour envelopper un rapport
    public ConsoleRapportDecorator(IRapport rapport) {
        this.rapport = rapport;
    }

    @Override
    public String genererRapport() {
        if (rapport == null) {
            return "";
        }
        String contenu = rapport.genererRapport();

        // Ajout de la fonctionnalité (affichage console)
        System.out.println("\n=== AFFICHAGE CONSOLE DU RAPPORT ===");
        System.out.println(contenu);
        System.out.println("====================================\n");

        // On retourne le contenu original pour que Postman l'affiche aussi
        return contenu;
    }
}