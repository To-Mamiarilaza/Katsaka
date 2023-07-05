/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prevision;

import champ.Parcelle;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import recolte.Recolte;
import suivie.Suivie;

/**
 *
 * @author to
 */
public class Prevision extends Recolte {
/// Attribut du classe prevision
    public Etat etatPrevision;
    
    public enum Etat {
        PREVISION,
        RECOLTE
    };
    
/// Constructeur du classe

    public Prevision(Etat etatPrevision, int idRecolte, LocalDate date, Parcelle parcelle, double nombreMais, double longueurMais, double poids) throws Exception {
        super(idRecolte, date, parcelle, nombreMais, longueurMais, poids);
        this.etatPrevision = etatPrevision;
    }

    public Prevision(Etat etatPrevision, String date, String parcelle, String nombreMais, String longueurMais, String poids) throws Exception {
        super(date, parcelle, nombreMais, longueurMais, poids);
        this.etatPrevision = etatPrevision;
    }

    public Prevision() {
    }
    
/// Fonction du classe 
    
    // Calcul la moyenne de prevision
    public static Recolte calculMoyennePrevision(List<Recolte> recoltes) throws Exception {
        Recolte moyenne = new Recolte();
        for (Recolte recolte : recoltes) {
            moyenne.setNombreMais(moyenne.getNombreMais() + recolte.getNombreMais());
            moyenne.setLongueurMais(moyenne.getLongueurMais()+ recolte.getLongueurMais());
            moyenne.setPoids(moyenne.getPoids()+ recolte.getPoids());
        }
        
        int nbRecolte = recoltes.size();
        moyenne.setNombreMais(moyenne.getNombreMais() / nbRecolte);
        moyenne.setLongueurMais(moyenne.getLongueurMais() / nbRecolte);
        moyenne.setPoids(moyenne.getPoids()/ nbRecolte);
        return moyenne;
    }
    
    // Trouver tous les recoltes déja réaliser
    public static List<Recolte> getEffectuedRecolte(List<Parcelle> parcelles) {
        List<Recolte> recoltes = new ArrayList<>();
        for (Parcelle parcelle : parcelles) {
            if (parcelle.getRecolte() != null) {
                recoltes.add(parcelle.getRecolte());
            }
        }
        return recoltes;
    }
    
    // Calcul du prevision
    public static Prevision calculPrevision(Parcelle parcelle, Recolte moyenne) throws Exception {
        if (parcelle.getRecolte() != null) {
            Prevision prevision = new Prevision(Etat.RECOLTE, parcelle.getRecolte().getIdRecolte(), parcelle.getRecolte().getDate(), parcelle, parcelle.getRecolte().getNombreMais(), parcelle.getRecolte().getLongueurMais(), parcelle.getRecolte().getPoids());
            prevision.setParcelle(parcelle);
            return prevision;
        } else {
            Suivie lastSuivie = parcelle.getLastSuivie();
            if (lastSuivie == null) {
                Prevision prevision = new Prevision(Etat.PREVISION, 0, LocalDate.now(), parcelle, 0, 0, 0);
                prevision.setParcelle(parcelle);
                return prevision;
            }
            
            double poids = ((lastSuivie.getNombreTige() * lastSuivie.getNombreMais() * lastSuivie.getLongueurMais()) * moyenne.getPoids()) / (moyenne.getNombreMais() * moyenne.getLongueurMais());
            Prevision prevision = new Prevision(Etat.PREVISION, 0, LocalDate.now(), parcelle, (lastSuivie.getNombreTige() * lastSuivie.getNombreMais()), lastSuivie.getLongueurMais(), poids);
            prevision.setParcelle(parcelle);
            return prevision;
        }
    }
    
    // Calcul du prevision totale
    public static Prevision calculPrevisionTotal(List<Prevision> previsions) throws Exception {
        double nombreMais = 0;
        double poids = 0;
        
        for (Prevision prevision : previsions) {
            nombreMais += prevision.getNombreMais();
            poids += prevision.getPoids();
        }
        
        Prevision total = new Prevision();
        total.setNombreMais(nombreMais);
        total.setPoids(poids);
        
        return total;
    }
    
    // Calcul du prevision par parcelle
    public static List<Prevision> getAllPrevision() throws Exception {
        List<Prevision> previsions = new ArrayList<>();
        
        List<Parcelle> parcelles = Parcelle.findAllParcelles();
        List<Recolte> recolteEffectued = Prevision.getEffectuedRecolte(parcelles);
        
        if (recolteEffectued.size() == 0) {
            return null;
        }
        
        Recolte moyennePrevision = Prevision.calculMoyennePrevision(recolteEffectued);
        
        for (Parcelle parcelle : parcelles) {
            previsions.add(Prevision.calculPrevision(parcelle, moyennePrevision));
        }
        
        return previsions;
    }
    
    public static void main(String[] args) throws Exception {
        List<Prevision> previsions = Prevision.getAllPrevision();
        for (Prevision prevision : previsions) {
            System.out.println("Parcelle " + prevision.getParcelle().getIdParcelle() +  " Nombre de mais : " + prevision.getNombreMais() + " longueur : " + prevision.getLongueurMais() + " Poids : " + prevision.getPoids());
        }
        
        Prevision total = Prevision.calculPrevisionTotal(previsions);
        System.out.println("Total : nombre de mais : " + total.getNombreMais() + " poids : " + total.getPoids());
    }
}
