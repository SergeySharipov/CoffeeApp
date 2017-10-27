package ua.dp.sergey.coffeeapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ua.dp.sergey.coffeeapp.R;
import ua.dp.sergey.coffeeapp.adapter.OrdersAdapter;
import ua.dp.sergey.coffeeapp.api.IClientOrder;
import ua.dp.sergey.coffeeapp.api.ServerAPIHelper;
import ua.dp.sergey.coffeeapp.dialogfragment.AddOrderDialogFragment;
import ua.dp.sergey.coffeeapp.dialogfragment.IDialogCloseListener;
import ua.dp.sergey.coffeeapp.model.Order;

public class OrdersActivity extends AppCompatActivity implements IClientOrder,IDialogCloseListener {

    public static final String CUSTOMER_ID = "ua.dp.sergey.coffeeapp.activity.CUSTOMER_ID";
    private String mCustomerId;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private ArrayList<Order> mOrders;
    private ServerAPIHelper mServerAPIHelper;

    public static Intent newIntent(Context context, String customerId) {
        Intent intent = new Intent(context, OrdersActivity.class);
        intent.putExtra(CUSTOMER_ID, customerId);
        return intent;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_but:
                AddOrderDialogFragment addOrderDialogFragment =
                        AddOrderDialogFragment.newInstance(mCustomerId);
                addOrderDialogFragment.setIDialogCloseListener(this);
                addOrderDialogFragment.show(getSupportFragmentManager(),
                        "AddOrderDialogFragment");
                return true;
            case R.id.about_but:
                //
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        init();
    }

    private void init() {
        mCustomerId = getIntent().getStringExtra(CUSTOMER_ID);

        mOrders = new ArrayList<>();

        mRecyclerView = (RecyclerView) findViewById(R.id.customers_recycle_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        OrdersAdapter adapter = new OrdersAdapter(mOrders);
        mRecyclerView.setAdapter(adapter);

        mServerAPIHelper = new ServerAPIHelper(this);
        mServerAPIHelper.getOrders(mCustomerId);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                updateListItems();
            }
        });
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void updateListItems(List<Order> list) {
        mOrders.clear();
        mOrders.addAll(list);

        ((OrdersAdapter) mRecyclerView.getAdapter()).swap(mOrders);
        mSwipeRefreshLayout.setRefreshing(false);
        showMessage("updateListItems");
    }

    @Override
    public void updateListItems() {
        mServerAPIHelper.getOrders(mCustomerId);
        showMessage("updateListItems");
    }

    @Override
    public void showMessage(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCloseDialog() {
        updateListItems();
    }
}
