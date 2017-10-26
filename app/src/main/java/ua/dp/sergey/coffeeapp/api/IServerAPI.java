package ua.dp.sergey.coffeeapp.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import ua.dp.sergey.coffeeapp.model.Customer;
import ua.dp.sergey.coffeeapp.model.Order;

/**
 * Created by Sergey-PC on 21.10.2017.
 */

public interface IServerAPI {

    /**
     * API for customers
     */
    @GET("/Customer")
    Call<List<Customer>> getCustomers();

    @GET("/Customer/{id}")
    Call<Customer> getCustomer(@Path("id") String id);

    @POST("/Customer")
    Call<Customer> addCustomer(@Body Customer customer);

    @PUT("/Customer/{id}")
    Call<Customer> updateCustomer(@Path("id") String id, @Body Customer customer);

    @DELETE("/Customer/{id}")
    Call<Customer> deleteCustomer(@Path("id") String id);

    /**
     * API for orders
     */
    @GET("/Order")
    Call<List<Order>> getOrders();

    @GET("/Order/Customer/{customer_id}")
    Call<List<Order>> getOrders(@Path("customer_id") String customerId);

    @GET("/Order/{id}")
    Call<Order> getOrder(@Path("id") String id);

    @POST("/Order")
    Call<Order> addOrder(@Body Order order);

    @PUT("/Order/{id}")
    Call<Order> updateOrder(@Path("id") String id, @Body Order order);

    @DELETE("/Order/{id}")
    Call<Order> deleteOrder(@Path("id") String id);
}
