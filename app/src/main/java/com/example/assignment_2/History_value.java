package com.example.assignment_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class History_value extends AppCompatActivity {

    Products product;
    TextView productName;
    TextView productPrice;
    TextView productDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_value);

        productName = (TextView) findViewById(R.id.product);
        productPrice = (TextView) findViewById(R.id.price);
        productDate = (TextView) findViewById(R.id.date);

        if(getIntent().hasExtra("product"))
        {

            product = getIntent().getExtras().getParcelable("product");
        }

        productName.setText(product.productName);
        productPrice.setText(String.valueOf(product.productPrice));
        productDate.setText(product.purchaseDate);
    }
}