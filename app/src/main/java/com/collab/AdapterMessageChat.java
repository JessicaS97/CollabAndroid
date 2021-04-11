package com.collab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class AdapterMessageChat extends RecyclerView.Adapter<MessageHolder> {
    private List<Message> messageList = new ArrayList<>();
    private Context context;

    public AdapterMessageChat(Context context) {
        this.context = context;
    }

    public void addMessage(Message message) {
        messageList.add(message);
        notifyItemInserted(messageList.size());
    }

    @NonNull
    @Override
    public MessageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_view_message_chat, parent, false);
        return new MessageHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageHolder holder, int position) {
        holder.getName().setText(messageList.get(position).getName());
        holder.getMessage().setText(messageList.get(position).getMessage());
        holder.getTime().setText(messageList.get(position).getTime());
        if (messageList.get(position).getMessage_type().equals("2")) {
            holder.getMessagePhoto().setVisibility(View.VISIBLE);
            holder.getMessage().setVisibility(View.VISIBLE);
            Glide.with(context).load(messageList.get(position).getUrlPhoto()).into(holder.getMessagePhoto());
        } else if (messageList.get(position).getMessage_type().equals("1")) {
            holder.getMessagePhoto().setVisibility(View.GONE);
            holder.getMessage().setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }
}
