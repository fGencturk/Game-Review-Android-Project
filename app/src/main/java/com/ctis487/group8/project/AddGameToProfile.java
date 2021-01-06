package com.ctis487.group8.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AddGameToProfile extends AppCompatActivity {

    TextView name, year, rating;
    ImageView img;

    EditText hoursSpent, review;
    SeekBar ratingSeekbar;

    Button saveReviewBtn;

    DatabaseHelper dbHelper;
    boolean update;
    GameReview foundReview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_game_to_profile);

        update = false;
        foundReview = null;

        img = findViewById(R.id.gameImg);
        name = findViewById(R.id.gameNameTV);
        rating = findViewById(R.id.gameRatingTV);
        year = findViewById(R.id.gameYearTV);

        Intent intent = getIntent();
        final Game game = intent.getParcelableExtra("game");

        Picasso
            .with(this)
            .load(game.getImage())
            .into(img);
        Log.d("LOG", game.getImage());
        name.setText(game.getName());
        rating.setText("" + game.getRating());
        year.setText("" + game.getYear());

        hoursSpent = findViewById(R.id.hoursSpentInput);
        review = findViewById(R.id.reviewInput);
        ratingSeekbar = findViewById(R.id.ratingSeekbar);
        saveReviewBtn = findViewById(R.id.saveReviewBtn);

        dbHelper = new DatabaseHelper(this);
        ArrayList<GameReview> gameReviews = GameReviewDB.getAllGameReviews(dbHelper);

        for(GameReview gameReview: gameReviews) {
            if(gameReview.getGameId() == game.getId()) {
                foundReview = gameReview;
                update = true;
                break;
            }
        }
        if(foundReview != null) {
            hoursSpent.setText("" + foundReview.getHoursSpent());
            ratingSeekbar.setProgress(foundReview.getRating());
            review.setText(foundReview.getReview());

            saveReviewBtn.setText("Update Review");
        }


        saveReviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int hoursSpentValue = Integer.parseInt(hoursSpent.getText().toString());
                    int ratingValue = ratingSeekbar.getProgress();
                    String reviewValue = review.getText().toString();
                    if(reviewValue.length() == 0) {
                        throw new Exception();
                    }
                    Log.d("MYAPP", "Update value : " + update);
                    if(!update) {
                        if(GameReviewDB.insert(dbHelper, game.getId(), hoursSpentValue, ratingValue, reviewValue )) {
                            Toast.makeText(v.getContext(), "Inserted", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(v.getContext(), "An error occurred while inserting.", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Log.d("ID", "" + foundReview.getId());
                        if(GameReviewDB.update(dbHelper, foundReview.getId(), game.getId(), hoursSpentValue, ratingValue, reviewValue )) {
                            Toast.makeText(v.getContext(), "Updated", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(v.getContext(), "An error occurred while updating.", Toast.LENGTH_LONG).show();
                        }
                    }
                } catch (Exception e) {
                    Toast.makeText(v.getContext(), "An error occurred. Please provide appropriate inputs.", Toast.LENGTH_LONG).show();
                    Log.d("MYAPP", e.getMessage());
                }
            }
        });
    }
}