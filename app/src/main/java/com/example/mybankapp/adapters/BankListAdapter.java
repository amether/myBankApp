package com.example.mybankapp.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mybankapp.R;
import com.example.mybankapp.models.BankListRecyclerItem;

import java.util.ArrayList;

import static com.example.mybankapp.constants.Constants.PARAM_BROADCAST_NAME;
import static com.example.mybankapp.constants.Constants.PARAM_BUNDLE_NAME;
import static com.example.mybankapp.constants.Constants.PARAM_SWITCH_TYPE;
import static com.example.mybankapp.constants.Constants.PARAM_SWITCH_TYPE_ITEM_FRAGMENT;

public class BankListAdapter extends RecyclerView.Adapter<BankListAdapter.ViewHolder> {

    private ArrayList<BankListRecyclerItem> itemList;

    public BankListAdapter(java.util.ArrayList<BankListRecyclerItem> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bank_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder itemView, int position) {
        BankListRecyclerItem itemLists = itemList.get(position);
        itemView.txtTitle.setText(itemLists.getTitle());
        itemView.imageView.setImageResource(itemLists.getImage());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle;
        public ImageView imageView;

        ViewHolder(@NonNull final View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            imageView = itemView.findViewById(R.id.imageView);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(PARAM_BROADCAST_NAME);
                    intent.putExtra(PARAM_SWITCH_TYPE, PARAM_SWITCH_TYPE_ITEM_FRAGMENT);
                    intent.putExtra(PARAM_BUNDLE_NAME, txtTitle.getText().toString());
                    txtTitle.getContext().sendBroadcast(intent);
                }
            });

        }
    }
}
