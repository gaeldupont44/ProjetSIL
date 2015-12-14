package com.laposte.detailsenveloppe;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.app.Activity;
import android.widget.Toast;

import com.laposte.detailsenveloppe.beans.Enveloppe;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class EnveloppeActivity extends Activity {
    private static final String URI =
            "http://hmkcode.com/examples/index.php";  //changer pour le url du serveur rapsberryPi

    TextView txtCodeEnv, txtIsConnected;

    //Table columns
    TextView tableIDTitre, tableFormatTitre, tableEtatTitre, tablePriorTitre,                   //Titre
            tableDateDebTrtm, tableIDMTriTrtm, tableIDPlatfTrtm, tableEtatTrtm,                 //Traitement
            tableDateDebClient, tableConfEntMacClient, tableZ1IntClient, tableZ2IntClient,
            tableDateAutoClient, tableDateTotClient, tableSurMarqClient, tablePMultipleClient,   //Client
            tableCPAdr, tableCommuneAdr, tableTVoie1Adr, tableLibVoie1Adr, tableNumVoie1Adr,    //Adresse
            tableTSepCedexAdr, tableNumSepCedexAdr;

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
        tableDateDebClient = (TextView) findViewById(R.id.tableDateDebClient);
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

        /*  Recevoir le code de l'enveloppe
        */Intent i = getIntent();
        String code = i.getStringExtra("code");
        txtCodeEnv.setText(code);
        Toast.makeText(getApplicationContext(), code, Toast.LENGTH_SHORT).show();
        /**/

        if (isConnected()) {
            txtIsConnected.setText("Vous êtes connecté");

            new DownloadXmlTask().execute(URI);

        } else {
            txtIsConnected.setText("Vous n'êtes pas connecté !");
        }
    }

    public static String GET(String urlToServ) throws IOException {
        String result = "";
        URL url = new URL(urlToServ);
        URLConnection urlConnection = url.openConnection();
        InputStream in = new BufferedInputStream(urlConnection.getInputStream());

        if (in != null) {
            try {
                result = convertInputStreamToString(in);
            } catch (IOException e) {
                Log.e("GET error: ", e.getMessage());
            } finally {
                in.close();
            }
        } else {
            result = "Aucune réponse...";
        }
        return result;
    }

    private static String convertInputStreamToString(InputStream in) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(in));
        String line = "";
        String result = "";

        while ((line = bf.readLine()) != null) {
            result += line;
        }
        in.close();
        return result;
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
            try {
                return GET(params[0]);
            } catch (IOException e) {
                Log.e("GET error: ", e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            //Toast.makeText(getBaseContext(), "Recu!", Toast.LENGTH_LONG).show();
            //txtCodeEnv.setText(result);

            Enveloppe env = new Enveloppe();

            //Titre
            tableIDTitre.setText(result);                  /*
            tableFormatTitre.setText(result);
            tableEtatTitre.setText(result);
            tablePriorTitre.setText(result);

            //Traitement
            tableDateDebTrtm.setText(result);
            tableIDMTriTrtm.setText(result);
            tableIDPlatfTrtm.setText(result);
            tableEtatTrtm.setText(result);

            //Client
            tableDateDebClient.setText(result);
            tableConfEntMacClient.setText(result);
            tableZ1IntClient.setText(result);
            tableZ2IntClient.setText(result);
            tableDateAutoClient.setText(result);
            tableDateTotClient.setText(result);
            tableSurMarqClient.setText(result);
            tablePMultipleClient.setText(result);

            //Adresse
            tableCPAdr.setText(result);
            tableCommuneAdr.setText(result);
            tableTVoie1Adr.setText(result);
            tableLibVoie1Adr.setText(result);
            tableNumVoie1Adr.setText(result);
            tableTSepCedexAdr.setText(result);
            tableNumSepCedexAdr.setText(result);
*/
        }
    }
}