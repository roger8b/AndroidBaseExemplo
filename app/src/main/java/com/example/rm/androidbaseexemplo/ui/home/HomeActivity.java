package com.example.rm.androidbaseexemplo.ui.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;

import com.example.rm.androidbaseexemplo.R;
import com.example.rm.androidbaseexemplo.databinding.ActivityHomeBinding;
import com.example.rm.androidbaseexemplo.ui.home.sidemenu.SideMenuItem;
import com.example.rm.androidbaseexemplo.ui.home.sidemenu.SideMenuListItens;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ActivityHomeBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_home);

        loadSideMenu();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.side_menu_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_side_menu);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void loadSideMenu() {
        SideMenuListItens sideMenuListItens = new SideMenuListItens();
        mBinding.rvSideMenu.setLayoutManager(new LinearLayoutManager(this));
        SideMenuAdapter  sideMenuAdapter = new SideMenuAdapter();
        sideMenuAdapter.setData(sideMenuListItens.getSideMenuList());
        sideMenuAdapter.observableItemPairClick()
                .subscribe(new Observer<Pair<SideMenuItem, Integer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Pair<SideMenuItem, Integer> sideMenuItemIntegerPair) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        mBinding.rvSideMenu.setAdapter(sideMenuAdapter);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.side_menu_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.side_menu_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
