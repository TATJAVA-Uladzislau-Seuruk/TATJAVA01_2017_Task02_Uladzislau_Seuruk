package com.epam.oop.shop;

import java.util.HashMap;
import java.util.Map;

/**
 * Implements storage with goods in stock.
 *
 * @author Uladzislau Seuruk.
 */
public class Storage {
    /**
     * <tt>Map</tt> with info about amount of goods at storage.
     */
    private Map<SportEquipment, Integer> goods;

    public Storage() {
        goods = new HashMap<>();
    }

    /**
     * If received <tt>Map</tt> is <tt>null</tt>, initializes internal field with new <tt>HashMap</tt>.
     *
     * @param goods <tt>Map</tt> with info about amount of goods at storage.
     */
    public Storage(Map<SportEquipment, Integer> goods) {
        if (goods == null) {
            this.goods = new HashMap<>();
        } else {
            this.goods = goods;
        }
    }

    /**
     * Adds amount of received items to storage. If received <tt>SportEquipment</tt> was not
     * initialized or requested amount is less or equals to zero, does not add anything.
     *
     * @param item item to add to storage.
     * @param amount amount of items to add.
     * @return <tt>true</tt> if item was successfully added, <tt>false</tt> otherwise.
     */
    public boolean addItem(SportEquipment item, int amount) {
        if (item == null) {
            System.out.println("Requested item was not initialized.");
            return false;
        }
        if (amount <= 0) {
            System.out.println("Requested amount of items is less or equals to zero.");
            return false;
        }
        if (goods.get(item) == null) {
            goods.put(item, amount);
        } else {
            goods.put(item, goods.get(item) + amount);
        }
        return true;
    }

    /**
     * Getter.
     */
    public Map<SportEquipment, Integer> getGoods() {
        return goods;
    }

    /**
     * Reduces amount of requested items at storage on requested value.
     *
     * @param item <tt>SportEquipment</tt> to take from storage.
     * @param amount amount of items to take from storage.
     * @return <tt>true</tt> if item was successfully taken, <tt>false</tt> otherwise.
     */
    public boolean takeItem(SportEquipment item, int amount) {
        if (item == null) {
            System.out.println("Requested item was not initialized.");
            return false;
        }
        if (amount <= 0) {
            System.out.println("Requested amount of items is less or equals to zero.");
            return false;
        }
        Integer amountInStock = goods.get(item);
        if (amountInStock == null || amountInStock < amount) {
            System.out.println("There is no enough requested items at storage.");
            return false;
        }
        goods.put(item, amountInStock - amount);
        return true;
    }
}