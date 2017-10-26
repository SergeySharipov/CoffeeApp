package ua.dp.sergey.coffeeapp.api;

import java.util.List;

import ua.dp.sergey.coffeeapp.model.Customer;

/**
 * Created by Sergey-PC on 26.10.2017.
 */

public interface IClientCustomer extends IClient{

    void updateListItems(List<Customer> list);
}
