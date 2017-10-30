package ua.dp.sergey.coffeeapp.dialogfragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import ua.dp.sergey.coffeeapp.R;
import ua.dp.sergey.coffeeapp.api.ServerAPIHelper;
import ua.dp.sergey.coffeeapp.model.Order;

/**
 * Created by Sergey-PC on 26.10.2017.
 */

public class AddOrderDialogFragment extends DialogFragment implements View.OnClickListener {

    private IDialogCloseListener mIDialogCloseListener;
    public static final String CUSTOMER_ID = "ua.dp.sergey.coffeeapp.dialogfragment.CUSTOMER_ID";
    private String mCustomerId = "0";
    private EditText mType, mWeight, mDateCreate, mDateDelivery;
    private ServerAPIHelper mServerAPIHelper;

    public static AddOrderDialogFragment newInstance(String customerId) {
        AddOrderDialogFragment addOrderDialogFragment = new AddOrderDialogFragment();

        Bundle args = new Bundle();
        args.putString(CUSTOMER_ID, customerId);
        addOrderDialogFragment.setArguments(args);

        return addOrderDialogFragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().setTitle("Add Order!");
        View v = inflater.inflate(R.layout.dialogfragment_add_order, null);

        mCustomerId = getArguments().getString(CUSTOMER_ID);

        v.findViewById(R.id.add_but).setOnClickListener(this);
        v.findViewById(R.id.cancel_but).setOnClickListener(this);

        mType = v.findViewById(R.id.et_type);
        mWeight = v.findViewById(R.id.et_weight);
        mDateCreate = v.findViewById(R.id.et_date_create);
        mDateDelivery = v.findViewById(R.id.et_date_delivery);

        mServerAPIHelper = new ServerAPIHelper();
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mIDialogCloseListener = (IDialogCloseListener)context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_but:
                String type = mType.getText().toString();
                String weight = mWeight.getText().toString();
                String dateCreate = mDateCreate.getText().toString();
                String dateDelivery = mDateDelivery.getText().toString();

                Order order = new Order(mCustomerId, type, weight, dateCreate, dateDelivery);

                mServerAPIHelper.addOrder(order);
            case R.id.cancel_but:
                getDialog().onBackPressed();

                if (mIDialogCloseListener != null)
                    mIDialogCloseListener.onCloseDialog();
                break;
        }
    }
}
