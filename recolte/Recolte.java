/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package recolte;

import anomalie.Anomalie;
import anomalie.AnomalieRecolte;
import anomalie.AnomalieSuivie;
import champ.Parcelle;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import pgconnect.PGConnection;
import suivie.Suivie;

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
    List<Anomalie> anomalies;

/// Encapsulation et test unitaire
    public int getIdRecolte() {
        return idRecolte;
    }

    public void setIdRecolte(int idRecolte) throws Exception {
        if (idRecolte < 0) {
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
        if (nombreMais < 0) {
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
        if (longueurMais < 0) {
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
        if (poids < 0) {
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

    public List<Anomalie> getAnomalies() {
        return anomalies;
    }

    public void setAnomalies(List<Anomalie> anomalies) throws Exception {
        if (anomalies == null) {
            throw new Exception("Les anomalies ne doivent pas être null");
        }
        this.anomalies = anomalies;
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

/// Fonction du classe
    
    public void findAllAnomalie(Connection connection) throws Exception {
        boolean closeable = false;
        if (connection == null) {
            connection = PGConnection.getConnection();
            closeable = true;
        }

        Statement statement = null;
        ResultSet resultset = null;
        try {
            List<Anomalie> anomalies = new ArrayList<>();
            
            String sql = "SELECT * FROM anomalie_recolte WHERE id_recolte = %d";
            sql = sql.format(sql, getIdRecolte());
            System.out.println(sql);

            statement = connection.createStatement();
            resultset = statement.executeQuery(sql);

            while (resultset.next()) {                
                Anomalie nouveau = new AnomalieRecolte(this, resultset.getInt("id_anomalie"), resultset.getInt("id_type_anomalie"), resultset.getDouble("avant"), resultset.getDouble("apres"));
                anomalies.add(nouveau);
            }
            
            setAnomalies(anomalies);
            
        } catch (Exception e) {
            if (connection != null) {
                connection.rollback();
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
            if (connection != null && closeable == true) {
                connection.commit();
                connection.close();
            }
        }
    }
    
    // Trouver la recolte d'une parcelle
    public static Recolte findRecolte(Connection connection, Parcelle parcelle) throws Exception {
        boolean closeable = false;
        if (connection == null) {
            connection = PGConnection.getConnection();
            closeable = true;
        }

        Statement statement = null;
        ResultSet resultset = null;
        try {
            String sql = "SELECT * FROM recolte WHERE id_parcelle = %d";
            sql = String.format(sql, parcelle.getIdParcelle());
            
            statement = connection.createStatement();
            resultset = statement.executeQuery(sql);

            if (resultset.next()) {
                return new Recolte(resultset.getInt("id_recolte"), resultset.getDate("date_recolte").toLocalDate(), parcelle, resultset.getDouble("nb_epi"), resultset.getDouble("longueur"), resultset.getDouble("poids"));
            }
            
            return null;
            
        } catch (Exception e) {
            if (connection != null) {
                connection.rollback();
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
            if (connection != null && closeable == true) {
                connection.commit();
                connection.close();
            }
        }
    }
    
    public void getLastId(Connection connection) throws Exception {
        boolean closeable = false;
        if (connection == null) {
            connection = PGConnection.getConnection();
            closeable = true;
        }

        Statement statement = null;
        ResultSet resultset = null;
        try {
            String sql = "SELECT MAX(id_recolte) FROM recolte";
            statement = connection.createStatement();
            resultset = statement.executeQuery(sql);

            if (resultset.next()) {
                setIdRecolte(resultset.getInt(1));
            }

        } catch (Exception e) {
            if (connection != null) {
                connection.rollback();
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
            if (connection != null && closeable == true) {
                connection.commit();
                connection.close();
            }
        }
    }

    public void save(Connection connection) throws Exception {
        boolean closeable = false;
        if (connection == null) {
            connection = PGConnection.getConnection();
            closeable = true;
        }

        Statement statement = null;
        ResultSet resultset = null;
        try {
            String sql = "INSERT INTO recolte (nb_epi, longueur, poids, id_parcelle, date_recolte) VALUES (%s, %s, %s, %d, '%s')";
            sql = sql.format(sql, this.getNombreMais(), this.getLongueurMais(), this.getPoids(), this.getParcelle().getIdParcelle(), this.getDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
            System.out.println(sql);

            statement = connection.createStatement();
            statement.executeUpdate(sql);

            getLastId(connection);  // Prends et affecte le dernier ID

        } catch (Exception e) {
            if (connection != null) {
                connection.rollback();
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
            if (connection != null && closeable == true) {
                connection.commit();
                connection.close();
            }
        }
    }

    public void checkAnomalie(Connection connection, Suivie lastSuivie) throws Exception {
        if (this.getNombreMais() < (lastSuivie.getNombreMais() * lastSuivie.getNombreTige())) {        // Diminution nombre de pied
            AnomalieRecolte anomalie = new AnomalieRecolte(this, Anomalie.DIMINUTION_MAIS, (lastSuivie.getNombreMais() * lastSuivie.getNombreTige()), this.getNombreMais());
            anomalie.save(connection);
        }

        if ((this.getLongueurMais() < lastSuivie.getLongueurMais()) || (this.getLongueurMais() == lastSuivie.getLongueurMais() && this.getLongueurMais() < this.getParcelle().getLongueurMais())) {        // Diminution nombre de pied
            AnomalieRecolte anomalie = new AnomalieRecolte(this, Anomalie.DIMINUTION_CROISSANCE, lastSuivie.getLongueurMais(), this.getLongueurMais());
            anomalie.save(connection);
        }
    }

    public static void insertNewRecolte(String date, String idParcelle, String nombreMais, String longueurMais, String poids) throws Exception {
        Connection connection = PGConnection.getConnection();

        Recolte recolte = new Recolte(date, idParcelle, nombreMais, longueurMais, poids);
        Parcelle parcelle = Parcelle.findById(connection, recolte.getParcelle().getIdParcelle());
        Suivie lastSuivie = parcelle.getLastSuivie();

        if (lastSuivie == null) {
            throw new Exception("Vous devez faire tout d'abord un suivie");
        }

        if (recolte.getDate().isBefore(lastSuivie.getDate())) {
            connection.close();
            throw new Exception("La date du recolte ne doit pas être inférieur au date du dernier suivie");
        }

        recolte.save(connection);

        recolte.checkAnomalie(connection, lastSuivie);      // Vérification des anomalies de recoltes

        connection.commit();
        connection.close();

    }

    public static void main(String[] args) throws Exception {
        Recolte.insertNewRecolte("2023-09-10", "3", "8", "23", "600");
    }

}
