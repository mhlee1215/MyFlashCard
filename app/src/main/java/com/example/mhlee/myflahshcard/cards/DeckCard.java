package com.example.mhlee.myflahshcard.cards;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mhlee.myflahshcard.cardStructures.Deck;
import com.example.mhlee.myflahshcard.MainActivity;
import com.example.mhlee.myflahshcard.database.MyDbHelper;
import com.example.mhlee.myflahshcard.R;

import it.gmariotti.cardslib.library.internal.Card;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 9/22/14.
 */
public class DeckCard extends Card implements Card.OnCardClickListener, Card.OnSwipeListener, Card.OnUndoSwipeListListener {

    private Deck deck;
    private MyDbHelper helper;


    public DeckCard(Context context) {
        super(context, R.layout.card_deck);
        helper = new MyDbHelper(context);
        this.setSwipeable(true);
        this.setOnClickListener(this);
        this.setOnSwipeListener(this);
        this.setOnUndoSwipeListListener(this);
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {
        super.setupInnerViewElements(parent, view);

        TextView deckTitle = (TextView) view.findViewById(R.id.titleDeck);

        if(deck != null) {
            deckTitle.setText(deck.getTitle());
        }
    }

    @Override
    public void onClick(Card card, View view) {
        Bundle b = new Bundle();
        b.putLong(MyDbHelper._ID, deck.getId());
        b.putString(MyDbHelper.TITLE, deck.getTitle());
        ((MainActivity) getContext()).displayView(MainActivity.FLASHCARDS_FRAG, b);
    }

    @Override
    public void onSwipe(Card card) {
        helper.deleteFromDB(deck.getId(), MyDbHelper.DECKS_TABLE);
    }

    @Override
    public void onUndoSwipe(Card card) {
        helper.undoDeck(deck);
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }
}
