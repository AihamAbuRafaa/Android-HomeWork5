package com.example.majdh.homework4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DonateActivity extends AppCompatActivity
{
    DatabaseReference database;
    TextView donations_rec;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);
        database = FirebaseDatabase.getInstance().getReference();
        donations_rec = (TextView)findViewById(R.id.donations_rec_TV);

        updateDonations_rec();
    }

    public void donateBtn_handle(View view)
    {
        TextView message = (TextView)findViewById(R.id.donateMsg);
        EditText money = (EditText)findViewById(R.id.money_value_ET);
        if(money.getText().toString().isEmpty())
        {
            message.setTextColor(getResources().getColor(R.color.red));
            message.setText("Please insert money value to donate.");
            return;
        }
        double donations_val = Double.parseDouble(donations_rec.getText().toString())+
                               Double.parseDouble(money.getText().toString());
        database.child("donations").setValue(donations_val+"");
        message.setTextColor(getResources().getColor(R.color.green));
        message.setText("Thank you for donating.");
    }

    private void updateDonations_rec()
    {
        database.child("donations").addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            String value = dataSnapshot.getValue(String.class);
            if(value == null)
                return;
            donations_rec.setText(value);
        }

        @Override
        public void onCancelled(DatabaseError error) {
        }
    });
    }
}
