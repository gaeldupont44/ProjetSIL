package com.laposte.detailsenveloppe.com.laposte.detailsenveloppe.traitementXML;

import android.util.Xml;

import com.laposte.detailsenveloppe.beans.Adresse;
import com.laposte.detailsenveloppe.beans.Client;
import com.laposte.detailsenveloppe.beans.Enveloppe;
import com.laposte.detailsenveloppe.beans.Titre;
import com.laposte.detailsenveloppe.beans.Traitement;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fernando on 29/11/2015.
 */
public class XMLEnveloppeParser {
    private Enveloppe enveloppe;
    private Adresse adresse;
    private Titre titre;
    private Traitement traitement;
    private Client client;

    private String text;

    public XMLEnveloppeParser() {
        enveloppe = new Enveloppe();
        adresse = new Adresse();
        client = new Client();
        titre = new Titre();
        traitement = new Traitement();
    }

    //Récupérer les données d'une enveloppe
    public Enveloppe getEnveloppe() { return enveloppe; }
    public Adresse getAddrese() { return adresse; }
    public Titre getTitre() { return titre; }
    public Traitement getTraitement() { return traitement; }
    public Client getClient() { return client; }

    /**
     * Cette méthode retourne un objet de type Enveloppe
     * avec tous ces objets attributs.
     * </p>
     *
     * @param is c'est la source du XML
     * @return
     */
    public Enveloppe parse(InputStream is) {
        XmlPullParserFactory factory = null;
        XmlPullParser parser = null;
        try {
            factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            parser = factory.newPullParser();

            parser.setInput(is, null);

            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagname = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagname.equalsIgnoreCase("enveloppe")) {
                            // create a new instance of enveloppe
                            enveloppe = new Enveloppe();
                        }
                        break;

                    case XmlPullParser.TEXT:
                        text = parser.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if (tagname.equalsIgnoreCase("titre")) {
                            // ajout object titre à l'enveloppe


                            enveloppe.setTitre(titre);
                        } else if (tagname.equalsIgnoreCase("traitement")) {
                            // ajout object traitement à l'enveloppe


                            enveloppe.setTraitement(traitement);
                        } else if (tagname.equalsIgnoreCase("client")) {
                            // ajout object client à l'enveloppe


                            enveloppe.setClient(client);
                        } else if (tagname.equalsIgnoreCase("adresse")) {
                            // ajout object adresse à l'enveloppe


                            enveloppe.setAdresse(adresse);
                        }
                        break;

                    default:
                        break;
                }
                eventType = parser.next();
            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return enveloppe;
    }
}