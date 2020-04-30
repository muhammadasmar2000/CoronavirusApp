package com.muhammadasmar.coronavirusapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class ChartFavoritesActivity extends AppCompatActivity {
    //declare variables
    String country;
    WebView webView;
    String webHtml1 = "Chartjs-Cases.html"; //html file with chart making html
    ArrayList<String> datadate = new ArrayList<String>(); //x-axis
    ArrayList<String> datanum = new ArrayList<String>(); //y-axis
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_favorites);
        //get intent and initialize variables
        Intent intent = getIntent();
        country = intent.getStringExtra(FavoritesResultsActivity.EXTRA_COUNTRY_CHART);
        webView = (WebView) findViewById(R.id.webview2);
        webView.getSettings().setJavaScriptEnabled(true); //enables javascript
        webView.setWebChromeClient(new WebChromeClient());
        webView.addJavascriptInterface(new JavaScriptInterface(this), "Android");
        webView.loadUrl("file:///android_asset/Chartjs-Cases.html"); //loads the html file
        getCovid19Data(country); //make JSON request with country name
    }

    protected void DisplayCovidResults(JSONObject data) {
        System.out.println(data);
        try {
            //use an iterator to create the graph from response JSON data
            JSONObject CovidCasesTimeline=data.getJSONObject("timeline"); //get timeline object in reponse
            System.out.println(CovidCasesTimeline);
            JSONObject CovidCases = CovidCasesTimeline.getJSONObject("cases"); //get cases object in timeline
            System.out.println(CovidCases);
            Iterator<String> iterator = CovidCases.keys();
            while (iterator.hasNext()) {
                String key = iterator.next();
                String value = CovidCases.getString(key);
                datadate.add("'"+key+"'"); //add date to graph
                datanum.add(value); //add  datanum to height of bar in graph
            }
            System.out.println(datadate);
            System.out.println(datanum);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getCovid19Data(String Country) {
        String urlQuery;
        String CountryQuery;

        if (Country.isEmpty()) {
            CountryQuery = "USA"; //default search if field empty
        }
        else {
            CountryQuery = Country;
        }

        final String url_request = "https://corona.lmao.ninja/v2/historical/"; //url prefix
        urlQuery = url_request + CountryQuery; //full url with country name

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        System.out.println(urlQuery);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, urlQuery, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        //make graph if the response is successful
                        DisplayCovidResults(response);
                    }
                }, new Response.ErrorListener() {
                    //on error, print toast message
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ChartFavoritesActivity.this, "Could not display chart", Toast.LENGTH_SHORT).show();
                    }
                });

        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);
    }
}
