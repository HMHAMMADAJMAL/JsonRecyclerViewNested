package com.example.newp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerViewVer;
    ArrayList<ModelVer> data = new ArrayList<ModelVer>();
    ArrayList<ModelHor> data2 = new ArrayList<ModelHor>();
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewVer = findViewById(R.id.rec2);
        new GetContacts().execute();
    }

    private class GetContacts extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(MainActivity.this, "Loading Data", Toast.LENGTH_LONG).show();

        }
        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            String url = "https://sadaqahnz.pythonanywhere.com/campaigns/";
            String jsonStr = sh.makeServiceCall(url);
            Log.i(TAG, "Response getting from url: " + jsonStr);
            try {
                JSONArray jsonArray = new JSONArray(jsonStr);
                Log.i(TAG, "doInBackground: Json Length " + jsonArray.length());
                for(int i=0;i<jsonArray.length();i++)
                {
                    HashMap<String, String> user = new HashMap<>();
                    JSONObject jsonObject=jsonArray.getJSONObject(i);
                    String id =  jsonObject.getString("id");
                    String title =  jsonObject.getString("title");
                    String target_amount =  jsonObject.getString("target_amount");
                    String raised_amount =  jsonObject.getString("raised_amount");
                    String detail =  jsonObject.getString("detail");
                    String t="Target: $";
                    String target = t + "" + target_amount;
                    String r="Raised: $";
                    String raised = r + "" + raised_amount;
                    Log.i(TAG, "doInBackground: Main Json Array Id:= " + id);
                    JSONObject adresobj = jsonObject.getJSONObject("masjid_data");
                    String adress = adresobj.getString("address");
                    Log.i(TAG, "doInBackground: Masjid Data Address:= " + adress);
                   JSONArray masjid=jsonObject.getJSONArray("campaign_data");
                    for(int j=0;j<masjid.length();j++) {
                        JSONObject object = masjid.getJSONObject(j);
                        String mas = object.getString("image");
                        mas.concat("image"+"ddd");
                       data2.add(new ModelHor(mas));
                        Log.i(TAG, "doInBackground: Compaign Data Image No:= " + mas);

                    }
                        data.add(new ModelVer(
                                 (title),(target),
                                (raised),(detail)
                                ));
               }
            } catch (JSONException e) {
                e.printStackTrace();
            }
               return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            recyclerViewVer.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
            ModalVerAdapter customAdapter = new ModalVerAdapter(MainActivity.this,data,data2);
            recyclerViewVer.setAdapter(customAdapter);
        }
    }
}