/*
 * @Chandra Dhakite
 * MenuListActivity class which build the list and add Recycler view to show the list of menus
 * - Also able to search the menu as per the buisness case
 * - Add menu button will help to add new menu that can be available for (Admin), however just added the functionality for testing
 */

package com.chandra.restaurants.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chandra.restaurants.R;
import com.chandra.restaurants.adapter.RecycleViewAdapter;
import com.chandra.restaurants.domain.models.Menu;
import com.chandra.restaurants.domain.models.MenuRoomDatabase;
import com.chandra.restaurants.utils.Constant;
import com.chandra.restaurants.viewmodel.MenuViewModel;

import java.util.List;

public class MenuListActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    // Declare Variables
    private ListView list;
    private Button search_button;
    private RecycleViewAdapter recycleViewAdapter;
    private SearchView editsearch;
    private LinearLayout searchParent;
    private String[] animalNameList;
    private final String TAG = MenuListActivity.class.getSimpleName();
    private String searchText;
    private MenuViewModel menuViewModel;
    private Button addButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.listview);
        addButton = findViewById(R.id.add_menu_btn);
        // Pass results to ListViewAdapter Class
        recycleViewAdapter = new RecycleViewAdapter(this);
        menuViewModel = ViewModelProviders.of(this).get(MenuViewModel.class);
        menuViewModel.getMenus().observe(this, new Observer<List<Menu>>() {
            @Override
            public void onChanged(List<Menu> menus) {

                recycleViewAdapter.setSearchArray(menus);
            }
        });

        recyclerView.setAdapter(recycleViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        // Locate the EditText in listview_main.xml
        editsearch = findViewById(R.id.search);
        editsearch.setOnQueryTextListener(this);
        search_button = findViewById(R.id.btn_search);
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: search_button ");
                recycleViewAdapter.filter(searchText);
                searchText = "";
            }
        });
    }


    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        Log.d(TAG, "Called" + newText);
        String text = newText;
        if (text.length() == 2)
            searchText = newText;
        if (text.length() == Constant.SEARCH_TEXT_SIZE || text.length() == Constant.CLEAR_TEXT_SIZE)
            recycleViewAdapter.filter(text);
        return false;
    }

    public void addButtonClicked(View view) {
        startActivity(new Intent(MenuListActivity.this, AddMenuActivity.class));
    }

    @Override
    protected void onStart() {
        recycleViewAdapter.notifyDataSetChanged();
        super.onStart();
    }
}