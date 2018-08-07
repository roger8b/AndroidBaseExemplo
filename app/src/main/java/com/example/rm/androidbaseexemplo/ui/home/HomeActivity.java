package com.example.rm.androidbaseexemplo.ui.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;

import com.blankj.utilcode.util.Utils;
import com.example.rm.androidbaseexemplo.R;
import com.example.rm.androidbaseexemplo.databinding.ActivityHomeBinding;
import com.example.rm.androidbaseexemplo.ui.BaseActivity;
import com.example.rm.androidbaseexemplo.ui.home.bottonmenu.BottomNavigationViewHelper;
import com.example.rm.androidbaseexemplo.ui.home.homefragment.HomeFragment;
import com.example.rm.androidbaseexemplo.ui.home.sidemenu.SideMenuAdapter;
import com.example.rm.androidbaseexemplo.ui.home.sidemenu.SideMenuItem;
import com.example.rm.androidbaseexemplo.ui.home.sidemenu.SideMenuListItens;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class HomeActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String HOME_FRAGMENT = "HOME_FRAGMENT";
    private static final String ROBOT_TWO_FRAGMENT = "ROBOT_TWO_FRAGMENT";
    private ActivityHomeBinding mBinding;
    private DrawerLayout mDrawerLayout;
    private Fragment mCurrentFragment;
    private FragmentManager mFragmentManager;
    private Toolbar mToolbar;

    private static final long MOVE_DEFAULT_TIME = 300;
    private static final long FADE_DEFAULT_TIME = 300;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        Utils.init(getApplicationContext());

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        mFragmentManager = getSupportFragmentManager();

        loadSideMenu();

        //loadMockRecyclerView();

        loadBottonMenu();

        loadFragment(HOME_FRAGMENT, HomeFragment.newInstance(), R.string.home, null);


    }

    private void loadBottonMenu() {

        BottomNavigationViewHelper.removeShiftMode(mBinding.inContentHome.bottomNavigation);

        BottomNavigationView bottomNavigationView = mBinding.inContentHome.bottomNavigation;



        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.robot_1:
                    loadFragment(HOME_FRAGMENT, HomeFragment.newInstance(), R.string.home, mCurrentFragment);
                    break;

                case R.id.robot_2:

                    loadFragment(ROBOT_TWO_FRAGMENT, RobotTwoFragment.newInstance(), R.string.robo_2, mCurrentFragment);
                    break;

                case R.id.robot_3:
                    break;

                case R.id.robot_4:
                    break;
            }
            return true;
        });

    }

    private void loadFragment(String fragmentTag, Fragment nextFragment, int title, Fragment currentFragment) {

        if (nextFragment != null) {
            mToolbar.setTitle(title);

            if (currentFragment != null) {
                Fade exitFade = new Fade();
                exitFade.setDuration(FADE_DEFAULT_TIME);
                currentFragment.setExitTransition(exitFade);
            }

            Fade enterFade = new Fade();
            enterFade.setStartDelay(MOVE_DEFAULT_TIME + FADE_DEFAULT_TIME);
            enterFade.setDuration(FADE_DEFAULT_TIME);
            nextFragment.setEnterTransition(enterFade);

            mFragmentManager.beginTransaction().replace(R.id.container, nextFragment, fragmentTag).commit();

            mCurrentFragment = nextFragment;


        }

    }


    private void loadSideMenu() {

        mDrawerLayout = mBinding.sideMenuLayout;
        NavigationView navigationView = mBinding.navSideMenu;

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
        SideMenuAdapter sideMenuAdapter = new SideMenuAdapter();
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
        Snackbar.make(mDrawerLayout, message, Snackbar.LENGTH_SHORT).show();
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
