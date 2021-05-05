package za.co.anycompany.documents;

import za.co.anycompany.model.Customer;
import za.co.anycompany.model.Order;

import java.util.ArrayList;
import java.util.List;

public class CustomerOutput  extends Customer {
    private List<Order> orders ;

    public List<Order> getOrders() {
        return null != orders ? new ArrayList<>(orders) : new ArrayList<>();
    }

    public void setOrders(List<Order> orders) {
        this.orders = null != orders ? new ArrayList<>(orders) : new ArrayList<>();
    }
}
