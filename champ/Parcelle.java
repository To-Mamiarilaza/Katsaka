/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package champ;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pgconnect.PGConnection;
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

    public Parcelle(int idParcelle, String nom) throws Exception {
        setIdParcelle(idParcelle);
        setNom(nom);
    }

/// Les fonction du classe
    // Prendre le dernier suivie
    public Suivie getLastSuivie() {
        if (getSuivies() == null || getSuivies().get(0) == null) {
            return null;
        }
        return getSuivies().get(0);    // Le dernier en premier
    }

    // Nombre de suivie effectué
    public int getNombreSuivie() {
        if (getSuivies() == null) {
            return 0;
        }
        return getSuivies().size();
    }

    // Prendre tous les parcelles avec leur suivies ordonnées
    public static List<Parcelle> findAllParcelles() throws Exception {
        List<Parcelle> parcelles = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultset = null;
        try {
            connection = PGConnection.getConnection();
            statement = connection.createStatement();
            String sql = "SELECT * FROM v_parcelle_et_responsable";
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                Responsable responsable = new Responsable(result.getInt("id_responsable"), result.getString("nom_responsable"));
                Parcelle parcelle = new Parcelle(result.getInt("id_parcelle"), result.getString("nom_parcelle"));
                parcelle.setResponsable(responsable);
                parcelles.add(parcelle);

                List<Suivie> suivies = Suivie.findByIdParcelle(connection, parcelle.getIdParcelle());
                parcelle.setSuivies(suivies);
            }

            return parcelles;
        } catch (Exception e) {
            throw e;
        } finally {
            if (resultset != null) {
                resultset.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    // Find parcelle par ID
    // Prendre tous les parcelles avec leur suivies ordonnées
    public static Parcelle findById(Connection connection, int idParcelle) throws Exception {
        List<Parcelle> parcelles = new ArrayList<>();
        Statement statement = null;
        ResultSet resultset = null;
        try {
            connection = PGConnection.getConnection();
            statement = connection.createStatement();
            String sql = "SELECT * FROM v_parcelle_et_responsable WHERE id_parcelle = " + idParcelle;
            ResultSet result = statement.executeQuery(sql);

            if (result.next()) {
                Responsable responsable = new Responsable(result.getInt("id_responsable"), result.getString("nom_responsable"));
                Parcelle parcelle = new Parcelle(result.getInt("id_parcelle"), result.getString("nom_parcelle"));
                parcelle.setResponsable(responsable);
                parcelles.add(parcelle);

                List<Suivie> suivies = Suivie.findByIdParcelle(connection, parcelle.getIdParcelle());
                parcelle.setSuivies(suivies);
                return parcelle;
            } else {
                return null;
            }

        } catch (Exception e) {
            if (connection != null) {
                connection.close();
            }
            throw e;
        } finally {
            if (resultset != null) {
                resultset.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
    }

/// Test unitaire
    public static void main(String[] args) throws Exception {
//        List<Parcelle> listes = findAllParcelles();
//        for (Parcelle liste : listes) {
//            System.out.println("ID : " + liste.getIdParcelle() + " Nom " + liste.getNom() + " Responsable " + liste.getResponsable().getNom());
//
//            for (Suivie suivie : liste.getSuivies()) {
//                System.out.println("ID suivie : " + suivie.getIdSuivie());
//            }
//        }

        Parcelle test = Parcelle.findById(PGConnection.getConnection(), 3);
        System.out.println("ID : " + test.getIdParcelle() + " Nom " + test.getNom() + " Responsable " + test.getResponsable().getNom());
        for (Suivie suivie : test.getSuivies()) {
            System.out.println("ID suivie : " + suivie.getIdSuivie());
        }

    }

}
