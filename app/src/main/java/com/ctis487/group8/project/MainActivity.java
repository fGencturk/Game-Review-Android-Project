package com.ctis487.group8.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.*;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNav;

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    item.setChecked(true);
                    Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.nav_games:
                            selectedFragment = new GamesFragment();
                            //changeMenuItemCheckedStateColor(bottomNav, "#FFFFFF", "#303550");
                            break;
                        case R.id.nav_profile:
                            selectedFragment = new ProfileFragment();
                            //changeMenuItemCheckedStateColor(bottomNav, "#FFFFFF", "#303550");
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();
                    return true;
                }
            };
    private DatabaseHelper dbHelper;

    private void changeMenuItemCheckedStateColor(BottomNavigationView bottomNavigationView, String checkedColorHex, String uncheckedColorHex) {
        int checkedColor = Color.parseColor(checkedColorHex);
        int uncheckedColor = Color.parseColor(uncheckedColorHex);

        int[][] states = new int[][] {
                new int[] {-android.R.attr.state_checked}, // unchecked
                new int[] {android.R.attr.state_checked}, // checked

        };

        int[] colors = new int[] {
                uncheckedColor,
                checkedColor
        };

        ColorStateList colorStateList = new ColorStateList(states, colors);

        bottomNavigationView.setItemTextColor(colorStateList);
        bottomNavigationView.setItemIconTintList(colorStateList);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DatabaseHelper(this);
        startService(new Intent(this,MusicPlayerService.class));


        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new GamesFragment()).commit();
            changeMenuItemCheckedStateColor(bottomNav, "#FFFFFF", "#303550");
        }


        findViewById(R.id.main_container).setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {
            public void onSwipeRight() {
                bottomNav.getMenu().performIdentifierAction(R.id.nav_games, 0);
            }
            public void onSwipeLeft() {
                bottomNav.getMenu().performIdentifierAction(R.id.nav_profile, 0);
            }
        });
    }


    @Override
    protected void onDestroy() {
        stopService(new Intent(this,MusicPlayerService.class));
        super.onDestroy();
    }
}