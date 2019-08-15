/*
 * @Chandra Dhakite
 * MenuRoomDatabase abstract class to build the database
 *
 */

package com.chandra.restaurants.domain.models;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


@Database(entities = {Menu.class}, version = 4, exportSchema = false)
public abstract class MenuRoomDatabase extends RoomDatabase {
    private static final String TAG = "MenuRoomDatabase";

    public abstract MenuDao restaurantMenuDao();

    private static MenuRoomDatabase INSTANCE;


    public static MenuRoomDatabase getDatabaseInstance(final Context mContext) {

        if (INSTANCE == null) {
            synchronized (MenuDao.class) {
                if (INSTANCE == null) {
//                    get database from Asset folder
//                    copyDatabase(mContext, "test_menu");
                    INSTANCE = Room.databaseBuilder(mContext.getApplicationContext(), MenuRoomDatabase.class, "test_menu")
                            .fallbackToDestructiveMigration().
                                    build();
                }
            }

        }
        return INSTANCE;
    }


    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    public static void copyDatabase(Context context, String databaseName) {
        final File dbPath = context.getDatabasePath(databaseName);

        // If the database already exists, return
        if (dbPath.exists()) {
            Log.d("Activity", "db Path Exists");
            return;
        }

        // Make sure we have a path to the file
        dbPath.getParentFile().mkdirs();

        // Try to copy database file
        try {
            final InputStream inputStream = context.getAssets().open(databaseName);
            final OutputStream output = new FileOutputStream(dbPath);

            byte[] buffer = new byte[8192];
            int length;

            while ((length = inputStream.read(buffer, 0, 8192)) > 0) {
                output.write(buffer, 0, length);
            }

            output.flush();
            output.close();
            inputStream.close();
        } catch (IOException e) {
            Log.d("Activity", "Failed to open file", e);
            e.printStackTrace();
        }
    }
}
