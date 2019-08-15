/*
 * @Chandra Dhakite
 * DeleteAsyncTask class will delete all the menus entries from the DB
 */

package com.chandra.restaurants.repository;

import android.os.AsyncTask;

import com.chandra.restaurants.domain.models.Menu;
import com.chandra.restaurants.domain.models.MenuDao;

public class DeleteAsyncTask extends AsyncTask<Menu, Void, Void> {

    private MenuDao asyncTaskDao;

    private MenuDao mAsyncTaskDao;

    DeleteAsyncTask(MenuDao dao) {
        mAsyncTaskDao = dao;
    }

    @Override
    protected Void doInBackground(final Menu... params) {
        mAsyncTaskDao.deleteAll();
        return null;
    }
}