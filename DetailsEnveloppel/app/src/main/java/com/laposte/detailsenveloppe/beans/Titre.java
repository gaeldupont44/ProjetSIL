package com.laposte.detailsenveloppe.beans;

/**
 * Created by Fernando on 25/11/2015.
 */
public class Titre {
    private String id;
    private String format;
    private String etat;
    private String priorite;

    //Constructors
    public Titre() {
        super();
    }

    public Titre(String format, String etat, String priorite, String id) {
        this.format = format;
        this.etat = etat;
        this.priorite = priorite;
        this.id = id;
    }

    //Getters & Setters
    public String getFormat() { return format; }
    public void setFormat(String format) { this.format = format; }

    public String getEtat() { return etat; }
    public void setEtat(String etat) { this.etat = etat; }

    public String getPriorite() { return priorite; }
    public void setPriorite(String priorite) { this.priorite = priorite; }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
}
