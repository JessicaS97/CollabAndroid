package com.collab;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SimpleTimeZone;

public class AdapterMessageChat extends RecyclerView.Adapter<MessageHolder> {
    private List<MessageReceive> messageList = new ArrayList<>();
    private Context context;

    public AdapterMessageChat(Context context) {
        this.context = context;
    }

    public void addMessage(MessageReceive message) {
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
        if (messageList.get(position).getMessage_type().equals("2")) {
            holder.getMessagePhoto().setVisibility(View.VISIBLE);
            holder.getMessage().setVisibility(View.VISIBLE);
            Glide.with(context).load(messageList.get(position).getUrlPhoto()).into(holder.getMessagePhoto());
        } else if (messageList.get(position).getMessage_type().equals("1")) {
            holder.getMessagePhoto().setVisibility(View.GONE);
            holder.getMessage().setVisibility(View.VISIBLE);
        }

        Long hourTime = messageList.get(position).getHour();
        Date date = new Date(hourTime);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss a");
        holder.getTime().setText(simpleDateFormat.format(date));
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }
}
