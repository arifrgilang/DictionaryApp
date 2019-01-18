package com.rz.dictionaryapp.util;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rz.dictionaryapp.R;
import com.rz.dictionaryapp.data.model.DictModel;
import com.rz.dictionaryapp.ui.activities.detail.DetailActivity;

import java.util.ArrayList;

public class RvAdapter extends RecyclerView.Adapter<ListViewHolder> {
    private ArrayList<DictModel> mList;
    private Context context;

    public RvAdapter(Context context){
        this.mList = new ArrayList<>();
        this.context = context;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemRow = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.rv_viewholder, viewGroup, false);
        return new ListViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder listViewHolder, int i) {
        final DictModel model = mList.get(i);
        listViewHolder.rvText.setText(model.getDict());

        listViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(DetailActivity.WORD_MODEL, model);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setData(ArrayList<DictModel> data) {
        this.mList.clear();
        this.mList.addAll(data);
        notifyDataSetChanged();
    }
}