package com.romellfudi.fudi_annotation.sample;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.romellfudi.fudi_annotation.Bind;
import com.romellfudi.fudi_annotation.ItemWidget;
import com.romellfudi.fudi_annotation.NavigationWidget;
import com.romellfudi.fudi_annotation.Res;


/**
 * @author Romell Dominguez
 * @version 1.0.a 01/09/2015
 * @since 1.0
 */
public class MainMenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @ItemWidget
    Toolbar toolbar;
    @ItemWidget
    DrawerLayout drawer;
    @ItemWidget
    @NavigationWidget(idDrawer = R.id.drawer, idToolBar = R.id.toolbar,
            idMenu = R.menu.activity_main_menu_drawer,
            openString = R.string.navigation_drawer_open, closeString = R.string.navigation_drawer_close)
    NavigationView navigationView;

    @Res
    String infoString;
    @Res
    int num;
    @Res
    boolean normal;
    @Res
    Drawable configuracion;
    @Res
    String[] names;
    @Res
    int[] nums;
    @Res(className = Color.class)
    int orangeColor;
    @Res(className = Color[].class)
    int[] colors;
    @Res
    Drawable[] icons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Bind.Plug(this);
        setSupportActionBar(toolbar);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_layout, new MainFragment()); // f1_container is your FrameLayout container
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.addToBackStack(null);
        ft.commit();
        getSupportActionBar().setTitle(infoString);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {

        int id = menuItem.getItemId();
        Fragment newFragment = null;
        String tittle = null;

        //
        newFragment = new MainFragment();
        tittle = getString(R.string.app_name);

        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        getSupportActionBar().setTitle(tittle);
        ft.replace(R.id.fragment_layout, newFragment); // f1_container is your FrameLayout container
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.addToBackStack(null);
        ft.commit();

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
}
