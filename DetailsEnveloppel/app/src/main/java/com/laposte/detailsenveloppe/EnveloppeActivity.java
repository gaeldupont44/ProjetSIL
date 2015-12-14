package com.laposte.detailsenveloppe;

import android.annotation.TargetApi;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.app.Activity;
import android.widget.Toast;

import com.laposte.detailsenveloppe.beans.Adresse;
import com.laposte.detailsenveloppe.beans.Client;
import com.laposte.detailsenveloppe.beans.Enveloppe;
import com.laposte.detailsenveloppe.beans.Titre;
import com.laposte.detailsenveloppe.beans.Traitement;
import com.laposte.detailsenveloppe.com.laposte.detailsenveloppe.traitementXML.XMLEnveloppeParser;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class EnveloppeActivity extends Activity {
    private static final String URI =
            "192.168.42.1/req.php?id=clean";  //changer pour le url du serveur rapsberryPi

    TextView txtCodeEnv, txtIsConnected;

    //Table columns
    TextView tableIDTitre, tableFormatTitre, tableEtatTitre, tablePriorTitre,                   //Titre
            tableDateDebTrtm, tableIDMTriTrtm, tableIDPlatfTrtm, tableEtatTrtm,                 //Traitement
            tableDateDebClient, tableConfEntMacClient, tableZ1IntClient, tableZ2IntClient,
            tableDateAutoClient, tableDateTotClient, tableSurMarqClient, tablePMultipleClient,   //Client
            tableCPAdr, tableCommuneAdr, tableTVoie1Adr, tableLibVoie1Adr, tableNumVoie1Adr,    //Adresse
            tableTSepCedexAdr, tableNumSepCedexAdr;

    Enveloppe enveloppe;
    Titre titre;
    Adresse adresse;
    Client client;
    Traitement traitement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enveloppe);

        txtCodeEnv = (TextView) findViewById(R.id.txtCode);
        txtIsConnected = (TextView) findViewById(R.id.txtViewConnection);

        //Titre
        tableIDTitre = (TextView) findViewById(R.id.tableIDTitre);
        tableFormatTitre = (TextView) findViewById(R.id.tableFormatTitre);
        tableEtatTitre = (TextView) findViewById(R.id.tableEtatTitre);
        tablePriorTitre = (TextView) findViewById(R.id.tablePriorTitre);

        //Traitement
        tableDateDebTrtm = (TextView) findViewById(R.id.tableDateDebTrtm);
        tableIDMTriTrtm = (TextView) findViewById(R.id.tableIDMTriTrtm);
        tableIDPlatfTrtm = (TextView) findViewById(R.id.tableIDPlatfTrtm);
        tableEtatTrtm = (TextView) findViewById(R.id.tableEtatTrtm);

        //Client
        tableConfEntMacClient = (TextView) findViewById(R.id.tableConfEntMacClient);
        tableZ1IntClient = (TextView) findViewById(R.id.tableZ1IntClient);
        tableZ2IntClient = (TextView) findViewById(R.id.tableZ2IntClient);
        tableDateAutoClient = (TextView) findViewById(R.id.tableDateAutoClient);
        tableDateTotClient = (TextView) findViewById(R.id.tableDateTotClient);
        tableSurMarqClient = (TextView) findViewById(R.id.tableSurMarqClient);
        tablePMultipleClient = (TextView) findViewById(R.id.tablePMultipleClient);

        //Adresse
        tableCPAdr = (TextView) findViewById(R.id.tableCPAdr);
        tableCommuneAdr = (TextView) findViewById(R.id.tableCommuneAdr);
        tableTVoie1Adr = (TextView) findViewById(R.id.tableTVoie1Adr);
        tableLibVoie1Adr = (TextView) findViewById(R.id.tableLibVoie1Adr);
        tableNumVoie1Adr = (TextView) findViewById(R.id.tableNumVoie1Adr);
        tableTSepCedexAdr = (TextView) findViewById(R.id.tableTSepCedexAdr);
        tableNumSepCedexAdr = (TextView) findViewById(R.id.tableNumSepCedexAdr);

        // Recevoir le code de l'enveloppe
        Intent i = getIntent();
        String code = i.getStringExtra("code");
        txtCodeEnv.setText(code);
        Toast.makeText(getApplicationContext(), code, Toast.LENGTH_SHORT).show();

        enveloppe = null;

        if (isConnected()) {
            txtIsConnected.setText("Vous êtes connecté");

            new DownloadXmlTask().execute(URI);

        } else {
            txtIsConnected.setText("Vous n'êtes pas connecté !");
        }
    }
    private String downloadContent(String myurl) throws IOException {
        InputStream is = null;
        int length = 500;

        try {
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            Log.e("HTTP: ", "message");
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();
            Log.e("HTTP: ", "connect");
            int response = conn.getResponseCode();
            Log.d("TAG", "The response is: " + response);
            is = conn.getInputStream();
            Log.e("HTTP: ", "input");

            // Convert the InputStream into a string
            String contentAsString = convertInputStreamToString(is, length);

            Toast.makeText(getApplicationContext(), contentAsString, Toast.LENGTH_LONG).show();

            Log.e("HTTP: ", "string");
            return contentAsString;
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    public String convertInputStreamToString(InputStream stream, int length) throws IOException, UnsupportedEncodingException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[length];
        reader.read(buffer);
        return new String(buffer);
    }

    public boolean isConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo netI = cm.getActiveNetworkInfo();
        if (netI != null && netI.isConnected()) {
            return true;
        }
        return false;
    }

    // Implementation of AsyncTask used to download XML feed from raspberryPi server
    private class DownloadXmlTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            //do your request in here so that you don't interrupt the UI thread
            try {
                return downloadContent(params[0]);
            } catch (IOException e) {
                return "Unable to retrieve data. URL may be invalid.";
            }
        }

        @TargetApi(Build.VERSION_CODES.KITKAT)
        @Override
        protected void onPostExecute(String result) {
            //Here you are done with the task
            Toast.makeText(EnveloppeActivity.this, result, Toast.LENGTH_LONG).show();
            try {
                XMLEnveloppeParser parser = new XMLEnveloppeParser();
                InputStream is = new ByteArrayInputStream(result.getBytes(StandardCharsets.UTF_8));

                enveloppe = parser.parse(is);

                titre = enveloppe.getTitre();
                adresse = enveloppe.getAdresse();
                client = enveloppe.getClient();
                traitement = enveloppe.getTraitement();

                //Titre
                tableIDTitre.setText(titre.getId());
                tableFormatTitre.setText(titre.getFormat());
                tableEtatTitre.setText(titre.getEtat());
                tablePriorTitre.setText(titre.getPriorite());

                //Traitement
                tableDateDebTrtm.setText(traitement.getDatedebut());
                tableIDMTriTrtm.setText(traitement.getIdMachineTri());
                tableIDPlatfTrtm.setText(traitement.getIdPlateforme());
                tableEtatTrtm.setText(traitement.getEtat());

                //Client
                tableConfEntMacClient.setText(client.getConfigEntreeMachine());
                tableZ1IntClient.setText(client.getZ1Interdit());
                tableZ2IntClient.setText(client.getZ2Interdit());
                tableDateAutoClient.setText(client.getDateAutomatique());
                tableDateTotClient.setText(client.getDateTotal());
                tableSurMarqClient.setText(client.getSureteMarquage());
                tablePMultipleClient.setText(client.getPriseMultiple());

                //Adresse
                tableCPAdr.setText(adresse.getCP());
                tableCommuneAdr.setText(adresse.getCommune());
                tableTVoie1Adr.setText(adresse.getTypeVoie1());
                tableLibVoie1Adr.setText(adresse.getLibelleVoie1());
                tableNumVoie1Adr.setText(adresse.getNumVoie1());
                tableTSepCedexAdr.setText(adresse.getTypeSeparationCedex());
                tableNumSepCedexAdr.setText(adresse.getNumSeparationCedex());
            }catch (Exception e){
                Log.e("AsyncTask: ", e.getMessage());
            }

        }
    }
}