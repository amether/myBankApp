package com.example.mybankapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mybankapp.R;
import com.example.mybankapp.adapters.RecyclerAdapter;
import com.example.mybankapp.classes.RecyclerItem;

import java.util.ArrayList;

import static com.example.mybankapp.constants.Constants.PARAM_BUNDLE_TO_LIST_FRAGMENT_BANK_IMAGES;
import static com.example.mybankapp.constants.Constants.PARAM_BUNDLE_TO_LIST_FRAGMENT_BANK_ITEMS;
import static com.example.mybankapp.constants.Constants.PARAM_FILTER_CHANGED;


public class ListFragment extends Fragment {
    private ArrayList<RecyclerItem> recyclerItems = new ArrayList<>();
    private ArrayList<String> bankNames = new ArrayList<>();
    private ArrayList<Integer> bankImages = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            bankNames = savedInstanceState.getStringArrayList(PARAM_BUNDLE_TO_LIST_FRAGMENT_BANK_ITEMS);
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

        boolean filterChanged = false;

        Bundle args = getArguments();
        if (args == null) {
            args = savedInstanceState;
        }

        if (args != null && args.containsKey(PARAM_FILTER_CHANGED)){
            filterChanged = args.getBoolean(PARAM_FILTER_CHANGED);
        }
        if (filterChanged) {
            recyclerItems.clear();
            if (args.containsKey(PARAM_BUNDLE_TO_LIST_FRAGMENT_BANK_ITEMS)) {
                bankNames = args.getStringArrayList(PARAM_BUNDLE_TO_LIST_FRAGMENT_BANK_ITEMS);
                bankImages = args.getIntegerArrayList(PARAM_BUNDLE_TO_LIST_FRAGMENT_BANK_IMAGES);
                itemsAdd();
            }
        }
    }

    private void itemsAdd(){
        for (int i = 0; i < bankNames.size(); i++) {
            recyclerItems.add(new RecyclerItem(bankNames.get(i),bankImages.get(i)));
        }
    }

}
