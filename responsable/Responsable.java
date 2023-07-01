/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsable;

import champ.Parcelle;
import java.util.List;

/**
 *
 * @author to
 */
public class Responsable {
/// Attribut du classe 
    int idResponsable;
    String nom;
    List<Parcelle> parcelles;
    
/// Encapsulation et controle unitaire

    public int getIdResponsable() {
        return idResponsable;
    }

    public void setIdResponsable(int idResponsable) throws Exception {
        if (idResponsable <= 0) throw new Exception("L'ID du responsable ne doit pas être négative ou null !");
        this.idResponsable = idResponsable;
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

    public List<Parcelle> getParcelles() {
        return parcelles;
    }

    public void setParcelles(List<Parcelle> parcelles) throws Exception {
        if (parcelles == null) {
            throw new Exception("Les parcelles du responsable ne doit pas être null !");
        }
        this.parcelles = parcelles;
    }
    
/// Constructeur du classe 

    public Responsable() {
    }

    public Responsable(int idResponsable, String nom, List<Parcelle> parcelles) throws Exception {
        setIdResponsable(idResponsable);
        setNom(nom);
        setParcelles(parcelles);
    }
    
    public Responsable(int idResponsable, String nom) throws Exception {
        setIdResponsable(idResponsable);
        setNom(nom);
    }
    
    
    
}
