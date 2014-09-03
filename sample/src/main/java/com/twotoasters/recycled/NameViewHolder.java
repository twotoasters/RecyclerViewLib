package com.twotoasters.recycled;


import android.view.View;
import android.widget.TextView;

import com.twotoasters.android.support.v7.widget.CardView;
import com.twotoasters.android.support.v7.widget.RecyclerView.ViewHolder;

public class NameViewHolder extends ViewHolder {
    public final CardView cardView;
    public final TextView textView;

    public NameViewHolder(View itemView) {
        super(itemView);
        cardView = (CardView) itemView;
        cardView.setRadius(itemView.getResources().getDimension(R.dimen.card_radius));

        textView = (TextView) cardView.getChildAt(0);
    }
}
