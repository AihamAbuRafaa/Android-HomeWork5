package com.example.majdh.homework4;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity
{
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void LoginBtn_handle(View view)
    {
        final TextView message = (TextView)findViewById(R.id.LoginMsg);
        EditText email = (EditText)findViewById(R.id.emailET);
        EditText password = (EditText)findViewById(R.id.passwordET);
        if(email.getText().toString().isEmpty() || password.getText().toString().isEmpty())
        {
            message.setText("Please fill out all fields.");
            return;
        }
        firebaseAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            Intent notes = new Intent(getBaseContext(), NotesActivity.class);
                            startActivity(notes);
                        } else {
                            // If sign in fails, display a message to the user.
                            message.setText("Email or Password is incorrect");
                        }
                    }
                });
    }

    public void registerBtn_handle(View view)
    {
        Intent register = new Intent(this, RegisterActivity.class);
        startActivity(register);
    }

    public void donateBtn_handle(View view)
    {
        Intent donate = new Intent(this, DonateActivity.class);
        startActivity(donate);
		
    }
}
