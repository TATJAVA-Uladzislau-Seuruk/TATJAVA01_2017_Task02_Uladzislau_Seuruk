package com.epam.oop.order;

import com.epam.oop.shop.SportEquipment;

/**
 * Implements order of sports equipment unit.
 *
 * @author Uladzislau Seuruk.
 */
public class Order {
    /**
     * Rented item.
     */
    private SportEquipment item;
    /**
     * Amount of items.
     */
    private int amount;

    public Order(SportEquipment item, int amount) {
        this.item = item;
        this.amount = amount;
    }

    /**
     * Getter.
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Getter.
     */
    public SportEquipment getItem() {
        return item;
    }
}