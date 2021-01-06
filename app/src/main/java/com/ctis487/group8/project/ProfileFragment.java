package com.ctis487.group8.project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {

    RecyclerView gameReviewsRecyclerView;
    LinearLayoutManager layoutManager;
    DatabaseHelper dbHelper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        dbHelper = new DatabaseHelper(view.getContext());

        gameReviewsRecyclerView = view.findViewById(R.id.gamesRecyclerView);

        GameReviewRecyclerViewAdapter gameRecyclerViewAdapter = new GameReviewRecyclerViewAdapter(getContext(), GameReviewDB.getAllGameReviews(dbHelper));
        gameReviewsRecyclerView = view.findViewById(R.id.gamesRecyclerView);
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        gameReviewsRecyclerView.setLayoutManager(layoutManager);
        gameReviewsRecyclerView.setAdapter(gameRecyclerViewAdapter);

        return view;
    }
}
