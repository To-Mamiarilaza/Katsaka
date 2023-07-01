/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package recolte;

import champ.Parcelle;
import java.time.LocalDate;

/**
 *
 * @author to
 */
public class Recolte {
/// Les attributs du classe

    int idRecolte;
    LocalDate date;
    Parcelle parcelle;
    double nombreMais;
    double longueurMais;
    double poids;

/// Encapsulation et test unitaire
    public int getIdRecolte() {
        return idRecolte;
    }

    public void setIdRecolte(int idRecolte) throws Exception {
        if (idRecolte <= 0) {
            throw new Exception("L' ID du recolte ne doit pas être négative ou null");
        }
        this.idRecolte = idRecolte;
    }

    public void setIdRecolte(String idRecolte) throws Exception {
        if (idRecolte == null || idRecolte.trim().equals("")) {
            throw new Exception("L' ID du recolte ne doit pas être vide ou null");
        }
        setIdRecolte(Integer.valueOf(idRecolte));
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) throws Exception {
        if (date == null) {
            throw new Exception("La date de recolte ne doit pas être null");
        }
        this.date = date;
    }

    public void setDate(String date) throws Exception {
        if (date == null || date.trim().equals("")) {
            throw new Exception("La date du recolte ne doit pas être vide ou null");
        }
        LocalDate newDate = LocalDate.parse(date);
        setDate(newDate);
    }

    public Parcelle getParcelle() {
        return parcelle;
    }

    public void setParcelle(Parcelle parcelle) throws Exception {
        if (parcelle == null) {
            throw new Exception("La parcelle ne doit pas être null");
        }
        this.parcelle = parcelle;
    }
    
    public void setParcelle(String idParcelle) throws Exception {
        if (idParcelle == null || idParcelle.trim().equals("")) {
            throw new Exception("L' ID du parcelle ne doit pas être vide ou null");
        }
        Parcelle newParcelle = new Parcelle();
        newParcelle.setIdParcelle(Integer.valueOf(idParcelle));
        setParcelle(newParcelle);
    }

    public double getNombreMais() {
        return nombreMais;
    }

    public void setNombreMais(double nombreMais) throws Exception {
        if (nombreMais <= 0) {
            throw new Exception("Le nombre de mais ne doit pas être négative ou null");
        }
        this.nombreMais = nombreMais;
    }
    
    public void setNombreMais(String nombreMais) throws Exception {
        if (nombreMais == null || nombreMais.trim().equals("")) {
            throw new Exception("Le nombre de mais ne doit pas être vide ou null");
        }
        setNombreMais(Double.valueOf(nombreMais));
    }

    public double getLongueurMais() {
        return longueurMais;
    }

    public void setLongueurMais(double longueurMais) throws Exception {
        if (longueurMais <= 0) {
            throw new Exception("La longueur du mais ne doit pas être négative ou null");
        }
        this.longueurMais = longueurMais;
    }
    
    public void setLongueurMais(String longueurMais) throws Exception {
        if (longueurMais == null || longueurMais.trim().equals("")) {
            throw new Exception("La longueur du mais ne doit pas être vide ou null");
        }
        setLongueurMais(Double.valueOf(longueurMais));
    }

    public double getPoids() {
        return poids;
    }

    public void setPoids(double poids) throws Exception {
        if (poids <= 0) {
            throw new Exception("Le poids ne doit pas être négative ou null");
        }
        this.poids = poids;
    }
    
    public void setPoids(String poids) throws Exception {
        if (poids == null || poids.trim().equals("")) {
            throw new Exception("Le poids du mais ne doit pas être vide ou null");
        }
        setPoids(Double.valueOf(poids));
    }

/// Constructeur du classe recolte
    public Recolte() {
    }

    public Recolte(int idRecolte, LocalDate date, Parcelle parcelle, double nombreMais, double longueurMais, double poids) throws Exception {
        setIdRecolte(idRecolte);
        setDate(date);
        setParcelle(parcelle);
        setNombreMais(nombreMais);
        setLongueurMais(longueurMais);
        setPoids(poids);
    }
    
    public Recolte(String date, String parcelle, String nombreMais, String longueurMais, String poids) throws Exception {
        setDate(date);
        setParcelle(parcelle);
        setNombreMais(nombreMais);
        setLongueurMais(longueurMais);
        setPoids(poids);
    } 

}
