package tn.esprit.autoloc.calcul;

import org.springframework.stereotype.Component;

@Component("weekend")
public class TarifWeekend implements IStrategieTarif {
    @Override
    public double calculer(double tarifJournalier, int nombreJours) {
        return (tarifJournalier * 0.80) * nombreJours;
    }
}