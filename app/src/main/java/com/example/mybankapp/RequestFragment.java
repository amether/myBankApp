package com.example.mybankapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RequestFragment extends Fragment {
    private ArrayList<RecyclerRequestItem> recyclerItems = new ArrayList<>();
    private ArrayList<RecyclerRequestItem> tempRecyclerItems = new ArrayList<>();
    boolean filterChanged;
    String bankName;
    int requestCounter;
    int moneyCount;
    int percent;
    int time;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            bankName = savedInstanceState.getString("bank name");
            requestCounter = savedInstanceState.getInt("requestCounter");
            moneyCount = savedInstanceState.getInt("moneycount");
            percent = savedInstanceState.getInt("percent");
        }
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.request_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_requests);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        RequestRecyclerAdapter adapter = new RequestRecyclerAdapter(recyclerItems, getContext());
        recyclerView.setAdapter(adapter);

        Bundle args = getArguments();

        if (args != null && args.containsKey("filterChanged")) {
            filterChanged = args.getBoolean("filterChanged");
            if (filterChanged) {
                bankName = args.getString("bank name");
                requestCounter = args.getInt("requestCounter");
                moneyCount = args.getInt("moneycount");
                time = args.getInt("time");
                percent = (int) (Math.random()*10+5);
                RecyclerRequestItem newItem = new RecyclerRequestItem(bankName, requestCounter, percent, moneyCount, "В обработке", time);
                boolean isHere = false;
                if (!recyclerItems.isEmpty()) {
                    for (int i = 0; i < recyclerItems.size(); i++) {
                        if (bankName.equals(recyclerItems.get(i).getBankName()) && requestCounter == recyclerItems.get(i).getRequestCount()
                                && moneyCount == recyclerItems.get(i).getMoneyCount() && time == recyclerItems.get(i).getTime()) {
                            isHere = true;
                        }
                    }
                    if(!isHere){
                        recyclerItems.add(newItem);
                    }
                } else {
                    recyclerItems.add(newItem);
                }
            }
        }
    }
}
