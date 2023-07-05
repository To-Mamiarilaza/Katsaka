package anomalie;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import pgconnect.PGConnection;
import suivie.Suivie;

public abstract class Anomalie {

    private int idAnomalie;
    private TypeAnomalie type;
    private double avant;
    private double apres;

    public static final int DIMINUTION_PIED = 1;
    public static final int DIMINUTION_MAIS = 2;
    public static final int DIMINUTION_VERETE = 3;
    public static final int DIMINUTION_CROISSANCE = 4;

    public int getIdAnomalie() {
        return this.idAnomalie;
    }

    public void setIdAnomalie(int idAnomalie) throws Exception {
        if (idAnomalie <= 0) {
            throw new Exception("L'ID de Anomalie doit être positive ou null");
        }
        this.idAnomalie = idAnomalie;
    }

    public TypeAnomalie getType() {
        return this.type;
    }

    public void setType(TypeAnomalie type) {
        this.type = type;
    }

    public double getAvant() {
        return this.avant;
    }

    public void setAvant(double avant) throws Exception {
        if (avant < 0) {
            throw new Exception("La valeur avant ne pas être négative");
        }
        this.avant = avant;
    }

    public double getApres() {
        return this.apres;
    }

    public void setApres(double apres) throws Exception {
        if (apres < 0) {
            throw new Exception("La valeur apres ne pas être négative");
        }
        this.apres = apres;
    }

/// Constructeur du classe anomalie
    public Anomalie(int idAnomalie, TypeAnomalie type, double avant, double apres) throws Exception {
        setIdAnomalie(idAnomalie);
        setType(type);
        setAvant(avant);
        setApres(apres);
    }

    public Anomalie(int type, double avant, double apres) throws Exception {
        setType(new TypeAnomalie(type, ""));
        setAvant(avant);
        setApres(apres);
    }
    
    public Anomalie(int idAnomalie, int type, double avant, double apres) throws Exception {
        setIdAnomalie(idAnomalie);
        setType(new TypeAnomalie(type, ""));
        setAvant(avant);
        setApres(apres);
    }

    public Anomalie() {
        
    }
    
/// Fonction du classe anomalie
    public String getContent() {
        switch (this.getType().getIdTypeAnomalie()) {
            case 1:
                return "Le nombre de pied a diminué de <strong> " + this.getAvant() + " </strong> à <strong> " + this.getApres() + " </strong> ";
                
            case 2:
                return "Le nombre de mais a diminué de <strong> " + this.getAvant() + " </strong> à <strong> " + this.getApres()  + " </strong> ";
                
            case 3:
                return "Le taux de vereté a diminué de <strong> " + this.getAvant() + " </strong> à <strong> " + this.getApres()  + " </strong> ";
                
            case 4:
                return "Il y a un problème au niveau croissance , la moyenne est : <strong> " + this.getAvant() + " </strong> , mais ce qui ce passe : <strong> " + this.getApres() + " </strong>";
        }
        return null;
    }
    
    public abstract void save(Connection connection) throws Exception;
    
    

}
