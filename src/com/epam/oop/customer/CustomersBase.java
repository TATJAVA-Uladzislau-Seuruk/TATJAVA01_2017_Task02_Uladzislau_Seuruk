package com.epam.oop.customer;

import java.util.LinkedList;
import java.util.List;

/**
 * Implements base with shop's customers.
 *
 * @author Uladzislau Seuruk.
 */
public class CustomersBase {
    /**
     * List of registered customers.
     */
    private List<Customer> customerList;

    public CustomersBase() {
        this(null);
    }

    /**
     * If received <tt>List</tt> of customers was not initialized, initializes internal field with
     * new <tt>LinkedList</tt>.
     *
     * @param customers <tt>List</tt> of customers.
     */
    public CustomersBase(List<Customer> customers) {
        if (customers == null) {
            this.customerList = new LinkedList<>();
        } else {
            this.customerList = customers;
        }
    }

    /**
     * Adds received <tt>Customer</tt> to list if it is not already there.
     *
     * @param newCustomer <tt>Customer</tt> to add.
     * @return <tt>true</tt> if user was successfully added, <tt>false</tt> otherwise.
     */
    public boolean addNewCustomer(Customer newCustomer) {
        if (newCustomer == null) {
            return false;
        }
        if (customerList.size() != 0) {
            if (customerList.get(customerList.size() - 1).getId() >= newCustomer.getId()) {
                System.out.println("User with received id was already registered.");
                return false;
            }
        }
        customerList.add(newCustomer);
        return true;
    }

    /**
     * Adds new user with received name to list.
     *
     * @param name name of user to add.
     * @return <tt>true</tt> if user was successfully added, <tt>false</tt> otherwise.
     */
    public boolean addNewCustomer(String name) {
        if (name == null) {
            return false;
        }
        customerList.add(new Customer(name));
        return true;
    }

    /**
     * Returns <tt>Customer</tt> with requested id.
     *
     * @param id id of requested user.
     * @return <tt>Customer</tt> with requested id or <tt>null</tt> if there is no such
     * <tt>Customer</tt>.
     */
    public Customer getCustomerById(long id) {
        for (Customer customer : customerList) {
            if (customer.getId() == id) {
                return customer;
            }
        }
        return null;
    }

    /**
     * Returns <tt>Customer</tt> with requested name.
     *
     * @param name name of requested user.
     * @return first found <tt>Customer</tt> with requested name or <tt>null</tt> if there is no
     * such <tt>Customer</tt>.
     */
    public Customer getCustomerByName(String name) {
        if (name == null) {
            return null;
        }
        for (Customer customer : customerList) {
            if (name.equals(customer.getName())) {
                return customer;
            }
        }
        return null;
    }

    /**
     * Getter.
     */
    public List<Customer> getCustomerList() {
        return customerList;
    }
}