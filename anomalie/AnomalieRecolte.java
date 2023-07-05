/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package anomalie;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import pgconnect.PGConnection;
import recolte.Recolte;

/**
 *
 * @author to
 */
public class AnomalieRecolte extends Anomalie {
/// Attributs du classe
    Recolte recolte;    // La recolte source de l'anomalie
    
/// Encapsulation et controle de données

    public Recolte getRecolte() {
        return recolte;
    }

    public void setRecolte(Recolte recolte) throws Exception {
        if (recolte == null) {
            throw new Exception("La recolte ne doit pas être null");
        }
        this.recolte = recolte;
    }
    
/// Constructeur

    public AnomalieRecolte(Recolte recolte, int idAnomalie, TypeAnomalie type, double avant, double apres) throws Exception {
        super(idAnomalie, type, avant, apres);
        setRecolte(recolte);
    }

    public AnomalieRecolte(Recolte recolte, int type, double avant, double apres) throws Exception {
        super(type, avant, apres);
        setRecolte(recolte);
    }

    public AnomalieRecolte(Recolte recolte, int idAnomalie, int type, double avant, double apres) throws Exception {
        super(idAnomalie, type, avant, apres);
        setRecolte(recolte);
    }

    public AnomalieRecolte() {
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
            String sql = "INSERT INTO anomalie_recolte (id_recolte, id_type_anomalie, avant, apres) VALUES (%d, %d, %s, %s)";
            sql = sql.format(sql, this.getRecolte().getIdRecolte(), this.getType().getIdTypeAnomalie(), this.getAvant(), this.getApres());
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
