package com.laposte.detailsenveloppe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txtCodeEnv;
    Button btnEnvCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCodeEnv = (EditText)findViewById(R.id.editCode);
        btnEnvCode = (Button)findViewById(R.id.btnEnvoyer);

        btnEnvCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!txtCodeEnv.getText().toString().matches("")){
                    Intent i = new Intent(MainActivity.this, EnveloppeActivity.class);
                    i.putExtra("code", txtCodeEnv.getText().toString());
                    startActivity(i);
                } else {
                    Toast.makeText(getApplicationContext(), "Veuillez insérer un code", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
