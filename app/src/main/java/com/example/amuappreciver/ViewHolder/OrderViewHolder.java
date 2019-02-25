package com.example.amuappreciver.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.amuappreciver.R;
import com.github.paolorotolo.expandableheightlistview.ExpandableHeightListView;

import org.w3c.dom.Text;

public class OrderViewHolder extends RecyclerView.ViewHolder {

    public TextView orderIdTextView;
    public TextView addressTextView;
    public TextView phoneTextView;
    public ExpandableHeightListView foodList;
    public TextView totalTextView;



    public OrderViewHolder(@NonNull View itemView) {
        super(itemView);
        orderIdTextView = (TextView) itemView.findViewById(R.id.orderIdTextView);
        addressTextView = (TextView) itemView.findViewById(R.id.orderAddressTextView);
        phoneTextView = (TextView) itemView.findViewById(R.id.phoneTextView);
        foodList = (ExpandableHeightListView) itemView.findViewById(R.id.foodList);
        totalTextView = (TextView) itemView.findViewById(R.id.totalTextView);
    }

}
