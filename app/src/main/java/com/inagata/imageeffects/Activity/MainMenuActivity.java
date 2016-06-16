package com.inagata.imageeffects.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.inagata.imageeffects.Fragment.EffectsFilterFragment;
import com.inagata.imageeffects.Fragment.GpuFilterFragment;
import com.inagata.imageeffects.Fragment.LayerDrawableFragment;
import com.inagata.imageeffects.R;

/**
 * Created by mnafian on 6/16/15.
 */
public class MainMenuActivity extends AppCompatActivity{

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imf_mainmenu_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
            changeFragment(new EffectsFilterFragment());
        }
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();

                        int id = menuItem.getItemId();
                        switch (id) {
                            case R.id.bt_gles:
                                changeFragment(new EffectsFilterFragment());
                                break;
                            case R.id.bt_layerd:
                                changeFragment(new LayerDrawableFragment());
                                break;
                            case R.id.bt_gpu:
                                changeFragment(new GpuFilterFragment());
                                break;
                        }

                        return true;
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.about_githubmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.About:
                Intent inAbout = new Intent(this, AboutMe.class);
                startActivity(inAbout);
                break;

            case R.id.Github:
                String url = "https://github.com/mnafian";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                break;

            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void changeFragment(Fragment targetFragment) {
            android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.main_fragment, targetFragment);
            transaction.commit();
    }
}
