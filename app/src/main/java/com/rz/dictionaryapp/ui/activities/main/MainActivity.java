package com.rz.dictionaryapp.ui.activities.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.rz.dictionaryapp.R;
import com.rz.dictionaryapp.ui.fragments.engtoind.EngToIndFragment;
import com.rz.dictionaryapp.ui.fragments.indtoeng.IndToEngFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.rz.dictionaryapp.ui.activities.main.MainPresenter.I_TO_E;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainContract.View {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.drawer_layout) DrawerLayout drawer;
    @BindView(R.id.nav_view) NavigationView navigationView;

    ActionBarDrawerToggle toggle;
    MainContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        presenter = new MainPresenter(this);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        presenter.changeFragment(I_TO_E);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.i_to_e:
                if(!(getSupportFragmentManager().findFragmentById(R.id.main_container) instanceof IndToEngFragment)){
                    presenter.changeFragment(MainPresenter.I_TO_E);
                }
                break;
            case R.id.e_to_i:
                if(!(getSupportFragmentManager().findFragmentById(R.id.main_container) instanceof EngToIndFragment)){
                    presenter.changeFragment(MainPresenter.E_TO_I);
                }
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void setFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .replace(R.id.main_container, fragment)
                .commit();

        toolbar.setSubtitle(fragment instanceof IndToEngFragment ?
                getString(R.string.indtoeng) : getString(R.string.engtoind));
    }

    @Override
    public void setRvData() {

    }
}
