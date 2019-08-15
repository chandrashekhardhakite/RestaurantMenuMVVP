package com.chandra.restaurants.viewmodel;


import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.chandra.restaurants.domain.models.Menu;
import com.chandra.restaurants.repository.MenuRepository;

import java.util.List;

public class MenuViewModel extends AndroidViewModel {

    private MenuRepository menuRepository;
    private LiveData<List<Menu>> mAllMenu;

    public MenuViewModel(Application application) {
        super(application);
        menuRepository = new MenuRepository(application);
        mAllMenu = menuRepository.getAllMenu();
    }

    public LiveData<List<Menu>> getMenus() {
        return mAllMenu;
    }

    public void deleteAll(){
        menuRepository.deleteAll();
    }

    public void insert(Menu menu) {
        menuRepository.insert(menu);
    }


}
