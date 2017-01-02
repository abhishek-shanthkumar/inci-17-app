package com.android.incidentapp;

/**
 * Created by RK on 05/11/2016.
 */
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

import com.android.incidentapp.adapter.LeaderboardAdapter;
import com.android.incidentapp.adapter.QuizElementsAdapter;
import com.android.incidentapp.auxiliary.BottomFadeEdgeRV;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.sothree.slidinguppanel.SlidingUpPanelLayout.PanelSlideListener;
import com.sothree.slidinguppanel.SlidingUpPanelLayout.PanelState;

public class HomeActivity extends ActionBarActivity {
    private static final String TAG = "HomeActivity";
    private SlidingUpPanelLayout mLayout;
    BottomFadeEdgeRV mLeaderboard;
    RecyclerView quizElements;
    LinearLayoutManager mLayoutManager, mLayoutManagerLeaderboard;
    QuizElementsAdapter mQuizElementsAdapter;
    LeaderboardAdapter mLeaderboardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);
        quizElements = (RecyclerView) findViewById(R.id.rv_quiz);
        mLeaderboard = (BottomFadeEdgeRV) findViewById(R.id.rv_leaderboard);

        mLayout.addPanelSlideListener(new PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                Log.i(TAG, "onPanelSlide, offset " + slideOffset);
            }
            @Override
            public void onPanelStateChanged(View panel, PanelState previousState, PanelState newState) {
                Log.i(TAG, "onPanelStateChanged " + newState);
            }
        });
        mLayout.setFadeOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mLayout.setPanelState(PanelState.COLLAPSED);
            }
        });

        //Setting the recyclerView
        mLayoutManager = new LinearLayoutManager(this);
        mQuizElementsAdapter = new QuizElementsAdapter(getApplicationContext());
        quizElements.setLayoutManager(mLayoutManager);
        quizElements.setItemAnimator(new DefaultItemAnimator());
        quizElements.setAdapter(mQuizElementsAdapter);

        mLayoutManagerLeaderboard = new LinearLayoutManager(this);
        mLeaderboardAdapter = new LeaderboardAdapter(getApplicationContext());
        mLeaderboard.setLayoutManager(mLayoutManagerLeaderboard);
        mLeaderboard.setItemAnimator(new DefaultItemAnimator());
        mLeaderboard.setAdapter(mLeaderboardAdapter);

    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        if (mLayout != null &&
                (mLayout.getPanelState() == PanelState.EXPANDED || mLayout.getPanelState() == PanelState.ANCHORED)) {
            mLayout.setPanelState(PanelState.COLLAPSED);
        } else {
            super.onBackPressed();
        }
    }
}
