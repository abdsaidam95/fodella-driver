package com.foodella.driver.features.paymentMethods.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.foodella.driver.databinding.ItemPaymentMethodBinding;

import java.util.ArrayList;

public class PaymentMethodsAdapter extends RecyclerView.Adapter<PaymentMethodsAdapter.ViewHolder> {

    private ArrayList<PaymentMethod> items = new ArrayList<>();
    private int selectedPosition = -1;

    public PaymentMethodsAdapter() {
    }

    @NonNull
    @Override
    public PaymentMethodsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemPaymentMethodBinding itemBinding = ItemPaymentMethodBinding.inflate(layoutInflater, parent, false);
        return new PaymentMethodsAdapter.ViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentMethodsAdapter.ViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    public void setData(ArrayList<PaymentMethod> data) {
        items.clear();
        items.addAll(data);
        notifyDataSetChanged();
    }

    public void appendData(ArrayList<PaymentMethod> data) {
        items.clear();
        items.addAll(data);
        notifyDataSetChanged();
    }

    public void removeItem(PaymentMethod data) {
        items.remove(data);
        notifyDataSetChanged();
    }

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ItemPaymentMethodBinding itemBinding;

        public ViewHolder(@NonNull ItemPaymentMethodBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }

        public void bind(PaymentMethod paymentMethod) {
            PaymentMethodItemViewModel vm = new PaymentMethodItemViewModel(paymentMethod, getAdapterPosition());
            vm.onStart(selectedPosition);
            itemBinding.setViewModel(vm);
            itemBinding.executePendingBindings();
        }
    }
}
