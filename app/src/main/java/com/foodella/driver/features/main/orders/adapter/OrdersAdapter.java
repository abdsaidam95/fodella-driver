package com.foodella.driver.features.main.orders.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.foodella.driver.databinding.ItemRecycleOrderBinding;
import com.foodella.driver.utils.OrderStatus;

import java.util.ArrayList;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.ViewHolder> {

    private ArrayList<Order> items = new ArrayList<>();
    private OrderStatus mOrderStatus = OrderStatus.PENDING;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemRecycleOrderBinding itemBinding = ItemRecycleOrderBinding.inflate(layoutInflater, parent, false);
        return new OrdersAdapter.ViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setData(ArrayList<Order> data) {
        items.clear();
        items.addAll(data);
        notifyDataSetChanged();
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        mOrderStatus = orderStatus;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ItemRecycleOrderBinding itemBinding;

        public ViewHolder(@NonNull ItemRecycleOrderBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }

        public void bind(Order modelCity) {
            OrderItemViewModel vm = new OrderItemViewModel(modelCity);
            vm.onStart(mOrderStatus);
            itemBinding.setViewModel(vm);
            itemBinding.executePendingBindings();
        }
    }
}
