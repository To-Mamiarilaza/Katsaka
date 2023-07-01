/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package champ;

import java.util.ArrayList;
import java.util.List;
import responsable.Responsable;
import suivie.Suivie;

/**
 *
 * @author to
 */
public class Parcelle {
/// Attributs du parcelle
    int idParcelle;
    String nom;
    Responsable responsable;
    List<Suivie> suivies;
    
/// Constructeur et test unitaire

    public int getIdParcelle() {
        return idParcelle;
    }

    public void setIdParcelle(int idParcelle) throws Exception {
        if (idParcelle <= 0) {
            throw new Exception("L' ID du parcelle ne doit pas être négative ou null !");
        }
        this.idParcelle = idParcelle;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) throws Exception {
        if (nom == null || nom.trim().equals("")) {
            throw new Exception("Le nom ne doit pas être null ou vide !");
        }
        this.nom = nom;
    }

    public Responsable getResponsable() {
        return responsable;
    }

    public void setResponsable(Responsable responsable) throws Exception {
        if (responsable == null) {
            throw new Exception("Le responsable ne doit pas être null !");
        }
        this.responsable = responsable;
    }

    public List<Suivie> getSuivies() {
        return suivies;
    }

    public void setSuivies(List<Suivie> suivies) throws Exception {
        if (suivies == null) {
            throw new Exception("Les suivies ne doit pas être null");
        }
        this.suivies = suivies;
    }
    
/// Les constructeurs du classe Parcellee

    public Parcelle() {
    }

    public Parcelle(int idParcelle, String nom, Responsable responsable, List<Suivie> suivies) throws Exception {
        setIdParcelle(idParcelle);
        setNom(nom);
        setResponsable(responsable);
        setSuivies(suivies);
    }
    
/// Les fonction du classe
    
    public List<Parcelle> findAllParcelles() {
        List<Parcelle> parcelles = new ArrayList<>();
        return null;
    }
    
    
}
