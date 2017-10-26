package ua.dp.sergey.coffeeapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ua.dp.sergey.coffeeapp.R;
import ua.dp.sergey.coffeeapp.model.Order;

/**
 * Created by Sergey-PC on 24.10.2017.
 */

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.ViewHolder> {

    private ArrayList<Order> mOrderList;

    public OrdersAdapter(ArrayList<Order> OrderList) {
        this.mOrderList = OrderList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_customer, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Order post = mOrderList.get(position);

        holder.post.setText(post.getCustomerId());
        holder.site.setText(post.getDateCreate());
    }

    public void swap(@NonNull ArrayList<Order> OrderList){
        mOrderList = OrderList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mOrderList == null)
            return 0;
        return mOrderList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView post;
        TextView site;

        ViewHolder(View itemView) {
            super(itemView);
            post = (TextView) itemView.findViewById(R.id.postitem_post);
            site = (TextView) itemView.findViewById(R.id.postitem_site);
        }
    }
}
