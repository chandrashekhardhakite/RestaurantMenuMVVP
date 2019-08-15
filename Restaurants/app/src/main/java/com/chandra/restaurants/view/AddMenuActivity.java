/*
 * @Chandra Dhakite
 * AddMenuActivity Activity class to add new menu in DB/menu list
 */

package com.chandra.restaurants.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.chandra.restaurants.R;
import com.chandra.restaurants.domain.models.Menu;
import com.chandra.restaurants.viewmodel.MenuViewModel;

public class AddMenuActivity extends AppCompatActivity {

    private static final String TAG = "AddMenuActivity";

    private EditText menuName;
    private EditText menuPrice;
    private CheckBox menuIsAvailable;
    private Button addmenu;
    private double price;
    private TextView errorMsg;
    private MenuViewModel menuViewModel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_menu);
        menuName = findViewById(R.id.menuName);
        menuPrice = findViewById(R.id.menuPrice);
        menuIsAvailable = findViewById(R.id.menuIsAvailable);
        addmenu = findViewById(R.id.addMenuBtn);
        errorMsg = findViewById(R.id.errorMsg);
        menuViewModel = ViewModelProviders.of(this).get(MenuViewModel.class);
        addmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!menuPrice.getText().toString().isEmpty() && !menuName.getText().toString().isEmpty()) {
                    price = Double.parseDouble(menuPrice.getText().toString());
                    Menu menu = new Menu(menuName.getText().toString(), price, menuIsAvailable.isChecked());
                    menuViewModel.insert(menu);
                    finish();
                } else {
                    errorMsg.setText(R.string.error_msg);
                }
            }
        });
    }


}
