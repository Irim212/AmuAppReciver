package com.example.amuappreciver;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.amuappreciver.Common.Common;
import com.example.amuappreciver.Model.Food;
import com.example.amuappreciver.Model.Order;
import com.example.amuappreciver.ViewHolder.OrderViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderList extends AppCompatActivity {

    FirebaseRecyclerAdapter<Order, OrderViewHolder> adapter;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    String restaurantId = "";
    ArrayAdapter<String> mealAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

        recyclerView = (RecyclerView) findViewById(R.id.orderList);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        restaurantId = Common.currentRestaurant.getRestaurantId();
        loadOrders(restaurantId);
    }

    private void loadOrders(final String restaurantId) {

        Query ordered = FirebaseDatabase.getInstance().getReference("/Requests").orderByChild("restaurantId").equalTo(restaurantId);
        final FirebaseRecyclerOptions<Order> options =
                new FirebaseRecyclerOptions.Builder<Order>()
                    .setQuery(ordered, Order.class)
                    .build();
        adapter = new FirebaseRecyclerAdapter<Order, OrderViewHolder>(options) {
            @NonNull
            @Override
            public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.order_component, viewGroup, false);
                return new OrderViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull final OrderViewHolder holder, int position, @NonNull Order model) {
                holder.orderIdTextView.setText(getRef(position).getKey());
                holder.addressTextView.setText(model.getAddress());
                holder.phoneTextView.setText(model.getPhone());
                holder.totalTextView.setText(model.getTotal());

                String requestKey = getRef(position).getKey().toString();
                FirebaseDatabase db = FirebaseDatabase.getInstance();
                Query queryRef = db.getReference("Requests/" + requestKey + "/food");
                final ArrayList<String> foodList = new ArrayList<String>();

                queryRef.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        Food food = dataSnapshot.getValue(Food.class);
                        foodList.add(food.getMenuName() + " w ilości " + food.getAmount());
                        mealAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.row, foodList);
                        holder.foodList.setAdapter(mealAdapter);
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                } );
                holder.foodList.setEnabled(false);
                holder.foodList.setExpanded(true);
            }
        };
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
