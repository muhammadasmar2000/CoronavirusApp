package com.muhammadasmar.coronavirusapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FavoritesActivity extends AppCompatActivity {
    //declare variables
    private TextView united_states, china, italy, canada, iran, spain;
    private String country;
    public static final String EXTRA_FAVORITES_COUNTRY = "com.muhammadasmar.coronavirusapp.FAVORITES_COUNTRY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        initializeTextViews(); //initializes variables

        //if user clicks United States, go to coronavirus search activity and auto search results
        united_states.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                country = "USA";
                Intent intent = new Intent(FavoritesActivity.this, FavoritesResultsActivity.class);
                intent.putExtra(EXTRA_FAVORITES_COUNTRY, country);
                startActivity(intent);
            }
        });

        //if user clicks China, go to coronavirus search activity and auto search results
        china.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                country = "China";
                Intent intent = new Intent(FavoritesActivity.this, FavoritesResultsActivity.class);
                intent.putExtra(EXTRA_FAVORITES_COUNTRY, country);
                startActivity(intent);
            }
        });

        //if user clicks Italy, go to coronavirus search activity and auto search results
        italy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                country = "Italy";
                Intent intent = new Intent(FavoritesActivity.this, FavoritesResultsActivity.class);
                intent.putExtra(EXTRA_FAVORITES_COUNTRY, country);
                startActivity(intent);
            }
        });

        //if user clicks Canada, go to coronavirus search activity and auto search results
        canada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                country = "Canada";
                Intent intent = new Intent(FavoritesActivity.this, FavoritesResultsActivity.class);
                intent.putExtra(EXTRA_FAVORITES_COUNTRY, country);
                startActivity(intent);
            }
        });

        //if user clicks Iran, go to coronavirus search activity and auto search results
        iran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                country = "Iran";
                Intent intent = new Intent(FavoritesActivity.this, FavoritesResultsActivity.class);
                intent.putExtra(EXTRA_FAVORITES_COUNTRY, country);
                startActivity(intent);
            }
        });

        //if user clicks Spain, go to coronavirus search activity and auto search results
        spain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                country = "Spain";
                Intent intent = new Intent(FavoritesActivity.this, FavoritesResultsActivity.class);
                intent.putExtra(EXTRA_FAVORITES_COUNTRY, country);
                startActivity(intent);
            }
        });
    }

    //initializes textview variables
    private void initializeTextViews() {
        united_states = (TextView)findViewById(R.id.tvUnitedStates);
        china = (TextView)findViewById(R.id.tvChina);
        italy = (TextView)findViewById(R.id.tvItaly);
        canada = (TextView)findViewById(R.id.tvCanada);
        iran = (TextView)findViewById(R.id.tvIran);
        spain = (TextView)findViewById(R.id.tvSpain);
    }
}
