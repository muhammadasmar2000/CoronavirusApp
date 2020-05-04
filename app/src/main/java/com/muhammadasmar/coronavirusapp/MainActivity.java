package com.muhammadasmar.coronavirusapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    //declare variables
    private EditText email;
    private EditText password;
    private Button loginButton;
    private int counter = 5;
    private TextView attempts;
    private TextView registerTextView;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog; //stops the user from interacting with the app when validating login credentials
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initialize variables
        email = (EditText)findViewById(R.id.emailEditText);
        password = (EditText)findViewById(R.id.passwordEditText);
        loginButton = (Button)findViewById(R.id.loginButton);
        attempts = (TextView)findViewById(R.id.attemptsTextView);
        registerTextView = (TextView)findViewById(R.id.registerAccountButton);
        attempts.setText("Login Attempts Remaining: 5");

        //use a firebase auth instance to check if user is already signed in
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        //if user is already signed in, take them to the search activity
        FirebaseUser user = firebaseAuth.getCurrentUser();
        //checks if the current user is not null, otherwise, proceed to normal login
        if(user != null){
            finish();
            startActivity(new Intent(MainActivity.this, CoronavirusSearchActivity.class));
        }

        //login button on click listener
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(email.getText().toString(), password.getText().toString());
            }
        });

        //if user clicks the register here text, them them to registration activity
        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
            }
        });
    }
    //validate login credentials
    private void validate(String email, String password) {
        //display progress dialog while validating credentials
        progressDialog.setMessage("Validating Login Credentials");
        progressDialog.show();
        //this method checks if there is a match of email and password in the database
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //login success and take directly to search activity
                    progressDialog.dismiss();
                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, CoronavirusSearchActivity.class));
                }
                else{
                    //login failure
                    progressDialog.dismiss();
                    //toast message to display failure
                    Toast.makeText(MainActivity.this, "Login Unsuccessful", Toast.LENGTH_SHORT).show();
                    counter--;  //decrement counter
                    attempts.setText("Login Attempts Remaining: " + String.valueOf(counter));
                    if(counter == 0) { //check if login counter is zero
                        //disable login button is counter reaches zero
                        loginButton.setEnabled(false);
                    }
                }
            }
        });
    }
}
