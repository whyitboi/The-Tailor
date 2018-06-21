package com.example.junior.thetailorv20;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

import static android.support.v4.content.ContextCompat.startActivity;

public class design_home_activity extends AppCompatActivity
        implements NavigationDrawerCallbacks {
    private FirebaseAuth firebaseAuth;
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.design_home_layout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.design_fragment_drawer);
        // Set up the drawer.
        mNavigationDrawerFragment.setup(R.id.design_fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), mToolbar);
        firebaseAuth = FirebaseAuth.getInstance();
        // onNavigationDrawerItemSelected(1);

       design_home_activity designHomeActivity = new design_home_activity();
        //anotherClass = designHomeActivity;



    }

    private void logout() {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(design_home_activity.this, login_activity.class));
    }

/*    public static Context getContextOfApplication()
    {
        return contextOfApplication;
    }*/


    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments

        if (position == 0) {
            // setTitle("Client Designs");
            design_clientsdesign_fragment designClientsdesignFragment = new design_clientsdesign_fragment();
            FragmentManager fragmentManger = getSupportFragmentManager();
            fragmentManger.beginTransaction().replace(R.id.design_container, designClientsdesignFragment).commit();
        } else if (position == 1) {
            //setTitle("Add New Design");
            add_design_fragment addDesignFragment = new add_design_fragment();
            FragmentManager fragmentManger = getSupportFragmentManager();
            fragmentManger.beginTransaction().replace(R.id.design_container, addDesignFragment).commit();
        } else if (position == 2) {
            // setTitle("Second Frag");
            add_sample_fragment addSampleFragment = new add_sample_fragment();
            FragmentManager fragmentManger = getSupportFragmentManager();
            fragmentManger.beginTransaction().replace(R.id.design_container, addSampleFragment).commit();
        }

    }


    @Override
    public void onBackPressed() {
        if (mNavigationDrawerFragment.isDrawerOpen())
            mNavigationDrawerFragment.closeDrawer();
        /*else
            startActivity(new Intent(design_home_activity.this, home_activity.class));// super.onBackPressed();*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.menu, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logout) {
            logout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
