/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package suivie;

import champ.Parcelle;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import pgconnect.PGConnection;
import responsable.Responsable;

/**
 *
 * @author to
 */
public class Suivie {
/// Attributs du classe suivie
    int idSuivie;
    LocalDate date;
    double nombreTige;
    double nombreMais;
    double longueurMais;
    double verete;
    Parcelle parcelle;
    
/// Encapsulation et controle unitaire de données

    public int getIdSuivie() {
        return idSuivie;
    }

    public void setIdSuivie(int idSuivie) throws Exception {
        if (idSuivie <= 0) {
            throw new Exception("L' ID du suivie ne doit pas être négative ou null");
        }
        this.idSuivie = idSuivie;
    }
    
    public void setIdSuivie(String idSuivie) throws Exception {
        if (idSuivie == null || idSuivie.trim().equals("")) {
            throw new Exception("L'id du suivie ne doit pas être vide ou null");
        }
        setIdSuivie(Integer.valueOf(idSuivie));
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) throws Exception {
        if (date == null) {
            throw new Exception("La date ne doit pas être null");
        }
        this.date = date;
    }
    
    public void setDate(String date) throws Exception {
        if (date == null || date.trim().equals(" ")) {
            throw new Exception("La date ne doit pas être vide ou null");
        }
        setDate(LocalDate.parse(date));
    }

    public double getNombreTige() {
        return nombreTige;
    }

    public void setNombreTige(double nombreTige) throws Exception {
        if (nombreTige < 0) {
            throw new Exception("Le nombre de tige ne doit pas être négative ou null !");
        }
        this.nombreTige = nombreTige;
    }
    
    public void setNombreTige(String nombreTige) throws Exception {
        if (nombreTige == null || nombreTige.trim().equals("")) {
            throw new Exception("Le nombre de tige ne doit pas être vide ou null !");
        }
        setNombreMais(Double.valueOf(nombreTige));
    }

    public double getNombreMais() {
        return nombreMais;
    }

    public void setNombreMais(double nombreMais) throws Exception {
        if (nombreMais < 0) {
            throw new Exception("Le nombre de mais ne doit pas être négative ou null !");
        }
        this.nombreMais = nombreMais;
    }
    
    public void setNombreMais(String nombreMais) throws Exception {
        if (nombreMais == null || nombreMais.trim().equals("")) {
            throw new Exception("Le nombre de mais ne doit pas être vide ou null !");
        }
        setNombreMais(Double.valueOf(nombreMais));
    }

    public double getLongueurMais() {
        return longueurMais;
    }

    public void setLongueurMais(double longueurMais) throws Exception {
        if (longueurMais < 0) {
            throw new Exception("La longueur du mais ne doit pas être négative ou null !");
        }
        this.longueurMais = longueurMais;
    }
    
    public void setLongueurMais(String longueurMais) throws Exception {
        if (longueurMais == null || longueurMais.trim().equals("")) {
            throw new Exception("Le longueur du mais ne doit pas être vide ou null !");
        }
        setLongueurMais(Double.valueOf(longueurMais));
    }

    public double getVerete() {
        return verete;
    }

    public void setVerete(double verete) throws Exception {
        if (verete < 0) {
            throw new Exception("La verete ne doit pas être négative ou null !");
        }
        this.verete = verete;
    }
    
    public void setVerete(String verete) throws Exception {
        if (verete == null || verete.trim().equals("")) {
            throw new Exception("La verete ne doit pas être vide ou null !");
        }
        setVerete(Double.valueOf(verete));
    }

    public Parcelle getParcelle() {
        return parcelle;
    }

    public void setParcelle(Parcelle parcelle) throws Exception {
        if (parcelle == null) {
            throw new Exception("La parcelle ne doit pas être null !");
        }
        this.parcelle = parcelle;
    }
    
    public void setParcelle(String idParcelle) throws Exception {
        if (idParcelle == null || idParcelle.trim().equals("")) {
            throw new Exception("L' ID du parcelle ne doit pas être null ou vide !");
        }
        Parcelle newParcelle = new Parcelle();
        newParcelle.setIdParcelle(Integer.valueOf(idParcelle));
        setParcelle(newParcelle);
    }
    
/// Constructeur du classe Suivie

    public Suivie() {
    }

    public Suivie(int idSuivie, LocalDate date, double nombreTige, double nombreMais, double longueurMais, double verete, Parcelle parcelle) throws Exception {
        setIdSuivie(idSuivie);
        setDate(date);
        setNombreTige(nombreTige);
        setNombreMais(nombreMais);
        setLongueurMais(longueurMais);
        setVerete(verete);
        setParcelle(parcelle);
    }
    
    public Suivie(int idSuivie, LocalDate date, double nombreTige, double nombreMais, double longueurMais, double verete) throws Exception {
        setIdSuivie(idSuivie);
        setDate(date);
        setNombreTige(nombreTige);
        setNombreMais(nombreMais);
        setLongueurMais(longueurMais);
        setVerete(verete);
    }
    
    public Suivie(String date, String nombreTige, String nombreMais, String longueurMais, String verete, String idParcelle) throws Exception {
        setDate(date);
        setNombreTige(nombreTige);
        setNombreMais(nombreMais);
        setLongueurMais(longueurMais);
        setVerete(verete);
        setParcelle(idParcelle);
    }
    
    
    
/// Fonction du classe suivie
    // Prendre tous les suivies du parcelle le plus récent le premier
    public static List<Suivie> findByIdParcelle(Connection connection, int idParcelle) throws Exception {
        List<Suivie> suivies = new ArrayList<>();
        Statement statement = null;
        ResultSet resultset = null;
        try {
            connection = PGConnection.getConnection();
            statement = connection.createStatement();
            String sql = "SELECT * FROM suivie WHERE id_parcelle = " + idParcelle + " ORDER BY date_suivie DESC";
            ResultSet result = statement.executeQuery(sql);
            
            while (result.next()) {         
                Suivie suivie = new Suivie(result.getInt("id_suivie"), result.getDate("date_suivie").toLocalDate(), result.getDouble("nb_pied"), result.getDouble("nb_epi"), result.getDouble("longueur_epi"), result.getDouble("verete"));
                suivies.add(suivie);
            }
            
            return suivies;
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
}
