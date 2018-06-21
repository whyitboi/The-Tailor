package com.example.junior.thetailorv20;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

public class payment_home_activity extends AppCompatActivity
        implements NavigationDrawerCallbacks {
    private FirebaseAuth firebaseAuth;
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_home_layout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.payment_fragment_drawer);

        // Set up the drawer.
        mNavigationDrawerFragment.setup(R.id.payment_fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), mToolbar);
        firebaseAuth = FirebaseAuth.getInstance();


    }

    private void logout() {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(payment_home_activity.this, login_activity.class));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments


        if (position == 0) {
            payment_list_fragment paymentListFragment = new payment_list_fragment();
            FragmentManager fragmentManger = getSupportFragmentManager();
            fragmentManger.beginTransaction().replace(R.id.container, paymentListFragment).commit();
        } else if (position == 1) {
            add_price_fragment addPriceFragment = new add_price_fragment();
            FragmentManager fragmentManger = getSupportFragmentManager();
            fragmentManger.beginTransaction().replace(R.id.container, addPriceFragment).commit();
            //fragmentManger.addToBackStack(null);
        }
    }


    @Override
    public void onBackPressed() {
        if (mNavigationDrawerFragment.isDrawerOpen())
            mNavigationDrawerFragment.closeDrawer();
        // else
        // startActivity(new Intent(clients_home_activity.this, home_activity.class));// super.onBackPressed();
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