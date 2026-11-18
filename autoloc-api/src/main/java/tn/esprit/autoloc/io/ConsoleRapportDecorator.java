package tn.esprit.autoloc.io;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
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
        log.info("=== AFFICHAGE CONSOLE DU RAPPORT ===");
        log.info("{}", contenu);
        log.info("====================================");

        // On retourne le contenu original pour que Postman l'affiche aussi
        return contenu;
    }
}