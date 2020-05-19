package com.example.topquiz.Controleur;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.topquiz.Modele.User;
import com.example.topquiz.R;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class ScoreListAdapter extends RecyclerView.Adapter<ScoreListAdapter.ViewHolder> {

    private List<User> mUsers;
    public ScoreListAdapter(List<User> listUser) {
        mUsers = listUser;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTextView;
        public TextView scoreTextView;


        public ViewHolder(View itemView) {

            super(itemView);

            nameTextView = itemView.findViewById(R.id.fragment_name);
            scoreTextView = itemView.findViewById(R.id.fragment_score);
        }
    }

    @Override
    public ScoreListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View userView = inflater.inflate(R.layout.fragment_score, parent, false);

        ViewHolder viewHolder = new ViewHolder(userView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ScoreListAdapter.ViewHolder viewHolder, int position) {

        User user = mUsers.get(position);

        TextView nameView = viewHolder.nameTextView;
        nameView.setText(user.getName());

        TextView scoreView = viewHolder.scoreTextView;
        scoreView.setText(user.getmScore() + "");
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }
}
