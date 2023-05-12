package com.example.a71;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.a71.CreateAdvertActivity;
import com.example.a71.R;
import com.example.a71.ShowAllItemsActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnCreateAdvert;
    private Button btnShowAllItems;
    private DbHelper dbHelper;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DbHelper(this);
        database = dbHelper.getWritableDatabase();

        btnCreateAdvert = findViewById(R.id.btnCreateAdvert);
        btnCreateAdvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateAdvertActivity.class);
                startActivity(intent);
            }
        });

        btnShowAllItems = findViewById(R.id.btnShowAllItems);
        btnShowAllItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShowAllItemsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Close the database connection
        database.close();
        dbHelper.close();
    }
}
