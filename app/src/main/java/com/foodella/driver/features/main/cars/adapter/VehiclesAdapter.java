package com.foodella.driver.features.main.cars.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.foodella.driver.databinding.ItemAddCarBinding;

import java.util.ArrayList;

public class VehiclesAdapter extends RecyclerView.Adapter<VehiclesAdapter.ViewHolder> {

    private ArrayList<Vehicle> items = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemAddCarBinding itemBinding = ItemAddCarBinding.inflate(layoutInflater, parent, false);
        return new VehiclesAdapter.ViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(items.get(position));

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setData(ArrayList<Vehicle> data) {
        items.clear();
        items.addAll(data);
        notifyDataSetChanged();
        Log.d("kkuytrfds", "" + items.size());
    }

    public void appendData(ArrayList<Vehicle> data) {
        items.addAll(data);
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ItemAddCarBinding itemBinding;

        public ViewHolder(@NonNull ItemAddCarBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }

        public void bind(Vehicle vehicle) {
            VehicleItemViewModel vm = new VehicleItemViewModel(vehicle);
            vm.onStart();
            itemBinding.setViewModel(vm);
            itemBinding.executePendingBindings();
        }
    }
}
