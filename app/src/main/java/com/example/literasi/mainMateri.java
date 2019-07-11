package com.example.literasi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.literasi.Adapter.listAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class mainMateri extends AppCompatActivity {
    ListView listView;
    List<list> materiList;
    private String url = "http://192.168.43.182/literasi/list.php";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_home);
        listView = (ListView) findViewById(R.id.list_view);
        materiList = new ArrayList<>();
        showList();
    }

    private void showList(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
        new Response.Listener<String>(){
            @Override
            public void onResponse(String response){

                try {
                    JSONObject obj = new JSONObject(response);
                    JSONArray array = obj.getJSONArray("result");
                    for (int i = 0; i <array.length(); i++)
                    {
                        JSONObject listMat = array.getJSONObject(i);
                        list p = new list(listMat.getString("id"), listMat.getString("judul"));
                        materiList.add(p);
                    }
                    listAdapter adapter = new listAdapter(materiList, getApplicationContext());
                    listView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){

            }
        }){

        };
        Handler.getInstance(getApplicationContext()).addToRequestQue(stringRequest);
    }
}
