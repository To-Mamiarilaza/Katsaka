package anomalie;

import suivie.Suivie;

public class Anomalie {
    private int idAnomalie;
    private Suivie suivie;
    private String anomalieDescription;
    private TypeAnomalie type;
    private CategorieAnomalie category;
    private double avant;
    private double apres;

    public int getIdAnomalie() {
        return this.idAnomalie;
    }
    public void setIdAnomalie(int idAnomalie) throws Exception{
        if (idAnomalie <= 0) throw new Exception("L'ID de Anomalie doit être positive ou null");
        this.idAnomalie = idAnomalie;
    }
    public Suivie getSuivie() {
        return this.suivie;
    }
    public void setSuivie(Suivie suivie) {
        this.suivie = suivie;
    }
    public String getAnomalieDescription() {
        return this.anomalieDescription;
    }
    public void setAnomalieDescription(String anomalieDescription) {
        this.anomalieDescription = anomalieDescription;
    }
    public TypeAnomalie getType() {
        return this.type;
    }
    public void setType()
}
