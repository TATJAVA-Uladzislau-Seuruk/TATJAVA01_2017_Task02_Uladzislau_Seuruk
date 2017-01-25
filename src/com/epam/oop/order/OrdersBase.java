package com.epam.oop.order;

import com.epam.oop.customer.Customer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Implements orders database.
 *
 * @author Uladzislau Seuruk.
 */
public class OrdersBase {
    /**
     * Map with records about ordersList.
     */
    protected Map<Customer, List<Order> > ordersList;

    public OrdersBase() {
        this(null);
    }

    /**
     * If received <tt>Map</tt> of orders wasn't initialized, initializes internal field with new
     * <tt>HashMap</tt>.
     *
     * @param orders <tt>Map</tt> with active orders.
     */
    public OrdersBase(Map<Customer, List<Order> > orders) {
        if (orders == null) {
            this.ordersList = new HashMap<>();
        } else {
            this.ordersList = orders;
        }
    }

    /**
     * Adds new order to <tt>Map</tt>.
     *
     * @param customer <tt>Customer</tt> to place an order for.
     * @param order <tt>Order</tt> to proceed.
     * @return <tt>true</tt> if order was successfully proceeded, <tt>false</tt> otherwise.
     */
    public boolean addOrder(Customer customer, Order order) {
        if (customer == null) {
            System.out.println("User wasn't initialized.");
            return false;
        }
        if (order == null) {
            System.out.println("Order wasn't initialized.");
            return false;
        }
        List<Order> userOrders = ordersList.get(customer);
        if (userOrders == null) {
            userOrders = new LinkedList<>();
        }
        userOrders.add(order);
        ordersList.put(customer, userOrders);
        return true;
    }

    /**
     * Removes requested order from <tt>Map</tt> of orders.
     *
     * @param customer <tt>Customer</tt> who's order must be closed.
     * @param order <tt>Order</tt> to close.
     * @return <tt>true</tt> if order was successfully closed, <tt>false</tt> otherwise.
     */
    public boolean closeOrder(Customer customer, Order order) {
        if (customer == null) {
            System.out.println("User wasn't initialized.");
            return false;
        }
        if (order == null) {
            System.out.println("Order wasn't initialized.");
            return false;
        }
        List<Order> userOrders = ordersList.get(customer);
        if (userOrders == null || userOrders.indexOf(order) == -1) {
            System.out.println("User doesn't have such order.");
            return false;
        }
        userOrders.remove(order);
        ordersList.put(customer, userOrders);
        return true;
    }

    /**
     * Returns orders of requested customer.
     *
     * @param customer <tt>Customer</tt> who's orders are requested.
     * @return <tt>List</tt> with active orders of requested customer.
     */
    public List<Order> getCustomerOrders(Customer customer) {
        if (customer == null) {
            return null;
        }
        return ordersList.get(customer);
    }
}