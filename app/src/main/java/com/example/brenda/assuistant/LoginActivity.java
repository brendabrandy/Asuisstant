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
    String user;
    String pass;
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

    public class jsonTaskLogin extends AsyncTask<String, String, List<testObjectLogin> > {

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
        protected List<testObjectLogin> doInBackground(String... params) {
            //return List of TestObjects
            List <testObjectLogin> result = new ArrayList<>();
            String jsonString = CallDB.getTable("employee");
            username = (EditText) findViewById(R.id.nameLogin);
            password = (EditText) findViewById(R.id.passwordLogin);
            Log.d("ASYNCTASK",jsonString);
            try {
                JSONObject reader = new JSONObject(jsonString);
                JSONArray parentArray = reader.getJSONArray("rows");
                for (int i =0; i < parentArray.length(); i++){
                    JSONObject person = parentArray.getJSONObject(i);
                    Log.d("ASYNC TASK",person.getString("name"));
                    testObjectLogin o = new testObjectLogin();
                    o.setPassword(person.getString("password"));
                    o.setUsername(person.getString("name"));
                    result.add(o);
                }
            }catch(JSONException ex){
                ex.printStackTrace();
            }
            return result;
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(List<testObjectLogin> result) {
            nDialog.dismiss();
           for (testObjectLogin people:result){
               user = username.getText().toString();
               pass = password.getText().toString();
               Log.d("ASYNC TASK",user);
               Log.d("ASYNC TASK",pass);
               Log.d("ASYNC TASK",people.getUsername());
               Log.d("ASYNC TASK",people.getPassword());
               if ((user.equals(people.getUsername()))&&(pass.equals(people.getPassword()))){

                   DataBaseHelper db = new DataBaseHelper(getApplicationContext());
                   db.addUser(user,pass);
                   Intent i = new Intent(LoginActivity.this, MainActivity.class);
                   startActivity(i);
                   Log.d("ASYNC TASK","Helloo!");
               }
            }
            // TODO: set result to listView
        }
    }
}
