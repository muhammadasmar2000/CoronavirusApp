package com.muhammadasmar.coronavirusapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {
    //declare variables
    private EditText userName, userPassword, userEmail;
    private Button registerButton;
    private TextView userLogin;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog; //stops the user from interacting with app when validating credentials
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initializeVariables(); //function sets views as variables
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this); //displays message when creating account
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    //uploading data to firebase
                    progressDialog.setMessage("Creating Account");
                    progressDialog.show();
                    String user_email = userEmail.getText().toString();
                    String user_password = userPassword.getText().toString();
                    firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                progressDialog.dismiss();
                                //success message if account registration to firebase successful
                                Toast.makeText(RegistrationActivity.this, "Account Registered", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                            }
                            else{
                                //error message if account registration fails
                                progressDialog.dismiss();
                                Toast.makeText(RegistrationActivity.this, "Account Registration Failure", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });
        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
            }
        });
    }

    //initializes the views instead of cluttering the onCreate function
    private void initializeVariables(){
        userName = (EditText)findViewById(R.id.userName);
        userPassword = (EditText)findViewById(R.id.passwordEditText);
        userEmail = (EditText)findViewById(R.id.emailEditText);
        registerButton = (Button)findViewById(R.id.registerButton);
        userLogin = (TextView)findViewById(R.id.back_to_login);
    }

    //checks if all form fields have been filled when submit button clicked
    private Boolean validate(){
        Boolean allFieldsCompleted = false;
        String name = userName.getText().toString();
        String password = userPassword.getText().toString();
        String email = userEmail.getText().toString();
        if(name.isEmpty() || password.isEmpty() || email.isEmpty()){
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
        }
        else{
            allFieldsCompleted = true;
        }
        return allFieldsCompleted;
    }
}
