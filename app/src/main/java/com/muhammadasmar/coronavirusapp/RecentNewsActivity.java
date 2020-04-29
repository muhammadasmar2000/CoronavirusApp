package com.muhammadasmar.coronavirusapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class RecentNewsActivity extends AppCompatActivity {
    private String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
    private String news_api_key = "93478700a1f841e89f67f723afcdf9c1";
    private String url = "https://newsapi.org/v2/everything?q=COVID&from=" + date + "&sortBy=publishedAt&apiKey=" + news_api_key + "&pageSize=3&page=2";
    private TextView news1, news2, news3;
    private RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_news);
        news1 = (TextView)findViewById(R.id.recentNews1);
        news2 = (TextView)findViewById(R.id.recentNews2);
        news3 = (TextView)findViewById(R.id.recentNews3);
        queue = Volley.newRequestQueue(this);
        makeNewsRequest();
    }

    private void makeNewsRequest() {
        JsonObjectRequest jsonObjectRequest;
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //json variables
                try {
                    JSONArray articles = response.getJSONArray("articles");
                    JSONObject news1object = articles.getJSONObject(0);
                    String author1 = news1object.getString("author");
                    String title1 = news1object.getString("title");
                    String description1 = news1object.getString("description");

                    JSONObject news2object = articles.getJSONObject(1);
                    String author2 = news2object.getString("author");
                    String title2 = news2object.getString("title");
                    String description2 = news2object.getString("description");

                    JSONObject news3object = articles.getJSONObject(2);
                    String author3 = news3object.getString("author");
                    String title3 = news3object.getString("title");
                    String description3 = news3object.getString("description");


                    //display results
                    news1.setText("Title: " + title1 + "\n" +
                                  "Author: " + author1 + "\n" +
                                  "Description: " + description1 + "\n");
                    news2.setText("Title: " + title2 + "\n" +
                                  "Author: " + author2 + "\n" +
                                  "Description: " + description2 + "\n");
                    news3.setText("Title: " + title3 + "\n" +
                                  "Author: " + author3 + "\n" +
                                  "Description: " + description3 + "\n");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RecentNewsActivity.this, "Could not find recent headlines for coronavirus news", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(jsonObjectRequest);
    }
}
