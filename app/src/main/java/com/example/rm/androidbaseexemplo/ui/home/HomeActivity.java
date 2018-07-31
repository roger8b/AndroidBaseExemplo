package com.example.rm.androidbaseexemplo.ui.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;

import com.blankj.utilcode.util.Utils;
import com.example.rm.androidbaseexemplo.R;
import com.example.rm.androidbaseexemplo.databinding.ActivityHomeBinding;
import com.example.rm.androidbaseexemplo.ui.BaseActivity;
import com.example.rm.androidbaseexemplo.ui.home.loremipsumlist.MockItemList;
import com.example.rm.androidbaseexemplo.ui.home.sidemenu.SideMenuItem;
import com.example.rm.androidbaseexemplo.ui.home.sidemenu.SideMenuListItens;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class HomeActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ActivityHomeBinding mBinding;
    DrawerLayout mDrawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_home);

        Utils.init(getApplicationContext());

        loadSideMenu();

        //loadMockRecyclerView();

        loadBottonMenu();

        loadFragment();

    }

    private void loadFragment() {
        Fragment fragment = HomeFragment.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container,fragment, String.valueOf(R.layout.fragment_home)).commit();

    }

    private void loadBottonMenu() {






    }

    private void loadMockRecyclerView() {

       /* MockItemList mockItemList = new MockItemList(getApplicationContext());
        mBinding.inContentHome.rvMockData.setLayoutManager(new LinearLayoutManager(this));
        MockListAdapter mockListAdapter = new MockListAdapter();
        mockListAdapter.setData(mockItemList.getMockListItems());
        mBinding.inContentHome.rvMockData.setAdapter(mockListAdapter);*/

    }

    private void loadSideMenu() {

        mDrawerLayout = mBinding.sideMenuLayout;
        navigationView = mBinding.navSideMenu;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        mBinding.navSideMenu.bringToFront();
        mBinding.sideMenuLayout.requestLayout();

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
                        SideMenuItem sideMenuItem = sideMenuItemIntegerPair.first;
                        sideMenuCliked(sideMenuItem.getId());

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

    private void sideMenuCliked(int id) {
        String message = "Item selecionado: " + id;
        Snackbar.make(mDrawerLayout,message,Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
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
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
