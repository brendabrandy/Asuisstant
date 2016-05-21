package com.example.brenda.assuistant;

import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

/**
 * Created by ysharma1126 on 5/19/16.
 */
public class Broadcast extends AppCompatActivity {

    private ProgressDialog nDialog;
    String user;
    EditText comments;
    String comments_string;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.broadcast);
        DataBaseHelper db = new DataBaseHelper(getApplicationContext());
        user = (String) db.getUserDetails().get("name");
        comments = (EditText) findViewById(R.id.log_write);
    }

    public void activitysubmit(View view){
        nDialog = new ProgressDialog(Broadcast.this);
        nDialog.setTitle("Uploading information");
        nDialog.setMessage("Loading..");
        nDialog.setIndeterminate(false);
        nDialog.setCancelable(true);
        new broadcast_asyncTask().execute();
    }

    public class broadcast_asyncTask extends AsyncTask<String, String, String > {

        @Override
        protected void onPreExecute() {
            //Shows dialog box in async task
            super.onPreExecute();
            comments_string = comments.getText().toString();
            nDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {

            CallDB.insertTable_activity(user,comments_string);
            return comments_string;
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            nDialog.dismiss();
            Intent intent = new Intent(Broadcast.this, MainActivity.class);
            startActivity(intent);
            // TODO: set result to listView
        }
    }

}

