package com.foodella.driver.features.orderDetails;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.databinding.BindingAdapter;

import com.foodella.driver.R;
import com.foodella.driver.databinding.ViewFieldBinding;

public class FieldView extends ConstraintLayout {

    private AttributeSet attributeSet;
    public ViewFieldBinding mBinding;

    public FieldView(Context context) {
        super(context);
        init();
    }

    public FieldView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.attributeSet = attributeSet;
        init();
        initComponents();
    }

    public void init() {
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(infService);
        assert layoutInflater != null;
        mBinding = ViewFieldBinding.inflate(layoutInflater, this, true);
    }

    public void initComponents() {
        @SuppressLint("Recycle") TypedArray typedArray = getContext().obtainStyledAttributes(attributeSet, R.styleable.FieldView, 0, 0);

        String txtLabel = typedArray.getString(R.styleable.FieldView_fv_txt_label);
        String txtValue = typedArray.getString(R.styleable.FieldView_fv_txt_value);
        int icon = typedArray.getResourceId(R.styleable.FieldView_fv_icon, 0);
        int iconColor = typedArray.getResourceId(R.styleable.FieldView_fv_icon_color, 0);
        int background = typedArray.getResourceId(R.styleable.FieldView_fv_background, 0);
        int textColor = typedArray.getResourceId(R.styleable.FieldView_fv_text_color, 0);
        int textStyle = typedArray.getResourceId(R.styleable.FieldView_fv_text_style, 0);

        setLabel(txtLabel);
        setText(txtValue);
        setIcon(icon, iconColor);
        setBackground(background);
        setColorText(textColor);
        setStyleText(textStyle);

    }

    public void setLabel(String label) {
        mBinding.textViewLabel.setText(label);
    }
    public void setColorText(int color) {
        if (color!=0) {
            mBinding.textViewLabel.setTextColor(this.getResources().getColor(color));
        }


    }
    public void setStyleText(int styleText) {
        if (styleText!=0){
            mBinding.textViewValue.setTypeface(mBinding.textViewValue.getTypeface(), styleText);
        }
    }


    public void setIconLabel(int color) {
        mBinding.textViewLabel.setTextColor(color);
    }

    public void setLabel(int label) {
        mBinding.textViewLabel.setText(label);
    }

    public void setText(String text) {
        mBinding.textViewValue.setText(text);
    }

    public void setText(int text) {
        mBinding.textViewValue.setText(text);
    }

    private void showIcon(boolean isShow) {
        mBinding.imageViewIcon.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    public void setIcon(int icon) {
        setIcon(icon, 0);
    }

    public void setIcon(int icon, int color) {
        if (icon != 0)
            mBinding.imageViewIcon.setImageResource(icon);

        if (color != 0) {
            int newColor = ResourcesCompat.getColor(getResources(), color, null);
            mBinding.imageViewIcon.setImageTintList(ColorStateList.valueOf(newColor));
        }
    }

    public void setBackground(int background) {
        if (background != 0)
            mBinding.getRoot().setBackgroundResource(background);
    }

    /*********** BindingAdapter ***********/

    @BindingAdapter("label")
    public static void setLabelText(FieldView view, String labelText) {
        view.setLabel(labelText);
    }

    @BindingAdapter("label")
    public static void setLabelText(FieldView view, int labelText) {
        view.setLabel(labelText);
    }

    @BindingAdapter("text")
    public static void setText(FieldView view, int value) {
        view.setText(value);
    }   @BindingAdapter("textColor")
    public static void setTextColor(FieldView view, int color) {
        view.setColorText(color);
    }

    @BindingAdapter("text")
    public static void setText(FieldView view, String value) {
        view.setText(value);
    }

    @BindingAdapter(value = {"icon", "iconColor"}, requireAll = false)
    public static void setIcon(FieldView view, int value, int color) {
        view.setIcon(value, color);
    }

    @BindingAdapter(value = {"showIcon"})
    public static void setIcon(FieldView view, boolean isShow) {
        view.showIcon(isShow);
    }

    @BindingAdapter("background")
    public static void setBackground(FieldView view, int value) {
        view.setBackground(value);
    }

}
