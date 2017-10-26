package ua.dp.sergey.coffeeapp.model;

/**
 * Created by Sergey-PC on 16.10.2017.
 * "customer_id": "0",
 * "type": "Late",
 * "weight": "1",
 * "date_create": "14/10/2017",
 * "date_delivery": "30/10/2017"
 */

//public class Order {
//
//    private String customer_id, type, weight, date_create, date_delivery;
//
//    public Order(String customer_id, String type, String weight, String date_create, String date_delivery) {
//        this.customer_id = customer_id;
//        this.type = type;
//        this.weight = weight;
//        this.date_create = date_create;
//        this.date_delivery = date_delivery;
//    }
//
//    public String getCustomer_id() {
//        return customer_id;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public String getWeight() {
//        return weight;
//    }
//
//    public String getDate_create() {
//        return date_create;
//    }
//
//    public String getDate_delivery() {
//        return date_delivery;
//    }
//}
import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class Order {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("customer_id")
    @Expose
    private String customerId;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("weight")
    @Expose
    private String weight;
    @SerializedName("date_create")
    @Expose
    private String dateCreate;
    @SerializedName("date_delivery")
    @Expose
    private String dateDelivery;
    @SerializedName("__v")
    @Expose
    private Integer version;

    public String getId() {
        return id;
    }
//
//    public void setId(String id) {
//        this.id = id;
//    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getDateDelivery() {
        return dateDelivery;
    }

    public void setDateDelivery(String dateDelivery) {
        this.dateDelivery = dateDelivery;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", customerId='" + customerId + '\'' +
                ", type='" + type + '\'' +
                ", weight='" + weight + '\'' +
                ", dateCreate='" + dateCreate + '\'' +
                ", dateDelivery='" + dateDelivery + '\'' +
                ", version=" + version +
                '}';
    }
}