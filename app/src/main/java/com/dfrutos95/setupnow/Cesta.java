package com.dfrutos95.setupnow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.navigation.NavigationBarView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

public class Cesta extends AppCompatActivity {
    NavigationBarView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cesta);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.navigation_home) {
                    mostrarInicio();
                    return true;
                } else if (itemId == R.id.navigation_notifications) {
                    // Handle Notifications navigation item click
                    return true;
                }
                return true;
            }
        });
    }

    public void mostrarInicio(){
        Intent abrirInicio = new Intent(this, MainActivity.class);
        startActivity(abrirInicio);
    }
}