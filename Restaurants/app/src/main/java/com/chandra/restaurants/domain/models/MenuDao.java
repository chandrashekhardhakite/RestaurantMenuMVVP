/*
 * @Chandra Dhakite
 * MenuDao Database interface
 */

package com.chandra.restaurants.domain.models;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MenuDao {

    @Insert
    void insertMenu(Menu menu);

    @Query("SELECT * FROM MENU_ITEMS")
    LiveData<List<Menu>> getAllMenu();


    @Query("DELETE FROM MENU_ITEMS")
    void deleteAll();


}
