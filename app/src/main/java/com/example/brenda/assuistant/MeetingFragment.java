package com.example.brenda.assuistant;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.text.DateFormatSymbols;


public class MeetingFragment extends Fragment {

    TextView showCaseMonth;
    TextView showCaseDay;
    TextView showCaseClient;
    TextView showCasePerson;
    TextView showCaseTime;
    ListView listView;
    List <TestObject> result;
    ProgressDialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        //TODO Auto-generated method stub
        View rootView=inflater.inflate(R.layout.fragment_meeting,container,false);
        listView = (ListView) rootView.findViewById(R.id.jsonList);

        //Set up progress dialog as json is being retrieved
        dialog = new ProgressDialog(this.getActivity());
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.setMessage("Loading. Please wait...");

        //JsonAdapter myAdapter = new JsonAdapter(getContext(), R.layout.listviewrow, result);

        new jsonTask().execute();
        return rootView;
    }


    public class jsonTask extends AsyncTask<String, Void, List<TestObject> > {

        BufferedReader reader;
        HttpURLConnection conn;

        @Override
        protected void onPreExecute() {
            //Shows dialog box in async task
            super.onPreExecute();
            dialog.show();
        }

        @Override
        protected List<TestObject> doInBackground(String... params) {
            //return List of TestObjects
            List<TestObject> testObjectList = new ArrayList<>();

            String jsonString = null;
            try {
                InputStream is = getActivity().getAssets().open("socialSample.json");
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                jsonString = new String(buffer, "UTF-8");
                JSONObject reader = new JSONObject(jsonString);
                JSONArray parentArray = reader.getJSONArray("meetings");
                //String finalString = meetings.toString();
                //Log.d("MEETING FRAGMENT", finalString);
                //JSONArray parentArray = new JSONArray(finalString);//the json returned is an array
                for(int i = 0; i<parentArray.length();i++) {
                    //get ith object from json and set properties of object according
                    //to the jsonobject
                    JSONObject childObject = parentArray.getJSONObject(i);
                    TestObject testObject = new TestObject();
                    testObject.setShowCaseMonth(childObject.getInt("Month"));
                    testObject.setShowCaseDay(childObject.getString("Date"));
                    testObject.setShowCaseClient(childObject.getString("Client"));
                    testObject.setShowCasePerson(childObject.getString("Person"));
                    testObject.setShowCaseTime(childObject.getString("Hour"));
                    testObjectList.add(testObject);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (JSONException ex){
                ex.printStackTrace();
            }
            return testObjectList;
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(List<TestObject> result) {
            //after execution, set adapter and set up listview
            //dismiss dialog
            super.onPostExecute(result);
            dialog.dismiss();
            JsonAdapter myAdapter = new JsonAdapter(getContext(), R.layout.listviewrow, result);
            listView.setAdapter(myAdapter);
            // TODO: set result to listView
        }
    }

    public class JsonAdapter extends ArrayAdapter {

        public List<TestObject> ListObject;
        private int resource;
        private LayoutInflater inflater;


        //Constructor
        public JsonAdapter(Context context, int resource, List<TestObject> objects) {
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

            TextView jsonMonth;
            TextView jsonDay;
            TextView jsonClient;
            TextView jsonPerson;
            TextView jsonTime;

            jsonMonth = (TextView) convertView.findViewById(R.id.showCaseMonth);
            jsonDay = (TextView) convertView.findViewById(R.id.showCaseDay);
            jsonClient = (TextView) convertView.findViewById(R.id.showCaseClient);
            jsonPerson = (TextView) convertView.findViewById(R.id.showCasePerson);
            jsonTime = (TextView) convertView.findViewById(R.id.showCaseTime);



            jsonMonth.setText(new DateFormatSymbols().getMonths()[ListObject.get(position).getShowCaseMonth()-1]);
            jsonDay.setText(ListObject.get(position).getShowCaseDay());
            jsonClient.setText(ListObject.get(position).getShowCaseClient());
            jsonPerson.setText(ListObject.get(position).getShowCasePerson());
            jsonTime.setText(ListObject.get(position).getShowCaseTime());


            return convertView;
        }

    }
}
