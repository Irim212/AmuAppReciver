package com.example.amuappreciver;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.amuappreciver.Common.Common;
import com.example.amuappreciver.Model.Restaurant;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText idEditText, passwordEditText;
    Button loginButton;
    FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idEditText = (EditText) findViewById(R.id.idEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        loginButton = (Button) findViewById(R.id.loginButton);

        db = FirebaseDatabase.getInstance();
        final DatabaseReference table_restaurant = db.getReference("Restaurant");

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setMessage("Trwa logowanie");
                progressDialog.show();

                table_restaurant.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        progressDialog.dismiss();
                        //Check user exist
                        if(dataSnapshot.child(idEditText.getText().toString().trim()).exists())
                        {
                            Restaurant restaurant = dataSnapshot.child(idEditText.getText().toString().trim()).getValue(Restaurant.class);
                            restaurant.setRestaurantId(idEditText.getText().toString());
                            if(restaurant.getPassword().equals(passwordEditText.getText().toString().trim()))
                            {
                                Toast.makeText(MainActivity.this, "Zalogowano pomyślnie", Toast.LENGTH_SHORT).show();
                                Intent mainPage = new Intent(MainActivity.this, OrderList.class);
                                Common.currentRestaurant = restaurant;
                                startActivity(mainPage);
                            }else{
                                Toast.makeText(MainActivity.this, "Błąd logowania", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(MainActivity.this, "Nie ma takiego użytkownika", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
