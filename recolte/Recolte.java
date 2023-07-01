/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package recolte;

import champ.Parcelle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pgconnect.PGConnection;

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
    
    public int save(Connection connection) throws Exception {
        int idRecolte = 0;
        ResultSet rs = null;
        Statement stmt = null;
        boolean wasConnected = true;
        if (connection == null) {
            connection = PGConnection.getConnection();
            wasConnected = false;
        }
        try {
            String sql = "INSERT INTO recolte (nb_epi, longueur, poids, id_parcelle, date_recolte) VALUES ("+getNombreMais()+", "+getLongueurMais()+", "+getPoids()+", "+ getParcelle().getIdParcelle()+", "+getDate()+") RETURNING id";
            stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.executeBatch();
            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                idRecolte = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (!wasConnected) {
                connection.close();
            }
        }
        return idRecolte;
    }
    
    public static List<Recolte> findAll(Connection connection) throws Exception {
        ArrayList<Recolte> models = new ArrayList<>();
        boolean wasConnected = true;
        if (connection == null) {
            connection = PGConnection.getConnection();
            wasConnected = false;
        }
        
        String query = "SELECT id_recolte, nb_epi, longueur, poids, id_parcelle, date_recolte FROM recolte";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int idRecolte = rs.getInt("id_recolte");
                double npEpi = rs.getDouble("nb_epi");
                double longueur = rs.getDouble("longueur");
                double poids = rs.getDouble("poids");
                LocalDate dateRecolte = rs.getDate("date_recolte").toLocalDate();
                int id_parcelle = rs.getInt("id_parcelle");
                Parcelle parcelle = new Parcelle();
                // parcelle = Parcelle.findById(connection, id_parcelle);
                Recolte model = new Recolte(idRecolte, dateRecolte, parcelle, npEpi, longueur, poids);
                models.add(model);
            }
        }
        if (!wasConnected) {
            connection.close();
        }
        return models;
    } 
    

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

    public static void main(String[] args) {
        try {
            LocalDate date = LocalDate.now();
            Parcelle parcelle = new Parcelle();
            parcelle.setIdParcelle(3);
            double nombreMais = 456;
            double longueurMais = 15;
            double poids = 300;
            Recolte recolte = new Recolte(1, date, parcelle, nombreMais, longueurMais, poids);
            System.out.println(recolte.save(null));
        } catch (Exception ex) {
            Logger.getLogger(Recolte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
