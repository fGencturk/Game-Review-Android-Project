package com.ctis487.group8.project;

import android.content.IntentFilter;
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

public class GamesFragment extends Fragment {

    RecyclerView gamesRecyclerView;
    LinearLayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_games, container, false);

        // startService(new Intent(this,MusicPlayerService.class));

        String jsonString = JSONHelper.loadJSONFromAsset(getActivity(), "games.json");
        Gson g = new Gson();
        Game[] games = g.fromJson(jsonString, Game[].class);

        ArrayList<Game> gameArrayList = new ArrayList<>();
        GameReviewDB.games = gameArrayList;
        for(Game game:games) {
            gameArrayList.add(game);
        }

        GameRecyclerViewAdapter gameRecyclerViewAdapter = new GameRecyclerViewAdapter(getContext(), gameArrayList);
        gamesRecyclerView = view.findViewById(R.id.gamesRecyclerView);
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        gamesRecyclerView.setLayoutManager(layoutManager);
        gamesRecyclerView.setAdapter(gameRecyclerViewAdapter);

        return view;
    }
}
