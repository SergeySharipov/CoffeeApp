package ua.dp.sergey.coffeeapp.dialogfragment;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import ua.dp.sergey.coffeeapp.R;
import ua.dp.sergey.coffeeapp.api.ServerAPIHelper;
import ua.dp.sergey.coffeeapp.model.Customer;

/**
 * Created by Sergey-PC on 26.10.2017.
 */

public class AddCustomerDialogFragment extends DialogFragment implements View.OnClickListener {

    private IDialogCloseListener mIDialogCloseListener;
    private EditText mFirstName, mLastName, mAddress, mPhone;
    private ServerAPIHelper mServerAPIHelper;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().setTitle("Add Customer");
        View v = inflater.inflate(R.layout.dialogfragment_add_customer, null);
        v.findViewById(R.id.add_but).setOnClickListener(this);

        mFirstName = v.findViewById(R.id.et_first_name);
        mLastName = v.findViewById(R.id.et_last_name);
        mAddress = v.findViewById(R.id.et_address);
        mPhone = v.findViewById(R.id.et_phone);

        mServerAPIHelper = new ServerAPIHelper();

        return v;
    }

    public void setIDialogCloseListener(IDialogCloseListener IDialogCloseListener) {
        mIDialogCloseListener = IDialogCloseListener;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_but:
                String firstName = mFirstName.getText().toString();
                String lastName = mLastName.getText().toString();
                String address = mAddress.getText().toString();
                String phone = mPhone.getText().toString();

                Customer customer = new Customer(firstName, lastName, address, phone);

                mServerAPIHelper.addCustomer(customer);
            case R.id.cancel_but:
                getDialog().onBackPressed();
                
                mIDialogCloseListener.onCloseDialog();
                break;
        }
    }
}
