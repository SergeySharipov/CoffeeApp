package ua.dp.sergey.coffeeapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ua.dp.sergey.coffeeapp.R;
import ua.dp.sergey.coffeeapp.model.Customer;

/**
 * Created by Sergey-PC on 24.10.2017.
 */

public class CustomersAdapter extends RecyclerView.Adapter<CustomersAdapter.ViewHolder> implements ISimpleItemTouchCallback{

    private ArrayList<Customer> mCustomerList;
    private ICustomerClickListener mCustomerClickListener;

    public CustomersAdapter(ArrayList<Customer> customerList) {
        this.mCustomerList = customerList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_customer, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Customer customer = mCustomerList.get(position);
        holder.onBind(customer);
    }

    public void swap(@NonNull ArrayList<Customer> customerList){
        mCustomerList = customerList;
        notifyDataSetChanged();
    }

    public void setItemClickListener(ICustomerClickListener customerClickListener) {
        mCustomerClickListener = customerClickListener;
    }

    @Override
    public int getItemCount() {
        if (mCustomerList == null)
            return 0;
        return mCustomerList.size();
    }

    @Override
    public void removeItem(int position) {
        mCustomerList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mCustomerList.size());
    }

    @Override
    public void editItem(int position) {
        //ToDo
    }

    public void initSwipe(RecyclerView recyclerView){
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new SimpleItemTouchCallback(
                recyclerView.getContext(),this);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        CardView mCardView;
        TextView firstName;
        TextView lastName;
        TextView phone;
        TextView address;

        ViewHolder(View itemView) {
            super(itemView);
            mCardView = itemView.findViewById(R.id.card_view);
            firstName = (TextView) itemView.findViewById(R.id.tv_first_name);
            lastName = (TextView) itemView.findViewById(R.id.tv_last_name);
            phone = (TextView) itemView.findViewById(R.id.tv_phone);
            address = (TextView) itemView.findViewById(R.id.tv_address);
        }

        void onBind(final Customer customer){
            firstName.setText(customer.getFirstName());
            lastName.setText(customer.getLastName());
            phone.setText(customer.getPhone());
            address.setText(customer.getAddress());

            mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCustomerClickListener.onCustomerClick(customer.getId());
                }
            });
        }
    }
}
