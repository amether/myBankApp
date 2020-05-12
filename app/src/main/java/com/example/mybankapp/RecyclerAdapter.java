package com.example.mybankapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static com.example.mybankapp.Constants.PARAM_BUNDLE_NAME;
import static com.example.mybankapp.Constants.PARAM_SWITCH_TYPE;
import static com.example.mybankapp.Constants.PARAM_SWITCH_TYPE_FILTER_FRAGMENT;
import static com.example.mybankapp.Constants.PARAM_SWITCH_TYPE_ITEM_FRAGMENT;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private ArrayList<RecyclerItem> itemList;
    private TextView txtTitle2;
    private static Context context;

    public RecyclerAdapter(java.util.ArrayList<RecyclerItem> itemList, Context mContext) {
        this.itemList = itemList;
        this.context = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder itemView, int position) {
        RecyclerItem itemLists = itemList.get(position);
        itemView.txtTitle.setText(itemLists.getTitle());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle;
//        public ImageView imageView;

        ViewHolder(@NonNull final View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent("myDumbBroadcast");
                    intent.putExtra(PARAM_SWITCH_TYPE, PARAM_SWITCH_TYPE_ITEM_FRAGMENT);
                    intent.putExtra(PARAM_BUNDLE_NAME, txtTitle.getText().toString());
                    context.sendBroadcast(intent);
                }
            });
//            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
