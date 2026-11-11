package tn.esprit.autoloc.calcul;

import org.springframework.stereotype.Component;

@Component("STANDARD")
public class TarifStandard implements IStrategieTarif {
    @Override
    public double calculer(double tarifJournalier, int nombreJours) {
        return tarifJournalier * nombreJours;
    }
}