package ua.dp.sergey.coffeeapp.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
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
    private Paint p = new Paint(Paint.FILTER_BITMAP_FLAG);

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
        mRecyclerView.setAdapter(adapter);

        mServerAPIHelper = new ServerAPIHelper(this);
        updateListItems();

        mSwipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                updateListItems();
            }
        });
        mSwipeRefreshLayout.setRefreshing(true);

        initSwipe();
    }

    @Override
    public void showMessage(String text) {
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCustomerClick(String customerId) {
        Intent intent = OrdersActivity.newIntent(this,customerId);
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

    private void initSwipe(){
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();

                if (direction == ItemTouchHelper.LEFT){
                    ((CustomersAdapter) mRecyclerView.getAdapter()).removeItem(position);
                } else {
//                    removeView();
//                    edit_position = position;
//                    alertDialog.setTitle("Edit Country");
//                    et_country.setText(countries.get(position));
//                    alertDialog.show();
                }
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

                Bitmap icon;
                if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE){

                    View itemView = viewHolder.itemView;
                    float height = (float) itemView.getBottom() - (float) itemView.getTop();
                    float width = height / 3;

                    if(dX > 0){
                        p.setColor(getResources().getColor(R.color.Green));
                        int alpha=(int)dX/2;
                        if(alpha<255)
                            p.setAlpha(alpha);
                        RectF background = new RectF((float) itemView.getLeft(), (float) itemView.getTop(), (itemView.getRight()/2)+dX,(float) itemView.getBottom());
                        c.drawRoundRect(background,10,10,p);
                        icon = BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_edit);
                        RectF icon_dest = new RectF((float) itemView.getLeft() + width ,(float) itemView.getTop() + width,(float) itemView.getLeft()+ 2*width,(float)itemView.getBottom() - width);
                        c.drawBitmap(icon,null,icon_dest,p);
                    } else if(dX < 0) {
                        p.setColor(getResources().getColor(R.color.Red));
                        int alpha=(int)(dX*-1)/2;
                        if(alpha<255)
                            p.setAlpha(alpha);
                        RectF background = new RectF((float) (itemView.getRight()/2)+dX, (float) itemView.getTop(),(float) itemView.getRight(), (float) itemView.getBottom());
                        c.drawRoundRect(background,10,10,p);
                        icon = BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_delete);
                        RectF icon_dest = new RectF((float) itemView.getRight() - 2*width ,(float) itemView.getTop() + width,(float) itemView.getRight() - width,(float)itemView.getBottom() - width);
                        c.drawBitmap(icon,null,icon_dest,p);
                    }
                }
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }
}