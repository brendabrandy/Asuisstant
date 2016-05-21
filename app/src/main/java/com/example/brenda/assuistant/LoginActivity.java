package com.example.brenda.assuistant;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity{
    Button btnLogin;
    EditText username;
    EditText password;
    ProgressDialog nDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.nameLogin);
        password = (EditText) findViewById(R.id.passwordLogin);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                new jsonTaskLogin().execute();
            }
        });
    }

    public class jsonTaskLogin extends AsyncTask<String, String, Boolean > {

        @Override
        protected void onPreExecute() {
            //Shows dialog box in async task
            super.onPreExecute();
            nDialog = new ProgressDialog(LoginActivity.this);
            nDialog.setTitle("Authenticating");
            nDialog.setMessage("Loading..");
            nDialog.setIndeterminate(false);
            nDialog.setCancelable(true);
            nDialog.show();
        }

        @Override
        protected Boolean doInBackground(String... params) {
            //return List of TestObjects
            String jsonString = CallDB.getTable("employee");
            Log.d("ASYNCTASK",jsonString);
            return false;
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(Boolean result) {
            nDialog.dismiss();
            if (result == true){
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
            }
            // TODO: set result to listView
        }
    }
}
