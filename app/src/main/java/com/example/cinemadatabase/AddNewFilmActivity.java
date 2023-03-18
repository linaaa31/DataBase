package com.example.cinemadatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cinemadatabase.db.AppDatabase;
import com.example.cinemadatabase.db.CinemaEntity;
import com.example.cinemadatabase.db.FilmEntity;

//public class AddNewFilmActivity extends AppCompatActivity {
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_new_film);
//
//        final EditText filmNameInput= findViewById(R.id.filmNameInput);
//        final EditText durationInput= findViewById(R.id.durationInput);
//        final EditText cinemaAddressInput=findViewById(R.id.cinemaAddress);
//        Button saveButton=findViewById(R.id.saveButton);
//        saveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                saveNewFilm(filmNameInput.getText().toString(),durationInput.getText().toString(),cinemaAddressInput.getText().toString());
//            }
//        });
//    }
//
//    private void saveNewFilm(String filmName,String duration,String cinemaAddress){
//        AppDatabase db=AppDatabase.getDBInstance(this.getApplicationContext());
//
//        FilmEntity film = new FilmEntity();
//        film.filmName = filmName;
//        film.duration = duration;
//
//        CinemaEntity cinema=new CinemaEntity();
//        cinema.address=cinemaAddress;
//
//        db.filmDao().insertFilm(film);
//        db.cinemaDao().insertCinema(cinema);
//
//        finish();
//    }
//
//}
public class AddNewFilmActivity extends AppCompatActivity {
    ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_film);

        final EditText filmNameInput = findViewById(R.id.filmNameInput);
        final EditText durationInput = findViewById(R.id.durationInput);
        final EditText cinemaAddressInput = findViewById(R.id.cinemaAddress);
        Button saveButton = findViewById(R.id.saveButton);

        // Initialize the ActivityResultLauncher
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        // Reload the film list when the AddNewFilmActivity is finished
                        if (result.getResultCode() == RESULT_OK) {
                            Intent intent = result.getData();
                            if (intent != null) {
                                boolean isDataChanged = intent.getBooleanExtra("isDataChanged", false);
                                if (isDataChanged) {
                                    setResult(RESULT_OK, intent);
                                    finish();
                                }
                            }
                        }
                    }
                });

        // Set an onClickListener for the saveButton
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filmName = filmNameInput.getText().toString();
                String duration = durationInput.getText().toString();
                String cinemaAddress = cinemaAddressInput.getText().toString();

                // Save the new film data to the database
                AppDatabase db = AppDatabase.getDBInstance(getApplicationContext());
                FilmEntity film = new FilmEntity();
                film.filmName = filmName;
                film.duration = duration;
                CinemaEntity cinema = new CinemaEntity();
                cinema.address = cinemaAddress;
                db.filmDao().insertFilm(film);
                db.cinemaDao().insertCinema(cinema);

                // Create an intent to return the result to the MainActivity
                Intent intent = new Intent();
                intent.putExtra("isDataChanged", true);

                // Set the result and finish the AddNewFilmActivity
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    // Override the onBackPressed method to set the result to canceled when the back button is pressed
    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        super.onBackPressed();
    }
}
