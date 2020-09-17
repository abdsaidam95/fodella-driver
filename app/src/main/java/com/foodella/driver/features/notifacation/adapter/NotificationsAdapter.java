package com.foodella.driver.features.notifacation.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.foodella.driver.databinding.ItemNotificationsBinding;

import java.util.ArrayList;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.ViewHolder> {
    private ArrayList<Notification> items = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemNotificationsBinding itemBinding = ItemNotificationsBinding.inflate(layoutInflater, parent, false);
        return new NotificationsAdapter.ViewHolder(itemBinding);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(items.get(position));

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public void setData(ArrayList<Notification> data) {
        items.clear();
        items.addAll(data);
        notifyDataSetChanged();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private ItemNotificationsBinding itemBinding;

        public ViewHolder(@NonNull ItemNotificationsBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }

        public void bind(Notification notifications) {
            NotificationsItemViewModel notificationsItemViewModel = new NotificationsItemViewModel(notifications);
            notificationsItemViewModel.onStart();
            itemBinding.setViewModel(notificationsItemViewModel);
            itemBinding.executePendingBindings();
        }
    }
}
