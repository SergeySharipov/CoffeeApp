package ua.dp.sergey.coffeeapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
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

public class CustomersAdapter extends RecyclerView.Adapter<CustomersAdapter.ViewHolder> {

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

        holder.firstName.setText(customer.getFirstName());
        holder.lastName.setText(customer.getLastName());
        holder.phone.setText(customer.getPhone());
        holder.address.setText(customer.getAddress());

        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCustomerClickListener.onCustomerClicked(customer.getId());
            }
        });
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
    }
}
