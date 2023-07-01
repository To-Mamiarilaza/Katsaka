/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pgconnect;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author to
 */
public class PGConnection {
    public static Connection getConnection() throws Exception {     
        // Fonction qui renvoie la connection vers la base : 
            String database = "gestion_mais";       // Nom de la base
            String user = "postgres";       // User dans postgres
            String mdp = "malalaniaina";       // Mot de passe
            
            // Charge la classe de driver
            Class.forName("org.postgresql.Driver");
            
            // Creation de l'objet de connection
            Connection connection = DriverManager.getConnection("jdbc:postgresql://192.168.10.211:5432/" + database, user,  mdp);
            
            connection.setAutoCommit(false);
            return connection;
    }
    
    public static void main(String[] args) throws Exception {
        System.out.println(PGConnection.getConnection());
    }
}
