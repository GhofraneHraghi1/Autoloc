package tn.esprit.autoloc.io;

public class RapportAgence implements IRapport {
    private final String contenu;

    public RapportAgence(String contenu) {
        this.contenu = contenu;
    }

    @Override
    public String genererRapport() {
        return contenu;
    }
}