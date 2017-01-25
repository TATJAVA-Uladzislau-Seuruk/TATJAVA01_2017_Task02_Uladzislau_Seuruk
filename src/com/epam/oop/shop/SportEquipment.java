package com.epam.oop.shop;

/**
 * Carries information about item of sports equipment.
 *
 * @author Uladzislau Seuruk.
 */
public class SportEquipment {
    /**
     * Category that item relates to.
     */
    private String category;
    /**
     * Title of item.
     */
    private String title;
    /**
     * Price of item.
     */
    private int price;

    /**
     * If received price is less than zero sets it to zero. Throws <tt>Exception</tt> if title of
     * item wasn't initialized.
     *
     * @param category category item relates to.
     * @param title title of item.
     * @param price price of item.
     */
    public SportEquipment(String category, String title, int price) {
        this.category = category;
        this.title = title;
        if (price < 0) {
            this.price = 0;
        } else {
            this.price = price;
        }
    }

    /**
     * Getter.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Getter.
     */
    public int getPrice() {
        return price;
    }

    /**
     * Getter.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter. If received value is less than zero doesn't change anything.
     */
    public void setPrice(int price) {
        if (price >= 0) {
            this.price = price;
        }
    }
}