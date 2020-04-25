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
    private EditText userName, userPassword, userEmail;
    private Button registerButton;
    private TextView userLogin;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initializeVariables();
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
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
                                Toast.makeText(RegistrationActivity.this, "Account Registered", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                            }
                            else{
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
    private void initializeVariables(){
        userName = (EditText)findViewById(R.id.userName);
        userPassword = (EditText)findViewById(R.id.passwordEditText);
        userEmail = (EditText)findViewById(R.id.emailEditText);
        registerButton = (Button)findViewById(R.id.registerButton);
        userLogin = (TextView)findViewById(R.id.back_to_login);
    }
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
