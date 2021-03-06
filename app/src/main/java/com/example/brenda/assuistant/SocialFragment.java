package com.example.brenda.assuistant;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.List;


/*
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SocialFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SocialFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class SocialFragment extends Fragment {

    ListView listView;
    List <TestObject2> result;
    ProgressDialog dialog;
    Button btnRefresh;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //TODO Auto-generated method stub

        View rootView = inflater.inflate(R.layout.fragment_social, container, false);
        listView = (ListView) rootView.findViewById(R.id.jsonList);

        //Set up progress dialog as json is being retrieved
        btnRefresh = (Button)rootView.findViewById(R.id.refresh);
        btnRefresh.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                new jsonTask2().execute();
            }
        });

        new jsonTask2().execute();

        return rootView;
    }


    public class jsonTask2 extends AsyncTask<String, Void, List<TestObject2> > {

        BufferedReader reader;
        HttpURLConnection conn;

        @Override
        protected void onPreExecute() {
            //Shows dialog box in async task
            super.onPreExecute();
            dialog = new ProgressDialog(getActivity());
            dialog.setIndeterminate(true);
            dialog.setCancelable(false);
            dialog.setMessage("Loading. Please wait...");
            dialog.show();
        }

        @Override
        protected List<TestObject2> doInBackground(String... params) {
            //return List of TestObjects
            List<TestObject2> testObjectList = new ArrayList<>();

            String jsonString = null;
            try {
                jsonString = CallDB.getTable("activity");
                JSONObject reader = new JSONObject(jsonString);
                JSONArray parentArray = reader.getJSONArray("rows");
                //String finalString = meetings.toString();
                //Log.d("MEETING FRAGMENT", finalString);
                //JSONArray parentArray = new JSONArray(finalString);//the json returned is an array
                for(int i = 0; i<parentArray.length();i++) {
                    //get ith object from json and set properties of object according
                    //to the jsonobject
                    JSONObject childObject = parentArray.getJSONObject(i);
                    TestObject2 testObject = new TestObject2();
                    testObject.setUser(childObject.getString("employee"));
                    testObject.setContent(childObject.getString("content"));
                    testObjectList.add(testObject);
                }
            }catch (JSONException ex){
                ex.printStackTrace();
            }
            return testObjectList;
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(List<TestObject2> result) {
            //after execution, set adapter and set up listview
            //dismiss dialog
            super.onPostExecute(result);
            dialog.dismiss();
            JsonAdapter myAdapter = new JsonAdapter(getContext(), R.layout.listviewrow2, result);
            listView.setAdapter(myAdapter);
            // TODO: set result to listView
        }
    }

    public class JsonAdapter extends ArrayAdapter {

        public List<TestObject2> ListObject;
        private int resource;
        private LayoutInflater inflater;


        //Constructor
        public JsonAdapter(Context context, int resource, List<TestObject2> objects) {
            super(context, resource, objects);
            ListObject = objects;
            this.resource = resource;
            inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        //populates listviewrow with image and title
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = inflater.inflate(resource, null);
            }

            TextView jsonUser;
            TextView jsonContent;


            jsonUser = (TextView) convertView.findViewById(R.id.showCaseUser);
            jsonContent = (TextView) convertView.findViewById(R.id.showCaseContent);


            jsonUser.setText(ListObject.get(position).getUser());
            jsonContent.setText(ListObject.get(position).getContent());


            return convertView;
        }

    }
}

