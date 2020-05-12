package com.example.mybankapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static java.lang.String.valueOf;

public class ListFragment extends Fragment {
    private ArrayList<RecyclerItem> recyclerItems = new ArrayList<>();
    private ArrayList<String> bankNames = new ArrayList<>();
    private boolean filterChanged;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            bankNames = savedInstanceState.getStringArrayList("bankItems");
        }
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.Recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerAdapter adapter = new RecyclerAdapter(recyclerItems, getContext());
        recyclerView.setAdapter(adapter);

        filterChanged = false;

        Bundle args = getArguments();
        if (args == null) {
            args = savedInstanceState;
        }

        if (args != null && args.containsKey("filterChanged")){
            filterChanged = args.getBoolean("filterChanged");
        }
        if (filterChanged) {
            recyclerItems.clear();
            if (args.containsKey("bankItems")) {
                bankNames = args.getStringArrayList("bankItems");
                itemsAdd();
            }
        }
    }

    private void itemsAdd(){
        for (int i = 0; i < bankNames.size(); i++) {
            recyclerItems.add(new RecyclerItem(bankNames.get(i)));
        }
    }

}
