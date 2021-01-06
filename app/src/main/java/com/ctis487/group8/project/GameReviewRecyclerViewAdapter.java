package com.ctis487.group8.project;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GameReviewRecyclerViewAdapter extends RecyclerView.Adapter<GameReviewRecyclerViewAdapter.MyRecyclerViewItemHolder>  {
    private Context context;
    private ArrayList<GameReview> recyclerItemValues;

    public GameReviewRecyclerViewAdapter(Context context, ArrayList<GameReview> values){
        this.context = context;
        this.recyclerItemValues = values;
    }

    @NonNull
    @Override
    public GameReviewRecyclerViewAdapter.MyRecyclerViewItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflator = LayoutInflater.from(viewGroup.getContext());

        View itemView = inflator.inflate(R.layout.game_review_recycler_item, viewGroup, false);

        GameReviewRecyclerViewAdapter.MyRecyclerViewItemHolder mViewHolder = new GameReviewRecyclerViewAdapter.MyRecyclerViewItemHolder(itemView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GameReviewRecyclerViewAdapter.MyRecyclerViewItemHolder myRecyclerViewItemHolder, int i) {

        final GameReview sm = recyclerItemValues.get(i);
        Game game = null;
        for(Game g:GameReviewDB.games) {
            if(g.getId() == sm.getGameId()) {
                game = g;
                break;
            }
        }
        final Game g = game;
        Picasso
                .with(context)
                .load(game.getImage())
                .into(myRecyclerViewItemHolder.img);
        myRecyclerViewItemHolder.name.setText(game.getName());
        myRecyclerViewItemHolder.rating.setText("" + sm.getRating());
        myRecyclerViewItemHolder.review.setText("" + sm.getReview());
        myRecyclerViewItemHolder.hoursSpent.setText("" + sm.getHoursSpent());

        myRecyclerViewItemHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AddGameToProfile.class);
                intent.putExtra("game", g);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return recyclerItemValues.size();
    }

    class MyRecyclerViewItemHolder extends  RecyclerView.ViewHolder {
        TextView name, review, rating, hoursSpent;
        ImageView img;
        ConstraintLayout parentLayout;
        public MyRecyclerViewItemHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.gameImg);
            name = itemView.findViewById(R.id.gameNameTV);
            rating = itemView.findViewById(R.id.ratingTV);
            hoursSpent = itemView.findViewById(R.id.hoursSpentTV);
            review = itemView.findViewById(R.id.reviewTV);
            parentLayout = itemView.findViewById(R.id.GameLayout);
        }
    }

}
