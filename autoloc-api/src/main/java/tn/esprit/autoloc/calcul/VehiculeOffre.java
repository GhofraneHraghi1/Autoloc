package tn.esprit.autoloc.calcul;

public enum VehiculeOffre {
    STANDARD("standard"),
    WEEKEND("weekend"),
    LONGUE_DUREE("longueDuree");

    private final String nomStrategie;

    VehiculeOffre(String nomStrategie) {
        this.nomStrategie = nomStrategie;
    }

    public String getNomStrategie() {
        return nomStrategie;
    }
}