package com.example.nouraalrossiny.androidbottomnav;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.nouraalrossiny.androidbottomnav.Account.AccoutFragment;
import com.example.nouraalrossiny.androidbottomnav.Cart.CartFragment;
import com.example.nouraalrossiny.androidbottomnav.Category.CategoryFragment;
import com.example.nouraalrossiny.androidbottomnav.Home.HomeFragment;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private static final String TAG = "MainActivity";

    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;
    private HomeFragment homeFragment;
    private CategoryFragment categoryFragment;
    private AccoutFragment accoutFragment;
    private CartFragment chartFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainFrame =findViewById(R.id.main_frame);
        mMainNav = findViewById(R.id.main_nav);
        homeFragment = new HomeFragment();
        categoryFragment = new CategoryFragment();
        accoutFragment = new AccoutFragment();
        chartFragment = new CartFragment();
        setFragment(homeFragment);

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        setFragment(homeFragment);
                        return true;
                    case R.id.nav_Category:
                        setFragment(categoryFragment);
                        return true;
                    case R.id.nav_account:
                        setFragment(accoutFragment);
                        return true;
                    case R.id.nav_cart:
                        setFragment(chartFragment);
                        return true;
                    default:
                        return false;
                }
            }
        });
    }
    //end of onCreate

    private void setFragment(android.support.v4.app.Fragment fragment) {
        android.support.v4.app.FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame,fragment);
        fragmentTransaction.commit();
    }
}