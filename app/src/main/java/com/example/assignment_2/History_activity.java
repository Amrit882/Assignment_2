package com.example.assignment_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class History_activity extends AppCompatActivity {

    ArrayList<Products> history;
    RecyclerView historyList;
    RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        historyList = (RecyclerView) findViewById(R.id.historyListView);

        if(getIntent().hasExtra("bundle"))
        {
            Bundle bundleFromMainActivity = getIntent().getBundleExtra("bundle");
            history = bundleFromMainActivity.getParcelableArrayList("productHistory");
        }

        historyList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewAdapter(history, this);
        historyList.setAdapter(adapter);
    }
}