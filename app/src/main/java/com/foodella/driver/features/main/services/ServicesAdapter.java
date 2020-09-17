package com.foodella.driver.features.main.services;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.foodella.driver.bottomSheets.selectMultiCity.model.City;
import com.foodella.driver.databinding.ItemServiceBinding;
import com.foodella.driver.features.main.payments.adapter.Payment;
import com.foodella.driver.features.main.payments.adapter.PaymentItemViewModel;
import com.foodella.driver.features.main.payments.adapter.PaymentsAdapter;

import java.util.ArrayList;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ViewHolder>{
    private ArrayList<Services> items = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemServiceBinding itemBinding = ItemServiceBinding.inflate(layoutInflater, parent, false);
        return new ServicesAdapter.ViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public void appendData(ArrayList<Services> data) {
        items.clear();
        items.addAll(data);
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        items.remove(position);
        notifyDataSetChanged();
    }

    public void editItem(int position, Services services) {
        items.set(position,services);
        notifyDataSetChanged();

    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private ItemServiceBinding itemBinding;

        public ViewHolder(@NonNull ItemServiceBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }

        public void bind(Services services) {
            ServicesItemViewModel adapterPaymentViewModel = new ServicesItemViewModel(services, getAdapterPosition());
            adapterPaymentViewModel.onStart();
            itemBinding.setViewModel(adapterPaymentViewModel);
            itemBinding.executePendingBindings();
        }
    }
}
