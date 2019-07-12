package com.example.majdh.homework4;

import android.content.Intent;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
    }

    public void registerBtn_handle(View view)
    {
        final TextView message = (TextView)findViewById(R.id.registerMsg);
        final EditText email = (EditText)findViewById(R.id.emailET);
        final EditText password = (EditText)findViewById(R.id.passwordET);
        EditText rePassword = (EditText)findViewById(R.id.rePasswordET);
        if(email.getText().toString().isEmpty() || password.getText().toString().isEmpty() || rePassword.getText().toString().isEmpty())
        {
            message.setTextColor(getResources().getColor(R.color.red));
            message.setText("Please fill out all fields.");
            return;
        }
        else if(!password.getText().toString().equals(rePassword.getText().toString()))
        {
            message.setTextColor(getResources().getColor(R.color.red));
            message.setText("password and Confirm password do not match!");
            return;
        }
        mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            HashMap<String, String> taskMap = new HashMap<String, String>();
                            taskMap.put("Email", email.getText().toString());
                            taskMap.put("Password", password.getText().toString());
                            database.child("Users").push().setValue(taskMap);
                            Intent notes = new Intent(getBaseContext(), NotesActivity.class);
                            startActivity(notes);
                        } else {
                            message.setText(task.getException().toString());
                        }
                    }
                });
    }

    private boolean isValidEmail(CharSequence email)
    {
        if (TextUtils.isEmpty(email))
            return false;
        else
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
