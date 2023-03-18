package com.example.cinemadatabase;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cinemadatabase.db.AppDatabase;
import com.example.cinemadatabase.db.CinemaEntity;
import com.example.cinemadatabase.db.FilmEntity;

import java.util.List;

//public class MainActivity extends AppCompatActivity {
//    private FilmListAdapter filmListAdapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        Button addNewFilmButton = findViewById(R.id.addNewFilmButton);
//        addNewFilmButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivityForResult(new Intent(MainActivity.this, AddNewFilmActivity.class), 100);
//            }
//        });
//
//        initRecyclerView();
//
//        loadFilmList();
//    }
//
//
//    private void initRecyclerView() {
//        RecyclerView recyclerView = findViewById(R.id.recyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL );
//        recyclerView.addItemDecoration(dividerItemDecoration);
//        filmListAdapter=new FilmListAdapter(this);
//        recyclerView.setAdapter(filmListAdapter);
//    }
//
//    private void loadFilmList() {
//        AppDatabase db = AppDatabase.getDBInstance(this.getApplicationContext());
//        List<FilmEntity> filmList = db.filmDao().getAllFilms();
//        List<CinemaEntity> cinemaList=db.cinemaDao().getAllCinemas();
//        filmListAdapter.setFilmList(filmList,cinemaList);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        if (requestCode == 100) {
//            loadFilmList();
//        }
//
//        super.onActivityResult(requestCode, resultCode, data);
//    }
//}
public class MainActivity extends AppCompatActivity {

    private FilmListAdapter filmListAdapter;

    private final ActivityResultLauncher<Intent> addNewFilmLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    loadFilmList();
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addNewFilmButton = findViewById(R.id.addNewFilmButton);
        addNewFilmButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddNewFilmActivity.class);
            addNewFilmLauncher.launch(intent);
        });

        initRecyclerView();

        loadFilmList();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        filmListAdapter = new FilmListAdapter(this);
        recyclerView.setAdapter(filmListAdapter);
    }
private void loadFilmList() {
    AppDatabase db = AppDatabase.getDBInstance(this.getApplicationContext());
    List<FilmEntity> filmList = db.filmDao().getAllFilms();
    List<CinemaEntity> cinemaList = db.cinemaDao().getAllCinemas();
    filmListAdapter.setFilmList(filmList, cinemaList);
}

}
