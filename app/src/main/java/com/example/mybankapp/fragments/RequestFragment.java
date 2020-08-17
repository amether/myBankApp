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
import com.example.mybankapp.models.RequestListRecyclerItem;
import com.example.mybankapp.adapters.RequestListAdapter;

import java.util.ArrayList;

import static com.example.mybankapp.constants.Constants.PARAM_BUNDLE_NAME;
import static com.example.mybankapp.constants.Constants.PARAM_BUNDLE_TO_REQUEST_FRAGMENT_MONEY_COUNT;
import static com.example.mybankapp.constants.Constants.PARAM_BUNDLE_TO_REQUEST_FRAGMENT_REQUEST_COUNTER;
import static com.example.mybankapp.constants.Constants.PARAM_BUNDLE_TO_REQUEST_FRAGMENT_TIME;
import static com.example.mybankapp.constants.Constants.PARAM_FILTER_CHANGED;

public class RequestFragment extends Fragment {
    private ArrayList<RequestListRecyclerItem> recyclerItems = new ArrayList<>();
    private String bankName;
    private int requestCounter;
    private int moneyCount;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            bankName = savedInstanceState.getString(PARAM_BUNDLE_NAME);
            requestCounter = savedInstanceState.getInt(PARAM_BUNDLE_TO_REQUEST_FRAGMENT_REQUEST_COUNTER);
            moneyCount = savedInstanceState.getInt(PARAM_BUNDLE_TO_REQUEST_FRAGMENT_MONEY_COUNT);
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
        RequestListAdapter adapter = new RequestListAdapter(recyclerItems, getContext());
        recyclerView.setAdapter(adapter);

        Bundle args = getArguments();

        if (args != null && args.containsKey(PARAM_FILTER_CHANGED)) {
            boolean filterChanged = args.getBoolean(PARAM_FILTER_CHANGED);
            if (filterChanged) {
                bankName = args.getString(PARAM_BUNDLE_NAME);
                requestCounter = args.getInt(PARAM_BUNDLE_TO_REQUEST_FRAGMENT_REQUEST_COUNTER);
                moneyCount = args.getInt(PARAM_BUNDLE_TO_REQUEST_FRAGMENT_MONEY_COUNT);
                int time = args.getInt(PARAM_BUNDLE_TO_REQUEST_FRAGMENT_TIME);
                int percent = (int) (Math.random() * 10 + 5);
                String requestState = "В обработке";
                RequestListRecyclerItem newItem = new RequestListRecyclerItem(bankName, requestCounter, percent, moneyCount, requestState, time);
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
