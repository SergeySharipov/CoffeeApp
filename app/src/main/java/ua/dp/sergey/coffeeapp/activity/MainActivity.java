package ua.dp.sergey.coffeeapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ua.dp.sergey.coffeeapp.R;
import ua.dp.sergey.coffeeapp.adapter.CustomersAdapter;
import ua.dp.sergey.coffeeapp.adapter.ICustomerClickListener;
import ua.dp.sergey.coffeeapp.api.ServerAPIHelper;
import ua.dp.sergey.coffeeapp.api.IClientCustomer;
import ua.dp.sergey.coffeeapp.model.Customer;

public class MainActivity extends AppCompatActivity implements IClientCustomer, ICustomerClickListener {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private ArrayList<Customer> mCustomers;
    private ServerAPIHelper mServerAPIHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        mCustomers = new ArrayList<>();

        mRecyclerView = (RecyclerView) findViewById(R.id.customers_recycle_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        CustomersAdapter adapter = new CustomersAdapter(mCustomers);
        adapter.setItemClickListener(this);
        mRecyclerView.setAdapter(adapter);

        mServerAPIHelper = new ServerAPIHelper(this);
        mServerAPIHelper.getCustomers();

        mSwipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                updateListItems();
            }
        });
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void showMessage(String text) {
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCustomerClicked(String customerId) {
        Intent intent = new Intent(this, OrdersActivity.class);
        intent.putExtra(OrdersActivity.CUSTOMER_ID, customerId);
        startActivity(intent);
    }

    @Override
    public void updateListItems(List<Customer> list) {
        mCustomers.clear();
        mCustomers.addAll(list);

        ((CustomersAdapter) mRecyclerView.getAdapter()).swap(mCustomers);
        mSwipeRefreshLayout.setRefreshing(false);
        showMessage("updateListItems");
    }

    @Override
    public void updateListItems() {
        mServerAPIHelper.getCustomers();
        showMessage("updateListItems");
    }
}