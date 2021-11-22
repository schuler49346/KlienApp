package com.example.klienapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private Button logoutMain;
    private FirebaseAuth mAuth;
    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private ViewPager2 mViewPager;
    private  SectionsPagerAdapter mSectionPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        mToolbar = (Toolbar) findViewById(R.id.main_app_bar);
        mViewPager = (ViewPager2) findViewById(R.id.main_tab_pager);
        mSectionPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(),getLifecycle());

        mViewPager.setAdapter(mSectionPagerAdapter);
        mTabLayout = (TabLayout) findViewById(R.id.main_tabs);
        mTabLayout.addTab(mTabLayout.newTab().setText("Requests"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Chats"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Friends"));

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser == null){
            sendtostart();
        }
    }

    private void sendtostart() {
        Intent startIntent = new Intent(MainActivity.this, StartActivity.class);
        startActivity(startIntent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.main_logout_button){
            FirebaseAuth.getInstance().signOut();
            sendtostart();
        }
        if(item.getItemId() == R.id.main_profile_button){
            Intent profileIntent = new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(profileIntent);
        }
        if(item.getItemId() == R.id.main_all_users){
            Intent usersIntent = new Intent(MainActivity.this, AllUserActivity.class);
            startActivity(usersIntent);
        }
        return true;
    }
}