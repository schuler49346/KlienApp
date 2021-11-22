package com.example.klienapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter{

    private Context mContext;
    private List<Users> mUsers;

    public UserAdapter(Context context, List<Users> users){
        this.mContext = context;
        this.mUsers = users;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.users_single_layout, parent, false);
        return new UserAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Users user = mUsers.get(position);
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView Username;
        public TextView Email;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Username = itemView.findViewById(R.id.user_single_name);
            Email = itemView.findViewById(R.id.user_single_email);
        }
    }
}
