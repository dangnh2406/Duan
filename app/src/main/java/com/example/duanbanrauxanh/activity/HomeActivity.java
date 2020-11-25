package com.example.duanbanrauxanh.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.duanbanrauxanh.R;
import com.example.duanbanrauxanh.frangment.BillFragment;
import com.example.duanbanrauxanh.frangment.ShoppingFragment;
import com.example.duanbanrauxanh.frangment.AddProductFragment;
import com.example.duanbanrauxanh.frangment.ProfileFragment;
import com.example.duanbanrauxanh.frangment.SupemarketFragment;
import com.example.duanbanrauxanh.model.Product;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    ChipNavigationBar chipNavigationBar;
    Fragment fragment = null;
    public List<Product> productList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        chipNavigationBar = findViewById(R.id.chipNavigation);
        chipNavigationBar.setItemSelected(R.id.market, true);
        getSupportFragmentManager().beginTransaction().replace(R.id.action_hContainer, new SupemarketFragment()).commit();
        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                switch (i) {
                    case R.id.market:
                        fragment = new SupemarketFragment();
                        break;
                    case R.id.shopping:
                        fragment = new ShoppingFragment();
                        break;
                    case R.id.setting:
                        fragment = new ProfileFragment();
                        break;
                    case R.id.bill:
                        fragment = new BillFragment();
                        break;
                    case R.id.aaa:
                        fragment = new AddProductFragment();
                }
                if (fragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.action_hContainer, fragment).commit();
                }

            }
        });
    }
}