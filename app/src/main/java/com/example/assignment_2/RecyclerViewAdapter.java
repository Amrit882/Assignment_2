package com.example.assignment_2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.viewHolder> {

    ArrayList<Products> historyList;
    Context mContext;

    public interface OnItemClickListner{
        void onProductClicked(Products item);
    }

    private final OnItemClickListner listner;

    public RecyclerViewAdapter(ArrayList<Products> historyList, Context mContext, OnItemClickListner listner) {
        this.historyList = historyList;
        this.mContext = mContext;
        this.listner = listner;
    }


    public static class viewHolder extends RecyclerView.ViewHolder {

        private final TextView prodName;
        private final TextView prodQty;
        private final TextView prodPrice;


        public viewHolder(@NonNull View itemView) {
            super(itemView);
            prodName = itemView.findViewById(R.id.productName);
            prodQty = itemView.findViewById(R.id.productQty);
            prodPrice = itemView.findViewById(R.id.productPrice);
        }

        public TextView getProdName() {
            return prodName;
        }

        public TextView getProdQty() {
            return prodQty;
        }

        public TextView getProdPrice() {
            return prodPrice;
        }

    }



    @NonNull
    @Override
    public RecyclerViewAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater myInflater = LayoutInflater.from(mContext);
        View view = myInflater.inflate(R.layout.product_list, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.viewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.getProdName().setText(historyList.get(position).productName);
        holder.getProdQty().setText(String.valueOf(historyList.get(position).productQty));
        holder.getProdPrice().setText(String.valueOf(historyList.get(position).productPrice));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listner.onProductClicked(historyList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }
}
