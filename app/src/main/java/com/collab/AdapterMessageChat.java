package com.collab;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdapterMessageChat extends RecyclerView.Adapter<MessageHolder> {
    private List<Message> messageList = new ArrayList<>();
    private Context context;

    public AdapterMessageChat(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MessageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
