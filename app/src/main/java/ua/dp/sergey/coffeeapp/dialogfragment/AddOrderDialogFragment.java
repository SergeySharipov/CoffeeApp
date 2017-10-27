package ua.dp.sergey.coffeeapp.dialogfragment;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ua.dp.sergey.coffeeapp.R;

/**
 * Created by Sergey-PC on 26.10.2017.
 */

public class AddOrderDialogFragment extends DialogFragment implements View.OnClickListener {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().setTitle("Add Order!");
        View v = inflater.inflate(R.layout.dialogfragment_add_order, null);
        v.findViewById(R.id.add_but).setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_but:
                break;
            case R.id.cancel_but:
                getDialog().onBackPressed();
                break;
        }
    }
}
