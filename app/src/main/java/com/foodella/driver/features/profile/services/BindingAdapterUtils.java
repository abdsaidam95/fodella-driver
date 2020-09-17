package com.foodella.driver.features.profile.services;

import android.graphics.Color;
import android.view.View;

import androidx.databinding.BindingAdapter;

import com.foodella.driver.R;
import com.foodella.driver.bottomSheets.selectMultiCity.model.City;
import com.foodella.driver.utils.AppUtil;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.shape.ShapeAppearanceModel;

import java.util.ArrayList;

public class BindingAdapterUtils {

    @BindingAdapter({"cities", "onClickChipListener"})
    public static void setCities(ChipGroup view, ArrayList<City> cities, View.OnClickListener onClickChipListener) {
        view.removeAllViews();
        for (int i = 0; i < cities.size(); i++) {
            Chip chip = new Chip(view.getContext());
            chip.setTag(i);
            chip.setText(cities.get(i).getCityName());
            chip.setTextColor(Color.WHITE);
            chip.setShapeAppearanceModel(ShapeAppearanceModel.builder()
                    .setAllCornerSizes(AppUtil.convertDpToPixel(view.getContext(), 8f))
                    .build());
            chip.setChipBackgroundColorResource(R.color.colorDarkGray);
            chip.setCloseIconVisible(true);
            chip.setCloseIconResource(R.drawable.ic_close);
            chip.setCheckable(false);
            chip.setClickable(false);
            chip.setOnCloseIconClickListener(onClickChipListener);
            view.addView(chip);
        }
    }

}

