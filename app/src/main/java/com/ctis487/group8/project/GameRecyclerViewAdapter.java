package com.ctis487.group8.project;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GameRecyclerViewAdapter extends RecyclerView.Adapter<GameRecyclerViewAdapter.MyRecyclerViewItemHolder> {
    private Context context;
    private ArrayList<Game> recyclerItemValues;

    public GameRecyclerViewAdapter(Context context, ArrayList<Game> values){
        this.context = context;
        this.recyclerItemValues = values;
    }

    @NonNull
    @Override
    public MyRecyclerViewItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflator = LayoutInflater.from(viewGroup.getContext());

        View itemView = inflator.inflate(R.layout.game_recycler_item, viewGroup, false);

        MyRecyclerViewItemHolder mViewHolder = new MyRecyclerViewItemHolder(itemView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewItemHolder myRecyclerViewItemHolder, int i) {

        final Game sm = recyclerItemValues.get(i);

        Picasso
            .with(context)
            .load(sm.getImage())
            .into(myRecyclerViewItemHolder.img);
        Log.d("LOG", sm.getImage());
        myRecyclerViewItemHolder.name.setText(sm.getName());
        myRecyclerViewItemHolder.rating.setText("" + sm.getRating());
        myRecyclerViewItemHolder.year.setText("" + sm.getYear());

        myRecyclerViewItemHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AddGameToProfile.class);
                intent.putExtra("game", sm);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return recyclerItemValues.size();
    }

    class MyRecyclerViewItemHolder extends  RecyclerView.ViewHolder {
        TextView name, year, rating;
        ImageView img;
        ConstraintLayout parentLayout;
        public MyRecyclerViewItemHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.gameImg);
            name = itemView.findViewById(R.id.gameNameTV);
            rating = itemView.findViewById(R.id.gameRatingTV);
            year = itemView.findViewById(R.id.gameYearTV);
            parentLayout = itemView.findViewById(R.id.GameLayout);
        }
    }

}

