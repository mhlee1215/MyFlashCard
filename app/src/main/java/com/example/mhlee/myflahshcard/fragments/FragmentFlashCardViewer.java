package com.example.mhlee.myflahshcard.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mhlee.myflahshcard.cardStructures.FlashCard;
import com.example.mhlee.myflahshcard.cards.FlashCardViewerCard;
import com.example.mhlee.myflahshcard.database.MyDbHelper;
import com.example.mhlee.myflahshcard.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 9/23/14.
 */
public class FragmentFlashCardViewer extends DefaultFragment implements View.OnClickListener {

    private static final String LOG_TAG = FragmentFlashCardViewer.class.getSimpleName();

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private List<FlashCardViewerCard> mCardsList;
    private MyDbHelper dbHelper;
    private Bundle activeBundle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        return inflater.inflate(R.layout.fragment_flashcard_viewer, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        activeBundle = getArguments();

        if(activeBundle == null) {
            activeBundle = savedInstanceState;
        }

        dbHelper = new MyDbHelper(getActivity());
        mCardsList = new ArrayList<FlashCardViewerCard>();
        List<FlashCard> flashcards = dbHelper.getAllFlashCardsForDeckId(activeBundle.getLong(MyDbHelper._ID));

        for(int i = 0; i < flashcards.size(); i++) {
            FlashCardViewerCard card = new FlashCardViewerCard(getActivity());
            card.setId(String.valueOf(flashcards.get(i).getId()));
            card.setCard(flashcards.get(i));
            mCardsList.add(card);
        }
        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());
        mViewPager = (ViewPager) view.findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setCurrentItem(0);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onClick(View v) {

    }

    /**
     * /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

        private Fragment mFragment;

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            mFragment = new FragmentFlashcard();
            ((FragmentFlashcard) mFragment).setCard(mCardsList.get(position));

            return mFragment;
        }

        @Override
        public int getCount() {
            return mCardsList.size();
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }
    }
}