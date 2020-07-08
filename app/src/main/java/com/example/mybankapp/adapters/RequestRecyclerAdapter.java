package com.example.mybankapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mybankapp.R;
import com.example.mybankapp.classes.RecyclerRequestItem;

import java.util.ArrayList;

import static java.lang.String.valueOf;

public class RequestRecyclerAdapter extends RecyclerView.Adapter<RequestRecyclerAdapter.ViewHolder> {

    private ArrayList<RecyclerRequestItem> itemList;
    private static Context context;

    public RequestRecyclerAdapter(java.util.ArrayList<RecyclerRequestItem> itemList, Context mContext) {
        this.itemList = itemList;
        this.context = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.request_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder itemView, int position) {
        RecyclerRequestItem itemLists = itemList.get(position);
        itemView.bankName.setText(itemLists.getBankName());
        String mCount = String.format("Сумма: %d", itemLists.getMoneyCount());
        itemView.moneyCount.setText(mCount);
        String bPercent = String.format("Процент: %d%%", itemLists.getPercent());
        itemView.bankPercent.setText(bPercent);
        itemView.requestNumber.setText("Заявка №" + valueOf(itemLists.getRequestCount()));
        itemView.requestState.setText("Статус: " + itemLists.getRequestState());
        double temp = (((itemLists.getMoneyCount() / 100) * itemLists.getPercent()) + itemLists.getMoneyCount()) / itemLists.getTime();
        itemView.monthlyPayment.setText("Ежемесячный платеж: " + temp);
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
