package com.foodella.driver.bottomSheets.vehicleModel.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.foodella.driver.databinding.ItemVehicleModelBinding;
import com.foodella.driver.features.main.cars.adapter.Vehicle;

import java.util.ArrayList;

public class VehiclesAdapter extends RecyclerView.Adapter<VehiclesAdapter.ViewHolder> {

    private ArrayList<Vehicle> items = new ArrayList<>();
    public int selectedPositions = -1;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemVehicleModelBinding itemBinding = ItemVehicleModelBinding.inflate(layoutInflater, parent, false);
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

    public void setData(ArrayList<Vehicle> data) {
         items.clear();
        items.addAll(data);
        notifyDataSetChanged();
    }

    public void appendData(ArrayList<Vehicle> data) {
        items.addAll(data);
        notifyDataSetChanged();
    }

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPositions = selectedPosition;
            notifyDataSetChanged();
    }

        class ViewHolder extends RecyclerView.ViewHolder {

            private ItemVehicleModelBinding itemBinding;

            public ViewHolder(@NonNull ItemVehicleModelBinding itemBinding) {
                super(itemBinding.getRoot());
                this.itemBinding = itemBinding;
            }

            public void bind(Vehicle modelVehicle) {
                VehicleItemViewModel vm = new VehicleItemViewModel(modelVehicle, getAdapterPosition());
                vm.onStart(selectedPositions);
                itemBinding.setViewModel(vm);
                itemBinding.executePendingBindings();

            }
        }
    }

