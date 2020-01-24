package com.example.mysubmission3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MenuItem;

import com.example.mysubmission3.adapter.SectionsPagerAdapter;
import com.example.mysubmission3.function.AlertDialogManager;
import com.example.mysubmission3.function.ConnectionDetect;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sp = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager vp = findViewById(R.id.view_pager);
        vp.setAdapter(sp);
        TabLayout tb = findViewById(R.id.tabs);
        tb.setupWithViewPager(vp);
        Toolbar tbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(tbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.title));
        getSupportActionBar().setElevation(0);
    }





    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
