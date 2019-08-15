/*
 * @Chandra Dhakite
 * InsertAsyncTask class will add the one by one menu in db
 */

package com.chandra.restaurants.repository;

import android.os.AsyncTask;

import com.chandra.restaurants.domain.models.Menu;
import com.chandra.restaurants.domain.models.MenuDao;

public class InsertAsyncTask extends AsyncTask<Menu, Void, Void> {

    private MenuDao asyncTaskDao;

    private MenuDao mAsyncTaskDao;

    InsertAsyncTask(MenuDao dao) {
        mAsyncTaskDao = dao;
    }

    @Override
    protected Void doInBackground(final Menu... params) {
        mAsyncTaskDao.insertMenu(params[0]);
        return null;
    }
}