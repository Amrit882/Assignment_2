package com.example.assignment_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class History_activity extends AppCompatActivity {

    ArrayList<Products> history;
    RecyclerView historyList;
    RecyclerViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ArrayList<Products> newItems = new ArrayList<>(1);

        historyList = (RecyclerView) findViewById(R.id.historyListView);

        if (getIntent().hasExtra("bundle")) {
            Bundle bundleFromMainActivity = getIntent().getBundleExtra("bundle");
            newItems = bundleFromMainActivity.getParcelableArrayList("productHistory");
        }

        if (savedInstanceState == null) {
            history = new ArrayList<>(1);
        } else
            history = savedInstanceState.getParcelableArrayList("listOfHistory");

        for (Products p : newItems) {
            history.add(new Products(p));
        }

        historyList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewAdapter(history, this, new RecyclerViewAdapter.OnItemClickListner() {
            @Override
            public void onProductClicked(Products item) {
                Toast.makeText(getApplicationContext(), item.productName, Toast.LENGTH_LONG).show();
                Intent myIntent = new Intent(History_activity.this, History_value.class);
                myIntent.putExtra("product", item);
                startActivity(myIntent);
            }
        });
        historyList.setAdapter(adapter);
    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("listOfHistory", history);
    }


}
