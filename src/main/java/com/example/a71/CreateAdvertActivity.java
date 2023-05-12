package com.example.a71;

import androidx.appcompat.app.AppCompatActivity;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class CreateAdvertActivity extends AppCompatActivity {

    private EditText etName, etPhoneNumber, etDescription, etDate, etLocation;
    private RadioGroup radioGroupPostType;
    private Button btnSaveAdvert;
    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_advert);

        dbHelper = new DbHelper(this);

        etName = findViewById(R.id.etName);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        etDescription = findViewById(R.id.etDescription);
        etDate = findViewById(R.id.etDate);
        etLocation = findViewById(R.id.etLocation);
        radioGroupPostType = findViewById(R.id.radioGroupPostType);
        btnSaveAdvert = findViewById(R.id.btnSubmit);

        btnSaveAdvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAdvert();
                finish(); // Finish the activity and return to the previous screen
            }
        });
    }

    private void saveAdvert() {
        // Get the selected post type (Lost or Found)
        RadioButton radioButton = findViewById(radioGroupPostType.getCheckedRadioButtonId());
        String postType = radioButton.getText().toString();

        String name = etName.getText().toString();
        String phoneNumber = etPhoneNumber.getText().toString();
        String description = etDescription.getText().toString();
        String date = etDate.getText().toString();
        String location = etLocation.getText().toString();

        // Create a new instance of ContentValues to store the advert data
        ContentValues values = new ContentValues();
        values.put(AdvertContract.AdvertEntry.COLUMN_POST_TYPE, postType);
        values.put(AdvertContract.AdvertEntry.COLUMN_NAME, name);
        values.put(AdvertContract.AdvertEntry.COLUMN_PHONE_NUMBER, phoneNumber);
        values.put(AdvertContract.AdvertEntry.COLUMN_DESCRIPTION, description);
        values.put(AdvertContract.AdvertEntry.COLUMN_DATE, date);
        values.put(AdvertContract.AdvertEntry.COLUMN_LOCATION, location);

        // Get a writable database instance
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Insert the values into the adverts table
        long newRowId = db.insert(AdvertContract.AdvertEntry.TABLE_NAME, null, values);

        // Check if the insertion was successful
        if (newRowId == -1) {
            // Show an error message or handle the failure
        } else {
            // Retrieve the advert from the database using the row ID
            Advert advert = getAdvertFromDatabase(db, newRowId);

            if (advert != null) {
                // Set the ID value in the advert object
                advert.setId((int) newRowId);

                // Print out the advert object
                Log.d("CreateAdvertActivity", "Saved Advert: " + advert.toString());
            }
        }

        // Close the database connection
        db.close();
    }

    // Retrieve the advert from the database using the row ID
    private Advert getAdvertFromDatabase(SQLiteDatabase db, long advertId) {
        // Define the projection for the query
        String[] projection = {
                AdvertContract.AdvertEntry._ID,
                AdvertContract.AdvertEntry.COLUMN_POST_TYPE,
                AdvertContract.AdvertEntry.COLUMN_NAME,  // Add COLUMN_NAME to the projection
                AdvertContract.AdvertEntry.COLUMN_PHONE_NUMBER,  // Add COLUMN_PHONE_NUMBER to the projection
                AdvertContract.AdvertEntry.COLUMN_DESCRIPTION,
                AdvertContract.AdvertEntry.COLUMN_DATE,
                AdvertContract.AdvertEntry.COLUMN_LOCATION
        };

        // Define the selection criteria
        String selection = AdvertContract.AdvertEntry._ID + " = ?";
        String[] selectionArgs = {String.valueOf(advertId)};

        // Execute the query
        Cursor cursor = db.query(
                AdvertContract.AdvertEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        // Retrieve the column indices
        int idColumnIndex = cursor.getColumnIndex(AdvertContract.AdvertEntry._ID);
        int postTypeColumnIndex = cursor.getColumnIndex(AdvertContract.AdvertEntry.COLUMN_POST_TYPE);
        int nameColumnIndex = cursor.getColumnIndex(AdvertContract.AdvertEntry.COLUMN_NAME);
        int phoneNumberColumnIndex = cursor.getColumnIndex(AdvertContract.AdvertEntry.COLUMN_PHONE_NUMBER);
        int descriptionColumnIndex = cursor.getColumnIndex(AdvertContract.AdvertEntry.COLUMN_DESCRIPTION);
        int dateColumnIndex = cursor.getColumnIndex(AdvertContract.AdvertEntry.COLUMN_DATE);
        int locationColumnIndex = cursor.getColumnIndex(AdvertContract.AdvertEntry.COLUMN_LOCATION);


        // Check if the cursor contains a valid result
        if (cursor != null && cursor.moveToFirst()) {
            // Retrieve the advert data from the cursor
            int id = cursor.getInt(idColumnIndex);
            String postType = cursor.getString(postTypeColumnIndex);
            String name = (nameColumnIndex != -1) ? cursor.getString(nameColumnIndex) : "";
            String phoneNumber = (phoneNumberColumnIndex != -1) ? cursor.getString(phoneNumberColumnIndex) : "";
            String description = cursor.getString(descriptionColumnIndex);
            String date = cursor.getString(dateColumnIndex);
            String location = cursor.getString(locationColumnIndex);

            // Create a new Advert object with the retrieved data
            Advert advert = new Advert(id, postType, description, date, location);



            // Close the cursor
            cursor.close();

            // Return the advert object
            return advert;
        }

        // If the cursor is null or no results found, return null
        return null;
    }



}