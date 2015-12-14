package com.laposte.detailsenveloppe.beans;

/**
 * Created by Fernando on 25/11/2015.
 */
public class Enveloppe {
    private Titre titre;
    private Traitement traitement;
    private Client client;
    private Adresse adresse;

    //Constructors
    public Enveloppe() {
        super();
    }

    public Enveloppe(Titre titre, Traitement traitement, Client client, Adresse adresse) {
        this.titre = titre;
        this.traitement = traitement;
        this.client = client;
        this.adresse = adresse;
    }

    public Titre getTitre() { return titre; }
    public void setTitre(Titre titre) { this.titre = titre; }

    public Traitement getTraitement() { return traitement; }
    public void setTraitement(Traitement traitement) { this.traitement = traitement; }

    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }

    public Adresse getAdresse() { return adresse; }
    public void setAdresse(Adresse adresse) { this.adresse = adresse; }

    @Override
    public String toString() {
        return "Enveloppe{" +
                "titre=" + titre +
                ", traitement=" + traitement +
                ", client=" + client +
                ", adresse=" + adresse +
                '}';
    }
}
