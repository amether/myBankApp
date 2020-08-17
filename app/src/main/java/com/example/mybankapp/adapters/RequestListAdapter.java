package com.example.mybankapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mybankapp.R;
import com.example.mybankapp.models.RequestListRecyclerItem;

import java.util.ArrayList;

import static com.example.mybankapp.R.string.request_list_adapter_bPercent;

public class RequestListAdapter extends RecyclerView.Adapter<RequestListAdapter.ViewHolder> {

    private ArrayList<RequestListRecyclerItem> itemList;

    public RequestListAdapter(java.util.ArrayList<RequestListRecyclerItem> itemList, Context mContext) {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.request_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder itemView, int position) {
        RequestListRecyclerItem itemLists = itemList.get(position);
        itemView.bankName.setText(itemLists.getBankName());
        String mCount = String.format(String.valueOf(R.string.request_list_adapter_mCount), itemLists.getMoneyCount());
        itemView.moneyCount.setText(mCount);
        String bPercent = String.format(String.valueOf(request_list_adapter_bPercent), itemLists.getPercent());
        itemView.bankPercent.setText(bPercent);
        itemView.requestNumber.setText(R.string.request_list_adapter_requestNum + itemLists.getRequestCount());
        itemView.requestState.setText(R.string.request_list_adapter_requestState + itemLists.getRequestState());
        int temp = (((itemLists.getMoneyCount() / 100) * itemLists.getPercent()) + itemLists.getMoneyCount()) / itemLists.getTime();
        itemView.monthlyPayment.setText(R.string.request_list_adapter_monthlyPayment + temp);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView bankName;
        TextView moneyCount;
        TextView requestState;
        TextView requestNumber;
        TextView bankPercent;
        TextView monthlyPayment;

        ViewHolder(@NonNull final View itemView) {
            super(itemView);
            bankName = itemView.findViewById(R.id.bank_name_txt);
            moneyCount = itemView.findViewById(R.id.money_count_txt);
            requestState = itemView.findViewById(R.id.state_request_txt);
            requestNumber = itemView.findViewById(R.id.request_num_txt);
            bankPercent = itemView.findViewById(R.id.percent_txt);
            monthlyPayment = itemView.findViewById(R.id.monthly_payment_txt);

        }
    }
}
