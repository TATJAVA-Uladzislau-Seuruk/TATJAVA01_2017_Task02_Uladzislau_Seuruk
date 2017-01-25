package com.epam.oop.customer;

/**
 * Carries shop's customer information.
 *
 * @author Uladzislau Seuruk.
 */
public class Customer {
    /**
     * Count of registered users.
     */
    private static long userCount = 0;
    /**
     * Name of the user.
     */
    private String name;
    /**
     * Unique id of the user.
     */
    private long id;

    /**
     * Throws <tt>Exception</tt> if received parameter was not initialized.
     *
     * @param name name of the user.
     */
    public Customer(String name) {
        this.name = name;
        id = ++userCount;
    }

    /**
     * Returns unique id of the user.
     */
    public long getId() {
        return id;
    }

    /**
     * Returns name of the user.
     */
    public String getName() {
        return name;
    }
}