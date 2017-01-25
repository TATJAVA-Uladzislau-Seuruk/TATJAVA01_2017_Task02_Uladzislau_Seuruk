package com.epam.oop;

import com.epam.oop.customer.Customer;
import com.epam.oop.order.Order;
import com.epam.oop.shop.Shop;
import com.epam.oop.shop.SportEquipment;

import java.util.*;

/**
 * @author Uladzislau Seuruk.
 */
public class Main {
    public static void main(String[] args) {
        ItemsParser reader = new ItemsParser(null);
        HashMap<SportEquipment, Integer> goods = reader.getItems();
        Shop shop = new Shop(goods);
        printInformation(shop);
        List<Customer> customers = generateCustomers();
        emulatesOrders(shop, customers);
        emulateItemsReturn(shop, customers);
        printInformation(shop);
    }

    /**
     * Generates some shop's customers.
     */
    private static List<Customer> generateCustomers() {
        List<Customer> customers = new ArrayList<>(4);
        customers.add(new Customer("Tommy"));
        customers.add(new Customer("John"));
        customers.add(new Customer("Jack"));
        customers.add(new Customer("Clark"));
        return customers;
    }

    /**
     * Emulates some consumer's order's closing.
     */
    private static void emulateItemsReturn(Shop shop, List<Customer> customerList) {
        System.out.println("---------------------------\n"
                         + "Returning items\n"
                         + "---------------------------");
        System.out.println(customerList.get(0).getName() + ":");
        List<Order> orderList = shop.getCustomerActiveOrders(customerList.get(0));
        if (orderList != null && orderList.size() != 0) {
            Order order = orderList.get(0);
            makeReturn(shop, customerList.get(0), order);
            makeReturn(shop, customerList.get(0), order);
        }
        System.out.println();

        System.out.println(customerList.get(1).getName() + ":");
        orderList = shop.getCustomerActiveOrders(customerList.get(1));
        if (orderList != null && orderList.size() != 0) {
            for (Order order : orderList) {
                makeReturn(shop, customerList.get(1), order);
            }
        }
        orderList = shop.getCustomerActiveOrders(customerList.get(2));
        if (orderList != null && orderList.size() != 0) {
            Order order = orderList.get(0);
            makeReturn(shop, customerList.get(1), order);
            makeReturn(shop, customerList.get(1), order);
        }
        System.out.println();
    }

    /**
     * Emulates some customer's orders.
     */
    private static List<Order> emulatesOrders(Shop shop, List<Customer> customerList) {
        System.out.println("---------------------------\n"
                         + "Making orders\n"
                         + "---------------------------");
        List<Order> orders = new ArrayList<>(12);
        Set<SportEquipment> itemSet = shop.getGoodsInStock().keySet();
        SportEquipment[] items = new SportEquipment[itemSet.size()];
        itemSet.toArray(items);
        for (Customer customer : customerList) {
            System.out.println(customer.getName() + ":");
            for (int i = 0; i < items.length; ++i) {
                orders.add(makeOrder(shop, customer, items[i], i + 1));
            }
            System.out.println();
        }
        return orders;
    }

    /**
     * Emulates situation when consumer tries to make an order.
     */
    private static Order makeOrder(Shop shop, Customer customer, SportEquipment item, int amount) {
        Order order = shop.addOrder(customer, item, amount);
        if (order != null) {
            System.out.println(
                    (order.getItem().getCategory() == null ? "" : order.getItem().getCategory() + " ")
                            + order.getItem().getTitle() + ": " + order.getAmount()
                            + " item(s) - ordered successfully.");
        }
        return order;
    }

    /**
     * Emulates situation when consumer tries to close an order.
     */
    private static boolean makeReturn(Shop shop, Customer customer, Order order) {
        boolean succeed = shop.closeOrder(customer, order);
        if (succeed) {
            System.out.println(
                    (order.getItem().getCategory() == null ? "" : order.getItem().getCategory() + " ")
                            + order.getItem().getTitle()+ ": " + order.getAmount()
                            + " item(s) - returned successfully.");
        }
        return succeed;
    }

    /**
     * Prints information about active orders and goods at stock to console.
     */
    private static void printInformation(Shop shop) {
        System.out.println("---------------------------\n"
                         + "Debts and goods information\n"
                         + "---------------------------");
        printOrdersInformation(shop);
        printLeftGoodsInformation(shop);
    }

    /**
     * Prints information about goods that are at stock of received shop to console.
     */
    private static void printLeftGoodsInformation(Shop shop) {
        System.out.println("In stock:");
        for (Map.Entry<SportEquipment, Integer> entry : shop.getGoodsInStock().entrySet()) {
            printItemInformation(entry.getKey(), entry.getValue());
        }
        System.out.println();
    }

    /**
     * Prints information about all active orders of received shop to console.
     */
    private static void printOrdersInformation(Shop shop) {
        for (Customer customer : shop.getCustomersList()) {
            System.out.println(customer.getName() + ":");
            List<Order> orderList = shop.getCustomerActiveOrders(customer);
            for (Order order : orderList) {
                printItemInformation(order.getItem(), order.getAmount());
            }
            System.out.println();
        }
    }

    /**
     * Prints information about one item to console.
     */
    private static void printItemInformation(SportEquipment item, int amount) {
        System.out.println((item.getCategory() == null ? "" : item.getCategory() + " ")
                + item.getTitle() + " (cost " + item.getPrice() + ") - " + amount + " item(s).");
    }
}