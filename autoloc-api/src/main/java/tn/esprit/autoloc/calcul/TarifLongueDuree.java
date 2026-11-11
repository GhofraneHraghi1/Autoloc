package tn.esprit.autoloc.calcul;

import org.springframework.stereotype.Component;

@Component("LONGUE_DUREE")
public class TarifLongueDuree implements IStrategieTarif {
    @Override
    public double calculer(double tarifJournalier, int nombreJours) {
        double total = (tarifJournalier * 0.75) * nombreJours;
        if (total > 2000) {
            total = total * 0.95;
        }
        return total;
    }
}