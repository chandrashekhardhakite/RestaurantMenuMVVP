/*
 * @Chandra Dhakite
 * MenuRepository (Repository) is a class that abstracts access to multiple data sources.
 * The Repository is not part of the Architecture Components libraries, but is a suggested best practice for
 * code separation and architecture. A Repository class handles data operations. It provides a clean API to the rest of the app for app data.
 * This class should be get connect to the Server or Db to pull data
 */

package com.chandra.restaurants.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.chandra.restaurants.domain.models.Menu;
import com.chandra.restaurants.domain.models.MenuDao;
import com.chandra.restaurants.domain.models.MenuRoomDatabase;

import java.util.List;

public class MenuRepository {

    private MenuDao menuDao;
    private LiveData<List<Menu>> mAllMenu;
    private static MenuRepository INSTANCE;

    public MenuRepository(Application application) {
        MenuRoomDatabase db;
        db = MenuRoomDatabase.getDatabaseInstance(application);
        menuDao = db.restaurantMenuDao();
        mAllMenu = menuDao.getAllMenu();
    }

    public LiveData<List<Menu>> getAllMenu() {
        return mAllMenu;
    }

    public void deleteAll() {
        new DeleteAsyncTask(menuDao).execute();
    }

    public void insert(Menu menu) {
        //Insert the data to the DB
        new InsertAsyncTask(menuDao).execute(menu);
    }
}