/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package anomalie;

/**
 *
 * @author tiavi
 */
public class TypeAnomalie {
    private int idTypeAnomalie;
    private String anomalie;

    public TypeAnomalie() {}
    public TypeAnomalie(int idTypeAnomalie, String anomalie) {
        setIdTypeAnomalie(idTypeAnomalie);
        setAnomalie(anomalie);
    }
    
    public int getIdTypeAnomalie() {
        return idTypeAnomalie;
    }

    public void setIdTypeAnomalie(int idTypeAnomalie) {
        this.idTypeAnomalie = idTypeAnomalie;
    }

    public String getAnomalie() {
        return anomalie;
    }

    public void setAnomalie(String anomalie) {
        this.anomalie = anomalie;
    }
    
}
