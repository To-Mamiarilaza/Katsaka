/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package anomalie;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import pgconnect.PGConnection;
import suivie.Suivie;

/**
 *
 * @author to
 */
public class AnomalieSuivie extends Anomalie {
/// Attribut du classe
    
    Suivie suivie;      // Le suivie source de l'anomalie
    
/// Encapsulation et controle de données

    public Suivie getSuivie() {
        return suivie;
    }

    public void setSuivie(Suivie suivie) throws Exception {
        if (suivie == null) {
            throw new Exception("Le suivie ne doit pas être null");
        }
        this.suivie = suivie;
    }
    
/// Constructeur du classe

    public AnomalieSuivie(int idAnomalie, Suivie suivie, TypeAnomalie type, double avant, double apres) throws Exception {
        super(idAnomalie, type, avant, apres);
        setSuivie(suivie);
    }

    public AnomalieSuivie() {
    }

    public AnomalieSuivie(Suivie suivie, int type, double avant, double apres) throws Exception {
        super(type, avant, apres);
        this.suivie = suivie;
    }

    public AnomalieSuivie(int idAnomalie, Suivie suivie, int type, double avant, double apres) throws Exception {
        super(idAnomalie, type, avant, apres);
        this.suivie = suivie;
    }
    
/// Fonction du classe 
    
    public void save(Connection connection) throws Exception {
        boolean closeable = false;
        if (connection == null) {
            connection = PGConnection.getConnection();
            closeable = true;
        }

        Statement statement = null;
        ResultSet resultset = null;
        try {
            String sql = "INSERT INTO anomalie_suivie (id_suivie, id_type_anomalie, avant, apres) VALUES (%d, %d, %s, %s)";
            sql = sql.format(sql, this.getSuivie().getIdSuivie(), this.getType().getIdTypeAnomalie(), this.getAvant(), this.getApres());
            System.out.println(sql);
            
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            
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
    
    
    
}
