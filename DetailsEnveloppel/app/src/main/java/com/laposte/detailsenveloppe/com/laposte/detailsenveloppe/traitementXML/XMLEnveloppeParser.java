package com.laposte.detailsenveloppe.com.laposte.detailsenveloppe.traitementXML;

import android.util.Log;
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
                        } else if (tagname.equalsIgnoreCase("titre")) {
                            titre = new Titre();
                        } else if (tagname.equalsIgnoreCase("traitement")) {
                            traitement = new Traitement();
                        } else if (tagname.equalsIgnoreCase("client")) {
                            client = new Client();
                        } else if (tagname.equalsIgnoreCase("adresse")) {
                            adresse = new Adresse();
                        }

                        break;

                    case XmlPullParser.TEXT:
                            text = parser.getText();
                        break;

                    case XmlPullParser.END_TAG:
//                        if (tagname.equalsIgnoreCase("titre")) {
                            if (tagname.equalsIgnoreCase("format")) {
                                titre.setFormat(text);
                            } else if (tagname.equalsIgnoreCase("titreetat")){
                                titre.setEtat(text);
                            } else if (tagname.equalsIgnoreCase("priorite")){
                                titre.setPriorite(text);
                            } else if (tagname.equalsIgnoreCase("titreid")){
                                titre.setId(text);
                            }

//                        } else if (tagname.equalsIgnoreCase("traitement")) {
                            // ajout object traitement à l'enveloppe
                            if (tagname.equalsIgnoreCase("datedebut")){
                                traitement.setDatedebut(text);
                            } else if (tagname.equalsIgnoreCase("idmachinetri")){
                                traitement.setIdMachineTri(text);
                            } else if (tagname.equalsIgnoreCase("idplateforme")){
                                traitement.setIdPlateforme(text);
                            } else if (tagname.equalsIgnoreCase("traitementetat")){
                                traitement.setEtat(text);
                            }

//                        } else if (tagname.equalsIgnoreCase("client")) {
                            if (tagname.equalsIgnoreCase("configentreemachine")){
                                client.setConfigEntreeMachine(text);
                            } else if (tagname.equalsIgnoreCase("z1interdit")) {
                                client.setZ1Interdit(text);
                            } else if (tagname.equalsIgnoreCase("z2interdit")) {
                                client.setZ2Interdit(text);
                            } else if (tagname.equalsIgnoreCase("dateautomatique")) {
                                client.setDateAutomatique(text);
                            } else if (tagname.equalsIgnoreCase("datetotal")) {
                                client.setDateTotal(text);
                            } else if (tagname.equalsIgnoreCase("suretemarquage")) {
                                client.setSureteMarquage(text);
                            } else if (tagname.equalsIgnoreCase("prisemultiple")) {
                                client.setPriseMultiple(text);
                            }

//                        } else if (tagname.equalsIgnoreCase("adresse")) {
                            if (tagname.equalsIgnoreCase("cp")){
                                adresse.setCP(text);
                            } else if (tagname.equalsIgnoreCase("commune")) {
                                adresse.setCommune(text);
                            } else if (tagname.equalsIgnoreCase("typevoie1")) {
                                adresse.setTypeVoie1(text);
                            } else if (tagname.equalsIgnoreCase("libellevoie1")) {
                                adresse.setLibelleVoie1(text);
                            } else if (tagname.equalsIgnoreCase("numvoie1")) {
                                adresse.setNumVoie1(text);
                            } else if (tagname.equalsIgnoreCase("typeseparationcedex")) {
                                adresse.setTypeSeparationCedex(text);
                            } else if (tagname.equalsIgnoreCase("numseparationcedex")) {
                                adresse.setNumSeparationCedex(text);
                            }

//                        }
                        break;

                    default:
                        break;
                }
                eventType = parser.next();
            }

            //Construire l'objet enveloppe
            enveloppe.setTitre(titre);
            enveloppe.setTraitement(traitement);
            enveloppe.setClient(client);
            enveloppe.setAdresse(adresse);

        } catch (XmlPullParserException e) {
            Log.e("Error: ", e.getMessage());
        } catch (IOException e) {
            Log.e("Error: ", e.getMessage());
        }

        Log.e("Enveloppe: ", enveloppe.toString());
        return enveloppe;
    }
}