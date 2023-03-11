package com.example.cinemadatabase;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cinemadatabase.db.AppDatabase;
import com.example.cinemadatabase.db.CinemaEntity;
import com.example.cinemadatabase.db.FilmEntity;

public class AddNewFilmActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_film);

        final EditText filmNameInput= findViewById(R.id.filmNameInput);
        final EditText durationInput= findViewById(R.id.durationInput);
        final EditText cinemaAddressInput=findViewById(R.id.cinemaAddress);
        Button saveButton=findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNewFilm(filmNameInput.getText().toString(),durationInput.getText().toString(),cinemaAddressInput.getText().toString());
            }
        });
    }

    private void saveNewFilm(String filmName,String duration,String cinemaAddress){
        AppDatabase db=AppDatabase.getDBInstance(this.getApplicationContext());

        FilmEntity film = new FilmEntity();
        film.filmName = filmName;
        film.duration = duration;

        CinemaEntity cinema=new CinemaEntity();
        cinema.address=cinemaAddress;

        db.filmDao().insertFilm(film);
        db.cinemaDao().insertCinema(cinema);

        finish();
    }

}
