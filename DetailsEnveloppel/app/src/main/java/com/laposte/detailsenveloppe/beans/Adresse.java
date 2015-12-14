package com.laposte.detailsenveloppe.beans;

/**
 * Created by Fernando on 25/11/2015.
 */
public class Adresse {
    private String CP;
    private String commune;
    private String typeVoie1;
    private String libelleVoie1;
    private String numVoie1;
    private String typeSeparationCedex;
    private String numSeparationCedex;

    //Constructors
    public Adresse() {
        super();
    }

    public Adresse(String CP, String commune, String typeVoie1, String libelleVoie1,
                   String numVoie1, String typeSeparationCedex, String numSeparationCedex) {
        this.CP = CP;
        this.commune = commune;
        this.typeVoie1 = typeVoie1;
        this.libelleVoie1 = libelleVoie1;
        this.numVoie1 = numVoie1;
        this.typeSeparationCedex = typeSeparationCedex;
        this.numSeparationCedex = numSeparationCedex;
    }

    //Getters & Setters
    public String getCP() { return CP; }
    public void setCP(String CP) { this.CP = CP; }

    public String getCommune() { return commune; }
    public void setCommune(String commune) { this.commune = commune; }

    public String getTypeVoie1() { return typeVoie1; }
    public void setTypeVoie1(String typeVoie1) { this.typeVoie1 = typeVoie1; }

    public String getLibelleVoie1() { return libelleVoie1; }
    public void setLibelleVoie1(String libelleVoie1) { this.libelleVoie1 = libelleVoie1; }

    public String getNumVoie1() { return numVoie1; }
    public void setNumVoie1(String numVoie1) { this.numVoie1 = numVoie1; }

    public String getTypeSeparationCedex() { return typeSeparationCedex; }
    public void setTypeSeparationCedex(String typeSeparationCedex) { this.typeSeparationCedex = typeSeparationCedex; }

    public String getNumSeparationCedex() { return numSeparationCedex; }
    public void setNumSeparationCedex(String numSeparationCedex) { this.numSeparationCedex = numSeparationCedex; }

    @Override
    public String toString() {
        return "Adresse{" +
                "CP='" + CP + '\'' +
                ", commune='" + commune + '\'' +
                ", typeVoie1='" + typeVoie1 + '\'' +
                ", libelleVoie1='" + libelleVoie1 + '\'' +
                ", numVoie1='" + numVoie1 + '\'' +
                ", typeSeparationCedex='" + typeSeparationCedex + '\'' +
                ", numSeparationCedex='" + numSeparationCedex + '\'' +
                '}';
    }
}
