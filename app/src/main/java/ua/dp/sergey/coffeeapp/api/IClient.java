package ua.dp.sergey.coffeeapp.api;

import java.util.List;

import ua.dp.sergey.coffeeapp.model.Order;

/**
 * Created by Sergey-PC on 26.10.2017.
 */

public interface IClient{

    void updateListItems();

    void showMessage(String text);
}