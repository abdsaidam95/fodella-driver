package com.foodella.driver.features.main.payments.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.foodella.driver.databinding.ItemPaymentBinding;

import java.util.ArrayList;

public class PaymentsAdapter extends RecyclerView.Adapter<PaymentsAdapter.ViewHolder> {
    private ArrayList<Payment> items = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemPaymentBinding itemBinding = ItemPaymentBinding.inflate(layoutInflater, parent, false);
        return new PaymentsAdapter.ViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(items.get(position)); }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public void setData(ArrayList<Payment> data) {
        items.clear();
        items.addAll(data);
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ItemPaymentBinding itemBinding;

        public ViewHolder(@NonNull ItemPaymentBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }

        public void bind(Payment modelPayment) {
            PaymentItemViewModel adapterPaymentViewModel = new PaymentItemViewModel(modelPayment);
            adapterPaymentViewModel.onStart();
            itemBinding.setViewModel(adapterPaymentViewModel);
            itemBinding.executePendingBindings();
        }
    }
}
