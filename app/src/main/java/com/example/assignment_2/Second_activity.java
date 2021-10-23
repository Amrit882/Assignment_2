package com.example.assignment_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class Second_activity extends AppCompatActivity implements View.OnClickListener {

    Button historyBut;

    ArrayList<Products> history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        ArrayList<Products> newItems = new ArrayList<>(1);
        
        historyBut = (Button) findViewById(R.id.buttonHistory);

        historyBut.setOnClickListener(this);


        if(getIntent().hasExtra("bundle"))
        {
            Bundle bundleFromMainActivity = getIntent().getBundleExtra("bundle");
            newItems = bundleFromMainActivity.getParcelableArrayList("productHistory");
        }


        if(savedInstanceState == null) {
            history = new ArrayList<>(1);
        }
        else
            history = savedInstanceState.getParcelableArrayList("listOfHistory");

        for(Products p : newItems)
        {
            history.add(p);
        }
    }

    @Override
    public void onClick(View view) {
        Intent myIntent = new Intent(this, History_activity.class);
        Bundle bundle = new Bundle();

        bundle.putParcelableArrayList("productHistory", history);
        myIntent.putExtra("bundle", bundle);
        startActivity(myIntent);
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("listOfHistory", history);
    }
}