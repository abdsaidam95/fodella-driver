package com.foodella.driver.bottomSheets.selectMultiCity.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.collection.ArrayMap;
import androidx.recyclerview.widget.RecyclerView;

import com.foodella.driver.bottomSheets.selectMultiCity.model.City;
import com.foodella.driver.databinding.ItemCityBinding;

import java.util.ArrayList;
import java.util.Set;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {

    private ArrayList<City> items = new ArrayList<>();
    private ArrayMap<Integer, Boolean> selectedPositions = new ArrayMap<>();

    public CityAdapter() {
    }

    @NonNull
    @Override
    public CityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemCityBinding itemBinding = ItemCityBinding.inflate(layoutInflater, parent, false);
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
        if (selectedPositions.containsKey(selectedPosition))
            selectedPositions.remove(selectedPosition);
        else
            this.selectedPositions.put(selectedPosition, true);

        notifyDataSetChanged();
    }

    public ArrayList<City> getSelectedCities() {
        ArrayList<City> selectedCities = new ArrayList<>();
        Set<Integer> set = selectedPositions.keySet();

        for (Integer item : set) {
            selectedCities.add(items.get(item));
        }
        return selectedCities;
    }

    public void setSelectedCities(ArrayList<City> selectedCities) {
        selectedPositions.clear();
        for (int i = 0; i < selectedCities.size(); i++) {
            selectedPositions.put(i, true);
        }
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ItemCityBinding itemBinding;

        public ViewHolder(@NonNull ItemCityBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }

        public void bind(City city) {
            CityItemViewModel vm = new CityItemViewModel(city, getLayoutPosition());
            vm.onStart(selectedPositions);
            itemBinding.setViewModel(vm);
            itemBinding.executePendingBindings();
        }
    }

}
