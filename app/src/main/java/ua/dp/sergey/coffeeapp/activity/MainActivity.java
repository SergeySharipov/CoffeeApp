package ua.dp.sergey.coffeeapp.activity;

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
import ua.dp.sergey.coffeeapp.adapter.CustomersAdapter;
import ua.dp.sergey.coffeeapp.adapter.ICustomerClickListener;
import ua.dp.sergey.coffeeapp.api.ServerAPIHelper;
import ua.dp.sergey.coffeeapp.api.IClientCustomer;
import ua.dp.sergey.coffeeapp.dialogfragment.AddCustomerDialogFragment;
import ua.dp.sergey.coffeeapp.dialogfragment.IDialogCloseListener;
import ua.dp.sergey.coffeeapp.model.Customer;

public class MainActivity extends AppCompatActivity implements IClientCustomer,
        ICustomerClickListener, IDialogCloseListener {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private ArrayList<Customer> mCustomers;
    private ServerAPIHelper mServerAPIHelper;

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
                AddCustomerDialogFragment addCustomerDialogFragment = new AddCustomerDialogFragment();
                addCustomerDialogFragment.setIDialogCloseListener(this);
                addCustomerDialogFragment.show(getSupportFragmentManager(),
                        "AddCustomerDialogFragment");
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
        adapter.initSwipe(mRecyclerView);
        mRecyclerView.setAdapter(adapter);

        mServerAPIHelper = new ServerAPIHelper(this);
        updateListItems();

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
    public void showMessage(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCustomerClick(String customerId) {
        Intent intent = OrdersActivity.newIntent(this, customerId);
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
    }

    @Override
    public void onCloseDialog() {
        updateListItems();
    }


}