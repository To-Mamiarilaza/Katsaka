/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package suivie;

import anomalie.Anomalie;
import anomalie.AnomalieSuivie;
import champ.Parcelle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    List<Anomalie> anomalies;

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
        setNombreTige(Double.valueOf(nombreTige));
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

    public List<Anomalie> getAnomalies() {
        return anomalies;
    }

    public void setAnomalies(List<Anomalie> anomalies) throws Exception {
        if (anomalies == null) {
            throw new Exception("Le tableau d'anomalie ne doit pas être null");
        }
        this.anomalies = anomalies;
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
    // Prendre tous les anomalies liée au suivie
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
            
            String sql = "SELECT * FROM anomalie_suivie WHERE id_suivie = %d";
            sql = sql.format(sql, getIdSuivie());
            System.out.println(sql);

            statement = connection.createStatement();
            resultset = statement.executeQuery(sql);

            while (resultset.next()) {                
                Anomalie nouveau = new AnomalieSuivie(resultset.getInt("id_anomalie"), this, resultset.getInt("id_type_anomalie"), resultset.getDouble("avant"), resultset.getDouble("apres"));
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

    // Prendre le dernier ID du suivie
    public void getLastId(Connection connection) throws Exception {
        boolean closeable = false;
        if (connection == null) {
            connection = PGConnection.getConnection();
            closeable = true;
        }

        Statement statement = null;
        ResultSet resultset = null;
        try {
            String sql = "SELECT MAX(id_suivie) FROM suivie";
            statement = connection.createStatement();
            resultset = statement.executeQuery(sql);

            if (resultset.next()) {
                setIdSuivie(resultset.getInt(1));
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

    // Insertion dans la base
    public void save(Connection connection) throws Exception {
        boolean closeable = false;
        if (connection == null) {
            connection = PGConnection.getConnection();
            closeable = true;
        }

        Statement statement = null;
        ResultSet resultset = null;
        try {
            String sql = "INSERT INTO suivie (id_parcelle, nb_pied, nb_epi, longueur_epi, verete, date_suivie) VALUES (%d, %s, %s, %s, %s, '%s')";
            sql = sql.format(sql, this.getParcelle().getIdParcelle(), this.getNombreTige(), this.getNombreMais(), this.getLongueurMais(), this.getVerete(), this.getDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
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

    // Vérification des anomalies
    public void checkAnomalie(Connection connection, Suivie lastSuivie) throws Exception {
        if (this.getNombreTige() < lastSuivie.getNombreTige()) {        // Diminution nombre de pied
            AnomalieSuivie anomalie = new AnomalieSuivie(this, Anomalie.DIMINUTION_PIED, lastSuivie.getNombreTige(), this.getNombreTige());
            anomalie.save(connection);
        }

        if (this.getNombreMais() < lastSuivie.getNombreMais()) {        // Diminution nombre de pied
            AnomalieSuivie anomalie = new AnomalieSuivie(this, Anomalie.DIMINUTION_MAIS, lastSuivie.getNombreMais(), this.getNombreMais());
            anomalie.save(connection);
        }

        if (this.getVerete() < lastSuivie.getVerete()) {        // Diminution nombre de pied
            AnomalieSuivie anomalie = new AnomalieSuivie(this, Anomalie.DIMINUTION_VERETE, lastSuivie.getVerete(), this.getVerete());
            anomalie.save(connection);
        }

//        if ((this.getLongueurMais() < lastSuivie.getLongueurMais()) || (this.getLongueurMais() == lastSuivie.getLongueurMais() && this.getLongueurMais() < this.getParcelle().getLongueurMais())) {        // Diminution nombre de pied
//            AnomalieSuivie anomalie = new AnomalieSuivie(this, Anomalie.DIMINUTION_CROISSANCE, lastSuivie.getLongueurMais(), this.getLongueurMais());
//            anomalie.save(connection);
//        }
    }

    // Insertion d'un nouveau suivie
    public static void insertSuivie(String idParcelle, String date, String nombreTige, String longueurMais, String nombreMais, String verete) throws Exception {
        Connection connection = PGConnection.getConnection();
        Suivie suivie = new Suivie(date, nombreTige, nombreMais, longueurMais, verete, idParcelle);
        Parcelle parcelle = Parcelle.findById(connection, suivie.getParcelle().getIdParcelle());
        suivie.setParcelle(parcelle);
        Suivie lastSuivie = parcelle.getLastSuivie();

        if (lastSuivie != null && (suivie.getDate().isBefore(lastSuivie.getDate()) || suivie.getDate().isEqual(lastSuivie.getDate()))) {
            connection.close();
            throw new Exception("La date du nouveau suivie ne doit pas être avant ou égale au dernier suivie");
        }

        suivie.save(connection);

        if (lastSuivie != null) {
            suivie.checkAnomalie(connection, lastSuivie);
        }

        connection.commit();
        connection.close();
    }

    // Prendre tous les suivies du parcelle le plus récent le premier
    public static List<Suivie> findByIdParcelle(Connection connection, Parcelle parcelle) throws Exception {
        List<Suivie> suivies = new ArrayList<>();
        Statement statement = null;
        ResultSet resultset = null;
        try {
            connection = PGConnection.getConnection();
            statement = connection.createStatement();
            String sql = "SELECT * FROM suivie WHERE id_parcelle = " + parcelle.getIdParcelle() + " ORDER BY date_suivie DESC";
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                Suivie suivie = new Suivie(result.getInt("id_suivie"), result.getDate("date_suivie").toLocalDate(), result.getDouble("nb_pied"), result.getDouble("nb_epi"), result.getDouble("longueur_epi"), result.getDouble("verete"));
                suivies.add(suivie);
                suivie.setParcelle(parcelle);
                suivie.findAllAnomalie(connection);     // Prendre tous les anomalies concernant le suivie
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

    public static void main(String[] args) throws Exception {
        Suivie.insertSuivie("3", "2023-07-20", "10", "20", "2", "40");
    }
}
