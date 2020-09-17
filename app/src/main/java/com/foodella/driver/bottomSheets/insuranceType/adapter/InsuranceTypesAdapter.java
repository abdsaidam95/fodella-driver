package com.foodella.driver.bottomSheets.insuranceType.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.foodella.driver.databinding.ItemInsuranceTypeBinding;
import com.foodella.driver.databinding.ItemVehicleModelBinding;
import com.foodella.driver.features.main.cars.adapter.Vehicle;

import java.util.ArrayList;

public class InsuranceTypesAdapter extends RecyclerView.Adapter<InsuranceTypesAdapter.ViewHolder> {

    private ArrayList<InsuranceType> items = new ArrayList<>();
    public int selectedPositions = -1;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemInsuranceTypeBinding itemBinding = ItemInsuranceTypeBinding.inflate(layoutInflater, parent, false);
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

    public void setData(ArrayList<InsuranceType> data) {
         items.clear();
        items.addAll(data);
        notifyDataSetChanged();
    }

    public void appendData(ArrayList<InsuranceType> data) {
        items.addAll(data);
        notifyDataSetChanged();
    }

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPositions = selectedPosition;
            notifyDataSetChanged();
    }

        class ViewHolder extends RecyclerView.ViewHolder {

            private ItemInsuranceTypeBinding itemBinding;

            public ViewHolder(@NonNull ItemInsuranceTypeBinding itemBinding) {
                super(itemBinding.getRoot());
                this.itemBinding = itemBinding;
            }

            public void bind(InsuranceType insuranceType) {
                InsuranceItemViewModel vm = new InsuranceItemViewModel(insuranceType, getAdapterPosition());
                vm.onStart(selectedPositions);
                itemBinding.setViewModel(vm);
                itemBinding.executePendingBindings();

            }
        }
    }

