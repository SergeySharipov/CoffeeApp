package ua.dp.sergey.coffeeapp.api;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Path;
import ua.dp.sergey.coffeeapp.App;
import ua.dp.sergey.coffeeapp.model.Customer;
import ua.dp.sergey.coffeeapp.model.Order;

/**
 * Created by Sergey-PC on 26.10.2017.
 */

public class ServerAPIHelper implements IServerAPI {
    private final String LOG_NAME = "ServerAPIHelper";
    private IClientCustomer mIClientCustomer;
    private IClientOrder mIClientOrder;

    public ServerAPIHelper(IClientCustomer iClientCustomer) {
        mIClientCustomer = iClientCustomer;
    }

    public ServerAPIHelper(IClientOrder iClientOrder) {
        mIClientOrder = iClientOrder;
    }

    public ServerAPIHelper() {
    }

    @Override
    public Call<List<Customer>> getCustomers() {

        App.getApi().getCustomers().enqueue(new Callback<List<Customer>>() {
            @Override
            public void onResponse(Call<List<Customer>> call, Response<List<Customer>> response) {
                Log.e(LOG_NAME, response.message());
                if (mIClientCustomer != null) {
                    mIClientCustomer.updateListItems(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Customer>> call, Throwable t) {
                Log.e(LOG_NAME, t.getMessage());
            }
        });
        return null;
    }

    @Override
    public Call<Customer> getCustomer(@Path("id") String id) {
        App.getApi().getCustomer(id).enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                Log.e(LOG_NAME, response.message());
            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {
                Log.e(LOG_NAME, t.getMessage());
            }
        });
        return null;
    }

    @Override
    public Call<Customer> addCustomer(@Body Customer customer) {
        App.getApi().addCustomer(customer).enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                Log.e(LOG_NAME, response.message());
            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {
                Log.e(LOG_NAME, t.getMessage());
            }
        });
        return null;
    }

    @Override
    public Call<Customer> updateCustomer(@Path("id") String id, @Body Customer customer) {
        App.getApi().updateCustomer(id, customer).enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                Log.e(LOG_NAME, response.message());
            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {
                Log.e(LOG_NAME, t.getMessage());
            }
        });
        return null;
    }

    @Override
    public Call<Customer> deleteCustomer(@Path("id") String id) {
        App.getApi().deleteCustomer(id).enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                Log.e(LOG_NAME, response.message());
            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {
                Log.e(LOG_NAME, t.getMessage());
            }
        });
        return null;
    }

    @Override
    public Call<List<Order>> getOrders() {
        App.getApi().getOrders().enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                Log.e(LOG_NAME, response.message());
                if (mIClientOrder != null) {
                    mIClientOrder.updateListItems(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                Log.e(LOG_NAME, t.getMessage());
            }
        });
        return null;
    }

    @Override
    public Call<List<Order>> getOrders(@Path("customer_id") String customerId) {
        App.getApi().getOrders(customerId).enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                Log.e(LOG_NAME, response.message());
                if (mIClientOrder != null) {
                    mIClientOrder.updateListItems(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                Log.e(LOG_NAME, t.getMessage());
            }
        });
        return null;
    }

    @Override
    public Call<Order> getOrder(@Path("id") String id) {
        App.getApi().getOrder(id).enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                Log.e(LOG_NAME, response.message());
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                Log.e(LOG_NAME, t.getMessage());
            }
        });
        return null;
    }

    @Override
    public Call<Order> addOrder(@Body Order order) {
        App.getApi().addOrder(order).enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                Log.e(LOG_NAME, response.message());
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                Log.e(LOG_NAME, t.getMessage());
            }
        });
        return null;
    }

    @Override
    public Call<Order> updateOrder(@Path("id") String id, @Body Order order) {
        App.getApi().updateOrder(id, order).enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                Log.e(LOG_NAME, response.message());
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                Log.e(LOG_NAME, t.getMessage());
            }
        });
        return null;
    }

    @Override
    public Call<Order> deleteOrder(@Path("id") String id) {
        App.getApi().deleteOrder(id).enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                Log.e(LOG_NAME, response.message());
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                Log.e(LOG_NAME, t.getMessage());
            }
        });
        return null;
    }
}
