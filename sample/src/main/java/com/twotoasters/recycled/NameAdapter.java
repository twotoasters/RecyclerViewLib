package com.twotoasters.recycled;


import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.twotoasters.android.support.v7.widget.RecyclerView;

import java.util.List;
import java.util.Random;

public class NameAdapter extends RecyclerView.Adapter<NameViewHolder> {
    private List<Item> mNames;

    public NameAdapter(List<Item> names) {
        this.mNames = names;
    }

    /**
     * @param name to be added to the list in a random position.
     */
    public void addToList(Item name) {
        int position = 0;
        if (mNames.size() > 1) {
            // Put the new name in a random place.
            position = new Random().nextInt(mNames.size() - 1);
        }
        mNames.add(position, name);
        notifyItemInserted(position);
    }

    public void removeItemFromList(Item name) {
        int position = mNames.indexOf(name);
        mNames.remove(position);
        notifyItemRemoved(position);
    }


    @Override
    public NameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item, parent, false);
        return new NameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NameViewHolder viewHolder, final int position) {
        final Item name = mNames.get(position);
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
        return mNames.size();
    }
}
