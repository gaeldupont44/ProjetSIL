package com.laposte.detailsenveloppe.beans;

/**
 * Created by Fernando on 25/11/2015.
 */
public class Adresse {
    private Long CP;
    private Long commune;
    private Long typeVoie1;
    private Long libelleVoie1;
    private Long numVoie1;
    private Long typeSeparationCedex;
    private Long numSeparationCedex;

    //Constructors
    public Adresse() {
        super();
    }

    public Adresse(Long CP, Long commune, Long typeVoie1, Long libelleVoie1,
                   Long numVoie1, Long typeSeparationCedex, Long numSeparationCedex) {
        this.CP = CP;
        this.commune = commune;
        this.typeVoie1 = typeVoie1;
        this.libelleVoie1 = libelleVoie1;
        this.numVoie1 = numVoie1;
        this.typeSeparationCedex = typeSeparationCedex;
        this.numSeparationCedex = numSeparationCedex;
    }

    //Getters & Setters
    public Long getCP() { return CP; }
    public void setCP(Long CP) { this.CP = CP; }

    public Long getCommune() { return commune; }
    public void setCommune(Long commune) { this.commune = commune; }

    public Long getTypeVoie1() { return typeVoie1; }
    public void setTypeVoie1(Long typeVoie1) { this.typeVoie1 = typeVoie1; }

    public Long getLibelleVoie1() { return libelleVoie1; }
    public void setLibelleVoie1(Long libelleVoie1) { this.libelleVoie1 = libelleVoie1; }

    public Long getNumVoie1() { return numVoie1; }
    public void setNumVoie1(Long numVoie1) { this.numVoie1 = numVoie1; }

    public Long getTypeSeparationCedex() { return typeSeparationCedex; }
    public void setTypeSeparationCedex(Long typeSeparationCedex) { this.typeSeparationCedex = typeSeparationCedex; }

    public Long getNumSeparationCedex() { return numSeparationCedex; }
    public void setNumSeparationCedex(Long numSeparationCedex) { this.numSeparationCedex = numSeparationCedex; }
}
