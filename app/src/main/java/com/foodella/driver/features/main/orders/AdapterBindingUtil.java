package com.foodella.driver.features.main.orders;

import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RatingBar;

import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.InverseBindingMethod;
import androidx.databinding.InverseBindingMethods;
import androidx.recyclerview.widget.RecyclerView;

import com.foodella.driver.R;
import com.foodella.driver.features.main.orders.adapter.OrdersAdapter;
import com.foodella.driver.features.main.payments.adapter.PaymentsAdapter;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;

@InverseBindingMethods(value = {@InverseBindingMethod(type = MaterialButton.class, attribute = "android:checked")})
public class AdapterBindingUtil {
    @BindingAdapter("showPopup")
    public static void showPopup(View view, boolean isShowPopup) {

        if (!isShowPopup) return;

        View layout = View.inflate(view.getContext(), R.layout.popup_window, null);
        PopupWindow popup = new PopupWindow(layout);
        popup.setContentView(layout);
        // Set content width and height
        popup.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popup.setWidth(400);
        // Closes the popup window when touch outside of it - when looses focus
        popup.setOutsideTouchable(true);
        popup.setFocusable(true);
        popup.setBackgroundDrawable(new BitmapDrawable());
        popup.showAsDropDown(view, 300, -500);
        // Show anchored to button
        popup.showAsDropDown(view);
    }

    @BindingAdapter("buttonBackground")
    public static void setCarAdapter(Button view, int background) {
        view.setBackgroundColor(background);

    }

    @BindingAdapter("DataAdapter")
    public static void setDataAdapter(RecyclerView view, OrdersAdapter adapter) {
        view.setAdapter(adapter);

    }

    @BindingAdapter("VisabilityButton")
    public static void VisabilityButton(MaterialButton view, int visabitity) {
        view.setVisibility(visabitity);

    }

    @BindingAdapter("VisabilityRatingBar")
    public static void VisabilityRating(RatingBar view, int visabitity) {
        view.setVisibility(visabitity);

    }

    @BindingAdapter("DataAdapterss")
    public static void setDataAdapterPayment(RecyclerView view, PaymentsAdapter adapter) {
        view.setAdapter(adapter);

    }

    @BindingAdapter("ClickButton")
    public static void cleakButton(Button view, View.OnClickListener clickListener) {
        view.setOnClickListener(clickListener);

    }

    @BindingAdapter({"onToggleButtonClick"})
    public static void onToggleButtonClick(MaterialButtonToggleGroup button, MaterialButtonToggleGroup.OnButtonCheckedListener listener) {
        button.addOnButtonCheckedListener(listener);
    }

    @BindingAdapter({"checkedClick"})
    public static void setCheckedButton(MaterialButtonToggleGroup button, int id) {
        button.check(id);
    }

    @BindingAdapter(value = {"android:checkedAttrChanged"}, requireAll = false)
    public static void setListeners(MaterialButton view, final InverseBindingListener attrChange) {
        if (attrChange != null) {
            view.clearOnCheckedChangeListeners();
            view.addOnCheckedChangeListener(new MaterialButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(MaterialButton buttonView, boolean isChecked) {
                    attrChange.onChange();
                }
            });
        }
    }

    @BindingAdapter("text")
    public static void changeTextButton(MaterialButton view, int text) {
        view.setText(text);
    }

}
