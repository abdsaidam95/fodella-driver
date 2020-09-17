package com.foodella.driver.features.main.services;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterUtil {
    @BindingAdapter("Adapter")
    public static void setPhotosAdapter(RecyclerView view, RecyclerView.Adapter adapter) {
        view.setAdapter(adapter);

    }
}
