/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package champ;

import java.util.List;
import recolte.Recolte;
import responsable.Responsable;

/**
 *
 * @author to
 */
public class Champ {
/// Les attributs du classe
    List<Responsable> responsables;
    List<Parcelle> parcelles;
    List<Recolte> effectue;
    List<Recolte> prevision;
    
/// Test unitaire des champs

    public List<Responsable> getResponsables() {
        return responsables;
    }

    public void setResponsables(List<Responsable> responsables) throws Exception {
        if (responsables == null) {
            throw new Exception("Les responsables ne doivent pas être null");
        }
        this.responsables = responsables;
    }

    public List<Parcelle> getParcelles() {
        return parcelles;
    }

    public void setParcelles(List<Parcelle> parcelles) throws Exception {
        if (parcelles == null) {
            throw new Exception("Les parcelles ne doivent pas être null");
        }
        this.parcelles = parcelles;
    }

    public List<Recolte> getEffectue() {
        return effectue;
    }

    public void setEffectue(List<Recolte> effectue) throws Exception {
        if (effectue == null) {
            throw new Exception("Les recoltes effectue ne doivent pas être null");
        }
        this.effectue = effectue;
    }

    public List<Recolte> getPrevision() {
        return prevision;
    }

    public void setPrevision(List<Recolte> prevision) throws Exception {
        if (prevision == null) {
            throw new Exception("Les prevision de recolte doivent pas être null");
        }
        this.prevision = prevision;
    }
    
/// Constructeur du classe Champ

    public Champ() {
    }

    public Champ(List<Responsable> responsables, List<Parcelle> parcelles, List<Recolte> effectue, List<Recolte> prevision) throws Exception {
        setResponsables(responsables);
        setParcelles(parcelles);
        setEffectue(effectue);
        setPrevision(prevision);
    }
    
    
    
}
