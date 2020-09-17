package com.foodella.driver.features.messages.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.foodella.driver.databinding.ItemMessageBinding;

import java.util.ArrayList;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.ViewHolder> {

    private ArrayList<Message> items = new ArrayList<>();

    public MessagesAdapter() {
    }

    @NonNull
    @Override
    public MessagesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemMessageBinding itemBinding = ItemMessageBinding.inflate(layoutInflater, parent, false);
        return new MessagesAdapter.ViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MessagesAdapter.ViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    public void setData(ArrayList<Message> data) {
        items.clear();
        items.addAll(data);
        notifyDataSetChanged();
    }

    public void appendData(ArrayList<Message> data) {
        items.clear();
        items.addAll(data);
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private ItemMessageBinding itemBinding;

        public ViewHolder(@NonNull ItemMessageBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }

        public void bind(Message message) {
            MessageItemViewModel vm = new MessageItemViewModel(message);
            vm.onStart();
            itemBinding.setViewModel(vm);
            itemBinding.executePendingBindings();
        }
    }
}
