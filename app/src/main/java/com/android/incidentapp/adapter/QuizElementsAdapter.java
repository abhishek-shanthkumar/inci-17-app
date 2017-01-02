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
public class QuizElementsAdapter extends
        RecyclerView.Adapter<QuizElementsAdapter.QuizElementsViewHolder> {

    Context context;

    String[] quizElements = {
            "QUESTION",
            "LEADERBOARD",
            "SPINWHEEL"
    };

    public QuizElementsAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return quizElements.length;
    }

    @Override
    public void onBindViewHolder(QuizElementsViewHolder quizElementsViewHolder, int i) {

        //Binding element_titles
        quizElementsViewHolder.elementTitle.setText(quizElements[i]);
        
    }

    @Override
    public QuizElementsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.card_layout_element, viewGroup, false);
        return new QuizElementsViewHolder(itemView);
    }

    public class QuizElementsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        CardView cardType;
        TextView elementTitle;

        public QuizElementsViewHolder(View v) {
            super(v);
            cardType = (CardView) v.findViewById (R.id.cv_element);
            elementTitle = (TextView) v.findViewById (R.id.tv_element);

            cardType.setOnClickListener(this);
        }

        @Override
        public void onClick(final View view) {
            int itemPosition = getAdapterPosition();
            Intent intent=null;

            if(itemPosition==0)
                intent = new Intent(view.getContext(),QuestionActivity.class);
            else if(itemPosition==1)
                intent = new Intent(view.getContext(),LeaderboardActivity.class);
            else if (itemPosition==2)
                intent = new Intent(view.getContext(),SpinActivity.class);
            else
                Toast.makeText(view.getContext(), "Sorry, we have encountered a problem.", Toast.LENGTH_LONG).show();

            if(intent!=null)
                view.getContext().startActivity(intent);
        }
    }
}
