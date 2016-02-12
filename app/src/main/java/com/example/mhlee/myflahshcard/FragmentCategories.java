package com.example.mhlee.myflahshcard;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.view.CardListView;

public class FragmentCategories extends DefaultFragment {

    /**
     * Declare the Instance variables that will be used by this fragment
     */
    private List<Card> mCardsList;
    private CardListView mCategoriesList;
    private CardArrayAdapter mCardAdapter;
    private FloatingActionButton buttonFab;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.setHasOptionsMenu(true); // We use this so we can have specific ActionBar actions/icons for this fragment
        // We return the inflated view to be used by onViewCreated.
        // The first argument is the XML layout to be inflated
        // The second argument is the root to which this layout is being attached.
        // The third argument we specify if we want the fragment to be attached to it's root.



        return inflater.inflate(R.layout.fragment_categories, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        // Initialize the FloatingActionButton and set it's colors
//        buttonFab = (FloatingActionButton) view.findViewById(R.id.addNewCategory);
//        buttonFab.setColor(getResources().getColor(R.color.action_bar_color));
//        buttonFab.setTextColor(getResources().getColor(R.color.action_bar_text_color));

        android.support.design.widget.FloatingActionButton fab = (android.support.design.widget.FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // We initialize the CardsList and add some Dummy Data for now.
        // We will come back to this point to add our Custom Card matching our App UI as well with real data from a Database.
        mCategoriesList = (CardListView) view.findViewById(R.id.categoriesList);
        mCardsList = new ArrayList<Card>();
        for (int i = 0; i < 10; i++) {
            Card card = new Card(getActivity());
            card.setTitle("Category " + i);
            mCardsList.add(card);
        }
        mCardAdapter = new CardArrayAdapter(getActivity(), mCardsList);
        mCardAdapter.setEnableUndo(true);
        mCategoriesList.setAdapter(mCardAdapter);
    }
}