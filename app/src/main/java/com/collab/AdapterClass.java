package com.collab;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.GroupsViewHolder> {
    ArrayList<Group> groupList;
    StorageReference storageReference;
    private Context context;

    public AdapterClass(Context context, ArrayList<Group> groupList) {
        this.groupList = groupList;
        this.context = context;
    }

    @NonNull
    @Override
    public GroupsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.group_individual_layout, parent, false);

        return new GroupsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupsViewHolder holder, int position) {
        holder.groupNameRetrieved.setText(groupList.get(position).getGroupName());
        holder.groupSizeRetrieved.setText(groupList.get(position).getGroupSize());

        storageReference = FirebaseStorage.getInstance().getReference()
                .child("profileImages")
                .child(groupList.get(position).getGroupAuthor() + ".jpeg");

        Glide.with(this.context).load(storageReference).into(holder.authorImage);
    }

    @Override
    public int getItemCount() {
        return groupList.size();
    }

    class GroupsViewHolder extends RecyclerView.ViewHolder {
        TextView groupNameRetrieved, groupSizeRetrieved;
        ImageView authorImage;

        public GroupsViewHolder(View view) {
            super(view);
            groupNameRetrieved = view.findViewById(R.id.groupName);
            groupSizeRetrieved = view.findViewById(R.id.membersNumber);
            authorImage = view.findViewById(R.id.profilePic);
        }
    }
}
