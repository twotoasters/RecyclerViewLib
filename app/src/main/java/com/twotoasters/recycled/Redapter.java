package com.twotoasters.recycled;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import java.util.List;
import java.util.Random;

public class Redapter extends RecyclerView.Adapter<ReViewHolder> {
    private List<Item> names;

    public Redapter(List<Item> names) {
        this.names = names;
    }

    public void addToList(Item name) {
        int position = 0;
        if (names.size() > 1) {
            // Put the new name in a random place.
            position = new Random().nextInt(names.size() - 1);
        }
        names.add(position, name);
        notifyItemInserted(position);
    }

    public void removeItemFromList(Item name) {
        int position = names.indexOf(name);
        names.remove(position);
        notifyItemRemoved(position);
    }


    @Override
    public ReViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item, parent, false);
        return new ReViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReViewHolder viewHolder, final int position) {
        final Item name = names.get(position);
        viewHolder.textView.setText(name.toString());
        viewHolder.textView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItemFromList(name);
            }
        });
    }

    @Override
    public int getItemCount() {
        return names.size();
    }
}
