package com.foodella.driver.features.main.chats.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.collection.ArrayMap;
import androidx.recyclerview.widget.RecyclerView;

import com.foodella.driver.databinding.ItemChatBinding;
import com.foodella.driver.databinding.ItemMenuBinding;
import com.foodella.driver.features.main.nav.Menu;

import java.util.ArrayList;

public class ChatsAdapter extends RecyclerView.Adapter<ChatsAdapter.ViewHolder> {

    private ArrayList<Chat> items = new ArrayList<>();
    private ArrayMap<Integer, Boolean> selectedPositions = new ArrayMap<>();

    public ChatsAdapter() {
    }

    @NonNull
    @Override
    public ChatsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemChatBinding itemBinding = ItemChatBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    public void setData(ArrayList<Chat> data) {
        items.clear();
        items.addAll(data);
        notifyDataSetChanged();
    }

    public void appendData(ArrayList<Chat> data) {
        items.addAll(data);
        notifyDataSetChanged();
    }

    public void setSelectedPosition(int selectedPosition) {
        if (selectedPositions.containsKey(selectedPosition))
            selectedPositions.remove(selectedPosition);
        else
            this.selectedPositions.put(selectedPosition, true);

        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private ItemChatBinding itemBinding;

        public ViewHolder(@NonNull ItemChatBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }

        public void bind(Chat chat) {
            ChatItemViewModel vm = new ChatItemViewModel(chat);
            vm.onStart();
            itemBinding.setViewModel(vm);
            itemBinding.executePendingBindings();
        }
    }

}
