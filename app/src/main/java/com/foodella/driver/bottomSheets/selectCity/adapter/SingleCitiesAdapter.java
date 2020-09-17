package com.foodella.driver.bottomSheets.selectCity.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.foodella.driver.bottomSheets.selectMultiCity.model.City;
import com.foodella.driver.databinding.ItemSellectCityBinding;

import java.util.ArrayList;

public class SingleCitiesAdapter extends RecyclerView.Adapter<SingleCitiesAdapter.ViewHolder> {

    private ArrayList<City> items = new ArrayList<>();
    public int selectedPositions = -1;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemSellectCityBinding itemBinding = ItemSellectCityBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setData(ArrayList<City> data) {
        items.clear();
        items.addAll(data);
        notifyDataSetChanged();
    }

    public void appendData(ArrayList<City> data) {
        items.addAll(data);
        notifyDataSetChanged();
    }

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPositions = selectedPosition;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ItemSellectCityBinding itemBinding;

        public ViewHolder(@NonNull ItemSellectCityBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }

        public void bind(City modelCity) {
            SingleCityItemViewModel vm = new SingleCityItemViewModel(modelCity, getAdapterPosition());
            vm.onStart(selectedPositions);
            itemBinding.setViewModel(vm);
            itemBinding.executePendingBindings();
        }
    }
}

