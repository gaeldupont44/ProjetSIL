package com.laposte.detailsenveloppe.beans;

/**
 * Created by Fernando on 25/11/2015.
 */
public class Traitement {
    private String datedebut;
    private String idMachineTri;
    private String idPlateforme;
    private String etat;

    //Constructors
    public Traitement() {
        super();
    }

    public Traitement(String datedebut, String idMachineTri, String idPlateforme, String etat) {
        this.datedebut = datedebut;
        this.idMachineTri = idMachineTri;
        this.idPlateforme = idPlateforme;
        this.etat = etat;
    }

    //Getters & Setters
    public String getDatedebut() { return datedebut; }
    public void setDatedebut(String datedebut) { this.datedebut = datedebut; }

    public String getIdMachineTri() { return idMachineTri; }
    public void setIdMachineTri(String idMachineTri) { this.idMachineTri = idMachineTri; }

    public String getIdPlateforme() { return idPlateforme; }
    public void setIdPlateforme(String idPlateforme) { this.idPlateforme = idPlateforme; }

    public String getEtat() { return etat; }
    public void setEtat(String etat) { this.etat = etat; }
}
