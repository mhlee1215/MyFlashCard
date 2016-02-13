package com.example.mhlee.myflahshcard.cards;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mhlee.myflahshcard.cardStructures.Category;
import com.example.mhlee.myflahshcard.MainActivity;
import com.example.mhlee.myflahshcard.database.MyDbHelper;
import com.example.mhlee.myflahshcard.R;

import it.gmariotti.cardslib.library.internal.Card;

// Inside CategoryCard.java
public class CategoryCard extends Card implements Card.OnSwipeListener, Card.OnCardClickListener, Card.OnUndoSwipeListListener {

    private Category category;
    private MyDbHelper helper;

    public CategoryCard(Context context) {
        super(context, R.layout.card_category);
        helper = new MyDbHelper(context);
        this.setSwipeable(true); 
        this.setOnClickListener(this);
        this.setOnSwipeListener(this);
        this.setOnUndoSwipeListListener(this);
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {
        super.setupInnerViewElements(parent, view);

        TextView categoryTitle = (TextView) view.findViewById(R.id.titleCategory);

        if(category != null) {
            categoryTitle.setText(category.getTitle());
        }
    }

    @Override
    public void onClick(Card card, View view) {
        Bundle bundle = new Bundle();
        bundle.putLong(MyDbHelper._ID, category.getId());
        bundle.putString(MyDbHelper.TITLE, category.getTitle());

        ((MainActivity) getContext()).displayView(MainActivity.DECKS_FRAG, bundle);
    }

    @Override
    public void onSwipe(Card card) {
        helper.deleteFromDB(category.getId(), MyDbHelper.CATEGORIES_TABLE);
    }

    @Override
    public void onUndoSwipe(Card card) {
        helper.undoCategory(category);
    }
}