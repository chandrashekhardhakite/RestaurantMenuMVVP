/*
 * @Chandra Dhakite
 * Menu is POJO class
 */

package com.chandra.restaurants.domain.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "menu_items")
public class Menu {

    private static final String TAG = "Menu";

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "menu_item_id")
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "price")
    private double price;

    @ColumnInfo(name = "is_available")
    private boolean is_available;

    public Menu( String name, double price, boolean is_available) {
        this.name = name;
        this.price = price;
        this.is_available = is_available;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean getIs_available() {
        return is_available;
    }

    public void setIs_available(boolean is_available) {
        this.is_available = is_available;
    }
}
