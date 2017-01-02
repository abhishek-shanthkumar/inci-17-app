package com.android.incidentapp;

/**
 * Created by RK on 05/11/2016.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.sothree.slidinguppanel.SlidingUpPanelLayout.PanelSlideListener;
import com.sothree.slidinguppanel.SlidingUpPanelLayout.PanelState;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";
    private SlidingUpPanelLayout mLayout;
    BottomFadeEdgeRV mLeaderboard;
    RecyclerView quizElements;
    LinearLayoutManager mLayoutManager, mLayoutManagerLeaderboard;
    QuizElementsAdapter mQuizElementsAdapter;
    LeaderboardAdapter mLeaderboardAdapter;

    // Firebase variables
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize Firebase Auth
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        if (mFirebaseUser == null) {
            // Not signed in, launch the Sign In activity
            startActivity(new Intent(this, SignInActivity.class));
            finish();
            return;
        } else {
            //mUsername = mFirebaseUser.getDisplayName();
            if (mFirebaseUser.getPhotoUrl() != null) {
                //mPhotoUrl = mFirebaseUser.getPhotoUrl().toString();
            }
        }

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
        mLeaderboardAdapter = new LeaderboardAdapter(getApplicationContext(), mFirebaseUser);
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
