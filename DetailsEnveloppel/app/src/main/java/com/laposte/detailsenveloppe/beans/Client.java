package com.laposte.detailsenveloppe.beans;

/**
 * Created by Fernando on 25/11/2015.
 */
public class Client {

    private Long configEntreeMachine;
    private Long z1Interdit;
    private Long z2Interdit;
    private String dateAutomatique;
    private String dateTotal;
    private Long sureteMarquage;
    private Long priseMultiple;

    //Constructors
    public Client() {
        super();
    }

    public Client(Long configEntreeMachine, Long z1Interdit, Long z2Interdit,
                  String dateAutomatique, String dateTotal, Long sureteMarquage, Long priseMultiple) {
        this.configEntreeMachine = configEntreeMachine;
        this.z1Interdit = z1Interdit;
        this.z2Interdit = z2Interdit;
        this.dateAutomatique = dateAutomatique;
        this.dateTotal = dateTotal;
        this.sureteMarquage = sureteMarquage;
        this.priseMultiple = priseMultiple;
    }

    //Getters & Setters
    public Long getConfigEntreeMachine() { return configEntreeMachine; }
    public void setConfigEntreeMachine(Long configEntreeMachine) { this.configEntreeMachine = configEntreeMachine; }

    public Long getZ1Interdit() { return z1Interdit; }
    public void setZ1Interdit(Long z1Interdit) { this.z1Interdit = z1Interdit; }

    public Long getZ2Interdit() { return z2Interdit; }
    public void setZ2Interdit(Long z2Interdit) { this.z2Interdit = z2Interdit; }

    public String getDateAutomatique() { return dateAutomatique; }
    public void setDateAutomatique(String dateAutomatique) { this.dateAutomatique = dateAutomatique; }

    public String getDateTotal() { return dateTotal; }
    public void setDateTotal(String dateTotal) { this.dateTotal = dateTotal; }

    public Long getSureteMarquage() { return sureteMarquage; }
    public void setSureteMarquage(Long sureteMarquage) { this.sureteMarquage = sureteMarquage; }

    public Long getPriseMultiple() { return priseMultiple; }
    public void setPriseMultiple(Long priseMultiple) { this.priseMultiple = priseMultiple; }
}
