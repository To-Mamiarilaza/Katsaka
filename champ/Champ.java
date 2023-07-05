/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package champ;

import anomalie.Anomalie;
import anomalie.AnomalieSuivie;
import java.sql.Connection;
import java.util.List;
import recolte.Recolte;
import responsable.Responsable;

/**
 *
 * @author to
 */
public class Champ {
/// Les attributs du classe
    List<Responsable> responsables;
    List<Parcelle> parcelles;
    List<Recolte> effectue;
    List<Recolte> prevision;
    
/// Test unitaire des champs

    public List<Responsable> getResponsables() {
        return responsables;
    }

    public void setResponsables(List<Responsable> responsables) throws Exception {
        if (responsables == null) {
            throw new Exception("Les responsables ne doivent pas être null");
        }
        this.responsables = responsables;
    }

    public List<Parcelle> getParcelles() {
        return parcelles;
    }

    public void setParcelles(List<Parcelle> parcelles) throws Exception {
        if (parcelles == null) {
            throw new Exception("Les parcelles ne doivent pas être null");
        }
        this.parcelles = parcelles;
    }

    public List<Recolte> getEffectue() {
        return effectue;
    }

    public void setEffectue(List<Recolte> effectue) throws Exception {
        if (effectue == null) {
            throw new Exception("Les recoltes effectue ne doivent pas être null");
        }
        this.effectue = effectue;
    }

    public List<Recolte> getPrevision() {
        return prevision;
    }

    public void setPrevision(List<Recolte> prevision) throws Exception {
        if (prevision == null) {
            throw new Exception("Les prevision de recolte doivent pas être null");
        }
        this.prevision = prevision;
    }
    
/// Constructeur du classe Champ

    public Champ() {
    }

    public Champ(List<Responsable> responsables, List<Parcelle> parcelles, List<Recolte> effectue, List<Recolte> prevision) throws Exception {
        setResponsables(responsables);
        setParcelles(parcelles);
        setEffectue(effectue);
        setPrevision(prevision);
    }
    
/// Fonction du classe Champ
    
    // Initialisation du champ 
    public void initChamp() throws Exception {
        List<Parcelle> parcelles = Parcelle.findAllParcelles();
        setParcelles(parcelles);
    }
    
    public double getMoyenneCroissance(Parcelle parcelleCible) {
        double moyenne = 0;
        for (Parcelle parcelle : parcelles) {
            if (parcelleCible != parcelle) {
                moyenne += parcelle.getSuivies().get(0).getLongueurMais()  - parcelle.getSuivies().get(1).getLongueurMais();    // Les deux derniers suivies
            }
        }
        return moyenne / (getParcelles().size() - 1);
    }
    
    public void checkCroissanceAnomalie() throws Exception {
        Connection connection = pgconnect.PGConnection.getConnection();
        for (Parcelle parcelle : parcelles) {
            double moyenneCroissance = getMoyenneCroissance(parcelle);      // Moyenne de croissance entre les parcelles autre que cible
            System.out.println("Pour parcelle : " + parcelle.getNom() + " moyenne = " + moyenneCroissance);
            double croissance = parcelle.getSuivies().get(0).getLongueurMais()  - parcelle.getSuivies().get(1).getLongueurMais();
            if (croissance < moyenneCroissance) {
               Anomalie anomalie = new AnomalieSuivie(parcelle.getLastSuivie(), 4, moyenneCroissance, croissance);
               anomalie.save(connection);
            }
        }
        connection.commit();
        connection.rollback();
    }
    
    public static void main(String[] args) throws Exception {
        Champ champ = new Champ();
        champ.initChamp();
        champ.checkCroissanceAnomalie();        // Vérification des anomalies de croissances
    }
}
