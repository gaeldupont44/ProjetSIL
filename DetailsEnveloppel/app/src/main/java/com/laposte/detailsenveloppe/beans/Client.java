package com.laposte.detailsenveloppe.beans;

/**
 * Created by Fernando on 25/11/2015.
 */
public class Client {

    private String configEntreeMachine;
    private String z1Interdit;
    private String z2Interdit;
    private String dateAutomatique;
    private String dateTotal;
    private String sureteMarquage;
    private String priseMultiple;

    //Constructors
    public Client() {
        super();
    }

    public Client(String configEntreeMachine, String z1Interdit, String z2Interdit,
                  String dateAutomatique, String dateTotal, String sureteMarquage, String priseMultiple) {
        this.configEntreeMachine = configEntreeMachine;
        this.z1Interdit = z1Interdit;
        this.z2Interdit = z2Interdit;
        this.dateAutomatique = dateAutomatique;
        this.dateTotal = dateTotal;
        this.sureteMarquage = sureteMarquage;
        this.priseMultiple = priseMultiple;
    }

    //Getters & Setters
    public String getConfigEntreeMachine() { return configEntreeMachine; }
    public void setConfigEntreeMachine(String configEntreeMachine) { this.configEntreeMachine = configEntreeMachine; }

    public String getZ1Interdit() { return z1Interdit; }
    public void setZ1Interdit(String z1Interdit) { this.z1Interdit = z1Interdit; }

    public String getZ2Interdit() { return z2Interdit; }
    public void setZ2Interdit(String z2Interdit) { this.z2Interdit = z2Interdit; }

    public String getDateAutomatique() { return dateAutomatique; }
    public void setDateAutomatique(String dateAutomatique) { this.dateAutomatique = dateAutomatique; }

    public String getDateTotal() { return dateTotal; }
    public void setDateTotal(String dateTotal) { this.dateTotal = dateTotal; }

    public String getSureteMarquage() { return sureteMarquage; }
    public void setSureteMarquage(String sureteMarquage) { this.sureteMarquage = sureteMarquage; }

    public String getPriseMultiple() { return priseMultiple; }
    public void setPriseMultiple(String priseMultiple) { this.priseMultiple = priseMultiple; }

    @Override
    public String toString() {
        return "Client{" +
                "configEntreeMachine='" + configEntreeMachine + '\'' +
                ", z1Interdit='" + z1Interdit + '\'' +
                ", z2Interdit='" + z2Interdit + '\'' +
                ", dateAutomatique='" + dateAutomatique + '\'' +
                ", dateTotal='" + dateTotal + '\'' +
                ", sureteMarquage='" + sureteMarquage + '\'' +
                ", priseMultiple='" + priseMultiple + '\'' +
                '}';
    }
}
