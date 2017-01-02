package com.android.incidentapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.incidentapp.LeaderboardActivity;
import com.android.incidentapp.QuestionActivity;
import com.android.incidentapp.R;
import com.android.incidentapp.SpinActivity;

/**
 * Created by RK on 03/11/2016.
 */
public class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardAdapter.LeaderboardViewHolder> {

    Context context;

    public static final int PROFILE = 0;
    public static final int HEADER = 1;

    public LeaderboardAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0)
            return PROFILE;
        else if(position==1)
            return HEADER;
        else
            return 2;
    }

    @Override
    public void onBindViewHolder(LeaderboardViewHolder leaderboardViewHolder, int i) {

        if (leaderboardViewHolder.getItemViewType() == PROFILE) {
            ProfileViewHolder mProfileViewHolder = (ProfileViewHolder) leaderboardViewHolder;
        }
        else if (leaderboardViewHolder.getItemViewType() == HEADER) {
            HeaderViewHolder mHeaderViewHolder = (HeaderViewHolder) leaderboardViewHolder;
        }
        else {
            ElementViewHolder mElementViewholder = (ElementViewHolder) leaderboardViewHolder;
        }

    }

    @Override
    public LeaderboardViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView;
        if (viewType == PROFILE) {
            itemView = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.card_layout_leaderboard_profile, viewGroup, false);

            return new ProfileViewHolder(itemView);
        } else if(viewType == HEADER){
            itemView = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.card_layout_leaderboard_header, viewGroup, false);
            return new HeaderViewHolder(itemView);
        } else {
            itemView = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.card_layout_leaderboard_element, viewGroup, false);
            return new ElementViewHolder(itemView);
        }
    }

    public class LeaderboardViewHolder extends RecyclerView.ViewHolder{

        public LeaderboardViewHolder(View v) {
            super(v);

        }

    }

    public class ProfileViewHolder extends LeaderboardViewHolder{

        CardView cardType;
        TextView elementTitle;

        public ProfileViewHolder(View v) {
            super(v);

        }
    }

    public class HeaderViewHolder extends LeaderboardViewHolder{

        CardView cardType;
        TextView elementTitle;

        public HeaderViewHolder(View v) {
            super(v);

        }
    }

    public class ElementViewHolder extends LeaderboardViewHolder {

        CardView cardType;
        TextView elementTitle;

        public ElementViewHolder(View v) {
            super(v);

        }
    }
}
