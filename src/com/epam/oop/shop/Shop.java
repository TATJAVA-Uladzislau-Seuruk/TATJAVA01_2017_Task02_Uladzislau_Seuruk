package com.epam.oop.shop;

import com.epam.oop.customer.Customer;
import com.epam.oop.customer.CustomersBase;
import com.epam.oop.order.Order;
import com.epam.oop.order.OrdersBase;

import java.util.List;
import java.util.Map;

/**
 * Implements sport equipment rental shop.
 *
 * @author Uladzislau Seuruk.
 */
public class Shop {
    /**
     * Constant with number of items that user can hold simultaneously.
     */
    private static final int ALLOWED_AMOUNT = 3;
    /**
     * Database with customers.
     */
    private CustomersBase customersBase = new CustomersBase();
    /**
     * Archive with closed orders.
     */
    private OrdersBase archive = new OrdersBase();
    /**
     * Database with active orders.
     */
    private OrdersBase activeOrdersBase = new OrdersBase();
    /**
     * Storage with goods.
     */
    private Storage storage;

    public Shop() {
        storage = new Storage();
    }

    public Shop(Map<SportEquipment, Integer> goods) {
        storage = new Storage(goods);
    }

    /**
     * Adds amount of received items to storage.
     *
     * @param item item to add to storage.
     * @param amount amount of items to add.
     * @return this.
     */
    public Shop addItemToStorage(SportEquipment item, int amount) {
        storage.addItem(item, amount);
        return this;
    }

    /**
     * Places new order if requested amount does not exceed remained allowed amount and adds
     * customer to database if he isn't already there.
     *
     * @param customer <tt>Customer</tt> to place order for.
     * @param item requested <tt>SportEquipment</tt>.
     * @param amount requested amount of items.
     * @return resulting <tt>Order</tt> if received parameters were initialized and received amount
     * is positive and does not exceed remained allowed amount, <tt>null</tt> otherwise.
     */
    public Order addOrder(Customer customer, SportEquipment item, int amount) {
        if (customer == null || item == null) {
            System.out.println("At least one of parameters was not initialized.");
            return null;
        }
        if (amount <= 0 || amount > ALLOWED_AMOUNT) {
            System.out.println("Requested amount is not positive or exceeds allowed limit.");
            return null;
        }
        List<Order> userOrders = activeOrdersBase.getCustomerOrders(customer);
        if (userOrders != null) {
            int takenItems = 0;
            for (Order order : userOrders) {
                takenItems += order.getAmount();
            }
            if (takenItems + amount > ALLOWED_AMOUNT) {
                System.out.println("Requested amount exceeds remained allowed amount.");
                return null;
            }
        }
        if (!storage.takeItem(item, amount)) {
            return null;
        }
        Order newOrder = new Order(item, amount);
        activeOrdersBase.addOrder(customer, newOrder);
        if (customersBase.getCustomerById(customer.getId()) == null) {
            customersBase.addNewCustomer(customer);
        }
        return newOrder;
    }

    /**
     * Closes active order and moves it to archive.
     *
     * @param customer <tt>Customer</tt> to close order for.
     * @param order <tt>Order</tt> to close.
     * @return <tt>true</tt> if order was successfully closed, <tt>false</tt> otherwise.
     */
    public boolean closeOrder(Customer customer, Order order) {
        boolean succeed = activeOrdersBase.closeOrder(customer, order);
        if (succeed) {
            archive.addOrder(customer, order);
            addItemToStorage(order.getItem(), order.getAmount());
        }
        return succeed;
    }

    /**
     * Returns orders of requested customer.
     *
     * @param customer <tt>Customer</tt> who's orders are requested.
     * @return <tt>List</tt> with active orders of requested customer.
     */
    public List<Order> getCustomerActiveOrders(Customer customer) {
        return activeOrdersBase.getCustomerOrders(customer);
    }

    /**
     * Returns closed orders of requested customer.
     *
     * @param customer <tt>Customer</tt> who's orders are requested.
     * @return <tt>List</tt> with archive orders of requested customer.
     */
    public List<Order> getCustomerClosedOrders(Customer customer) {
        return archive.getCustomerOrders(customer);
    }

    /**
     * Returns <tt>List</tt> of all <tt>Customer</tt>s that ever used this shop.
     */
    public List<Customer> getCustomersList() {
        return customersBase.getCustomerList();
    }

    /**
     * Returns <tt>Map</tt> with all goods that are in stock.
     */
    public Map<SportEquipment, Integer> getGoodsInStock() {
        return storage.getGoods();
    }
}