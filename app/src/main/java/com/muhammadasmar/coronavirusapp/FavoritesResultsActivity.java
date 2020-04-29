package com.muhammadasmar.coronavirusapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class FavoritesResultsActivity extends AppCompatActivity {
    public static final String EXTRA_COUNTRY_CHART = "com.muhammadasmar.coronavirusapp.COUNTRY";
    private String country;
    private TextView results;
    private RequestQueue queue;
    private Button chartBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites_results);
        queue = Volley.newRequestQueue(this);
        Intent intent = getIntent();
        country = intent.getStringExtra(FavoritesActivity.EXTRA_FAVORITES_COUNTRY);
        results = (TextView)findViewById(R.id.coronavirusFavoritesResult);
        chartBtn = (Button)findViewById(R.id.favoritesChartButton);
        displayData();

        chartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FavoritesResultsActivity.this, ChartFavoritesActivity.class);
                intent.putExtra(EXTRA_COUNTRY_CHART, country);
                startActivity(intent);
            }
        });
    }

    private void displayData() {
        String url = "https://corona.lmao.ninja/v2/countries/" + country;
        JsonObjectRequest jsonObjectRequest;
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String countryName;
                int cases, todayCases, deaths, todayDeaths, recovered, critical, tests;
                try {
                    //parse object and print to screen
                    countryName = response.getString("country");
                    cases = response.getInt("cases");
                    todayCases = response.getInt("todayCases");
                    deaths = response.getInt("deaths");
                    todayDeaths = response.getInt("todayDeaths");
                    recovered = response.getInt("recovered");
                    critical = response.getInt("critical");
                    tests = response.getInt("tests");

                    //display results
                    results.setText("Country: " + countryName + "\n" +
                            "Total Cases: " + String.valueOf(cases) + "\n" +
                            "Cases Today: " + String.valueOf(todayCases) + "\n" +
                            "Total Deaths: " + String.valueOf(deaths) + "\n" +
                            "Deaths Today: " + String.valueOf(todayDeaths) + "\n" +
                            "Recovered: " + String.valueOf(recovered) + "\n" +
                            "Critical Cases: " + String.valueOf(critical) + "\n" +
                            "People Tested: " + String.valueOf(tests)
                    );
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                results.setText("Could not find country for coronavirus data");
            }
        });
        queue.add(jsonObjectRequest);
    }
}
