package com.muhammadasmar.coronavirusapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONException;
import org.json.JSONObject;

public class CoronavirusSearchActivity extends AppCompatActivity {
    //declare variables
    public static final String EXTRA_COUNTRY = "com.muhammadasmar.coronavirusapp.COUNTRY";
    private FirebaseAuth firebaseAuth;
    private EditText country;
    private Button search, chart;
    private TextView results;
    private RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coronavirus_search);
        //initialize variables
        firebaseAuth = FirebaseAuth.getInstance();
        country = (EditText)findViewById(R.id.countryInput);
        search = (Button)findViewById(R.id.searchButton);
        results = (TextView)findViewById(R.id.resultsTextView);
        chart = (Button)findViewById(R.id.chartButton);
        queue = Volley.newRequestQueue(this);

        //get coronavirus data if search button clicked
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCoronavirusData();
            }
        });

        //view chart if chart button is clicked
        chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!country.getText().toString().equals("")){
                    Intent intent = new Intent(CoronavirusSearchActivity.this, ChartActivity.class);
                    intent.putExtra(EXTRA_COUNTRY, country.getText().toString());
                    startActivity(intent);
                }
                else {
                    results.setText("Please enter a country to view chart");
                }
            }
        });
    }

    //make JSON object request with country name
    private void getCoronavirusData(){
        final String countryName = country.getText().toString();
        //make error if country edit text field is blank
        if (countryName.equals("")){
            //user has not entered a country name
            results.setText("Please enter a country to view data");
            return;
        }
        else {
            String url = "https://corona.lmao.ninja/v2/countries/" + String.valueOf(countryName);
            JsonObjectRequest jsonObjectRequest;
            jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    String jsonCountry;
                    int cases, todayCases, deaths, todayDeaths, recovered, critical, tests;
                    try {
                        //parse object and print to screen
                        jsonCountry = response.getString("country");
                        cases = response.getInt("cases");
                        todayCases = response.getInt("todayCases");
                        deaths = response.getInt("deaths");
                        todayDeaths = response.getInt("todayDeaths");
                        recovered = response.getInt("recovered");
                        critical = response.getInt("critical");
                        tests = response.getInt("tests");

                        //display results by converting integers to strings
                        results.setText("Country: " + jsonCountry + "\n" +
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
                //print error message if country not found
                @Override
                public void onErrorResponse(VolleyError error) {
                    results.setText("Could not find country for coronavirus data");
                }
            });
            //Add to the request queue
            queue.add(jsonObjectRequest);
        }
    }

    //if logout option selected, signout of account, finish activity, and go back to login screen
    private void logoutAccount(){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(CoronavirusSearchActivity.this, MainActivity.class));
        Toast.makeText(CoronavirusSearchActivity.this, "Logout Successful", Toast.LENGTH_SHORT).show();
    }

    //if favorites option selected, go to FavoritesActivity
    private void goToFavorites() {
        startActivity(new Intent(CoronavirusSearchActivity.this, FavoritesActivity.class));
    }

    //if recent news option selected, go to RecentNewsActivity
    private void goToRecentNews(){
        startActivity(new Intent(CoronavirusSearchActivity.this, RecentNewsActivity.class));
    }

    //create options menu with the menu.xml file
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    //get the id of the menu option selected and start the respective activity
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.viewFavoritesMenu:{
                goToFavorites();
                break;
            }
            case R.id.viewRecentNewsMenu:{
                goToRecentNews();
                break;
            }
            case R.id.logoutMenu:{
                logoutAccount();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
