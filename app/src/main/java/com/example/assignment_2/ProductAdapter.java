package com.example.assignment_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductAdapter extends BaseAdapter {

    Context context;
    ArrayList<Products> products;

    public ProductAdapter(Context context, ArrayList<Products> products) {
        this.context = context;
        this.products = products;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null) {
            view = LayoutInflater.from(context).inflate((R.layout.product_list), null);
        }
        TextView prodName = (TextView) view.findViewById(R.id.productName);
        TextView prodQty = (TextView) view.findViewById(R.id.productQty);
        TextView prodPrice = (TextView) view.findViewById(R.id.productPrice);

        prodName.setText(products.get(i).productName);
        prodQty.setText(String.valueOf(products.get(i).productQty));
        prodPrice.setText(String.valueOf(products.get(i).productPrice));

        return view;
    }
}
