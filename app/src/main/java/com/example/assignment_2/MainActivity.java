package com.example.assignment_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button oneBut, twoBut, threeBut, fourBut, fiveBut, sixBut, sevenBut, eightBut, nineBut, zeroBut;
    Button clearBut, totalBut, managerBut;
    ListView productList;
    Spinner landScapeProducts;
    ProductAdapter adapter;
    ArrayList<Products> listOfProducts;
    ArrayList<Products> historyList;
    TextView productText;
    TextView qtyText;
    TextView totalText;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        builder = new AlertDialog.Builder(this);

        productList = (ListView) findViewById(R.id.productListView);
        landScapeProducts = (Spinner) findViewById(R.id.productSpinner);

        productText = (TextView) findViewById(R.id.textView);
        qtyText = (TextView) findViewById(R.id.quantity);
        totalText = (TextView) findViewById(R.id.totalT);

        oneBut = (Button) findViewById(R.id.button1);
        twoBut = (Button) findViewById(R.id.button2);
        threeBut = (Button) findViewById(R.id.button3);
        fourBut = (Button) findViewById(R.id.button4);
        fiveBut = (Button) findViewById(R.id.button5);
        sixBut = (Button) findViewById(R.id.button6);
        sevenBut = (Button) findViewById(R.id.button7);
        eightBut = (Button) findViewById(R.id.button8);
        nineBut = (Button) findViewById(R.id.button9);
        zeroBut = (Button) findViewById(R.id.button0);

        clearBut = (Button) findViewById(R.id.buttonC);
        totalBut = (Button) findViewById(R.id.buttonT);
        managerBut = (Button) findViewById(R.id.buttonM);

        if (savedInstanceState == null) {
            listOfProducts = new ArrayList<>(1);
            listOfProducts.add(new Products("Pante", 10, 20.44));
            listOfProducts.add(new Products("Shoes", 100, 10.44));
            listOfProducts.add(new Products("Hats", 30, 5.9));
        }
        else
            listOfProducts = savedInstanceState.getParcelableArrayList("productList");

        if(savedInstanceState == null)
            historyList = new ArrayList<>(1);
        else
            historyList = savedInstanceState.getParcelableArrayList("listOfHistory");

        oneBut.setOnClickListener(this);
        twoBut.setOnClickListener(this);
        threeBut.setOnClickListener(this);
        fourBut.setOnClickListener(this);
        fiveBut.setOnClickListener(this);
        sixBut.setOnClickListener(this);
        sevenBut.setOnClickListener(this);
        eightBut.setOnClickListener(this);
        nineBut.setOnClickListener(this);
        zeroBut.setOnClickListener(this);
        clearBut.setOnClickListener(this);
        totalBut.setOnClickListener(this);
        managerBut.setOnClickListener(this);

        if(zeroBut.getText().toString().equals("O")) {
            String[] spinnerList = new String[] {listOfProducts.get(0).productName, listOfProducts.get(1).productName, listOfProducts.get(2).productName};
            ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, R.layout.spinner_row, R.id.spinnerText, spinnerList);
            landScapeProducts.setAdapter(spinnerAdapter);

            landScapeProducts.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    productText.setText(listOfProducts.get(i).productName);
                }
                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    //productText.setText("Product Type");
                }
            });

        }
        else if (zeroBut.getText().toString().equals("0")){
            adapter = new ProductAdapter(this, listOfProducts);
            productList.setAdapter(adapter);

            productList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    productText.setText(listOfProducts.get(i).productName);
                }
            });
        }
    }

    @Override
    public void onClick(View view) {
        if(qtyText.getText().toString().equals("Quantity"))
            qtyText.setText("");
        String text = qtyText.getText().toString();
        String op = ((Button)view).getText().toString();

        switch (op) {
            case "C":
                clear();
                totalText.setText("Total");
                break;
            case "BUY":
                double price = totalPrice();
                if(price > 0) {
                    totalText.setText(String.valueOf(price));
                }
                clear();
                break;
            case "Manager":
                Intent myIntent = new Intent(this, Second_activity.class);
                Bundle bundle = new Bundle();

                bundle.putParcelableArrayList("productHistory", historyList);
                myIntent.putExtra("bundle", bundle);

                startActivity(myIntent);
                break;
            default:
                qtyText.setText(text + op);
                break;
        }
    }

    public void clear() {
        qtyText.setText("Quantity");
        productText.setText("Product Type");
    }

    public double totalPrice() {
        String type;
        Integer qty;
        double price = 0;
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
        String strDate = dateFormat.format(date);
        type = productText.getText().toString();
        if(type.equals("Product Type") || (qtyText.getText().toString()).equals(""))
        {
            Toast.makeText(this, "All fields are required!!", Toast.LENGTH_LONG).show();
            return 0;
        }
        else {
            qty = Integer.parseInt(qtyText.getText().toString());
            for (int i = 0; i < listOfProducts.size(); i++) {
                if (listOfProducts.get(i).productName.equals(type)) {
                    price = listOfProducts.get(i).productPrice;
                    if(qty > listOfProducts.get(i).productQty)
                        Toast.makeText(this, "Not enough Quantity in the stock!!", Toast.LENGTH_LONG).show();
                    else {
                        historyList.add(new Products(listOfProducts.get(i).productName, qty, (price * qty), strDate));
                        builder.create();
                        builder.setTitle("Thank you for your purchase");
                        builder.setMessage("Your purchase is " + qty + " " + listOfProducts.get(i).productName + " for " + (price * qty));
                        builder.show();
                        listOfProducts.get(i).updateQty(qty);
                        if(zeroBut.getText().toString().equals("0"))
                            productList.setAdapter(adapter);
                    }
                }
            }
            return price * qty;
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelableArrayList("productList", listOfProducts);

        outState.putParcelableArrayList("listOfHistory", historyList);
    }

}
