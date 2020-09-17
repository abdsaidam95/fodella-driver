package com.foodella.driver.features.onBoarding;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.foodella.driver.R;
import com.foodella.driver.bottomSheets.selectCity.adapter.SingleCitiesAdapter;
import com.foodella.driver.features.onBoarding.fragment.OnBoarding;
import com.foodella.driver.features.onBoarding.fragment.OnBoardingPagerAdapter;
import com.foodella.driver.features.profile.SliderPagerAdapter;
import com.foodella.driver.features.profile.cars.CarsListFragment;
import com.foodella.driver.features.profile.documents.AddDataFragment;
import com.foodella.driver.features.profile.info.InfoFragment;
import com.foodella.driver.features.profile.services.ServicesFragment;
import com.foodella.driver.features.main.cars.adapter.VehiclesAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class AdapterUtil {

    @BindingAdapter({"fragmentManager", "viewPagerList"})
    public static void setFragmentViewPager(ViewPager view, FragmentManager fragmentManager, ArrayList<OnBoarding> onBoardingArrayList) {
        OnBoardingPagerAdapter adapter = new OnBoardingPagerAdapter(fragmentManager, onBoardingArrayList);
        view.setAdapter(adapter);
    }

    @BindingAdapter("setTab")
    public static void setViewPagerToTab(TabLayout view, ViewPager viewPager) {
        view.setupWithViewPager(viewPager);
    }

    @BindingAdapter("setTab2")
    public static void setViewPager2ToTab(TabLayout view, ViewPager2 viewPager) {
        // view.setupWithViewPager(viewPager);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(view, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {

            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    @BindingAdapter("setPager2")
    public static void setViewPager2(ViewPager view, FragmentManager fragmentManager) {
        SliderPagerAdapter adapter = new SliderPagerAdapter(fragmentManager);
        adapter.addFragment(new InfoFragment(), "");
        adapter.addFragment(new AddDataFragment(), "");
        adapter.addFragment(new ServicesFragment(), "");
        adapter.addFragment(new CarsListFragment(), "");
        view.setAdapter(adapter);
        adapter.notifyDataSetChanged();
//        view.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return true;
//            }
//        });
    }

    @BindingAdapter("actionText")
    public static void getActionText(TextView view, final int position) {
        view.setVisibility(View.VISIBLE);
        if (position == 2) {
            view.setVisibility(View.GONE);
        }
    }

    @BindingAdapter("viewPageListener")
    public static void setViewPagerListener(ViewPager view, ViewPager.OnPageChangeListener onPageChangeListener) {
        view.addOnPageChangeListener(onPageChangeListener);
    }

    @BindingAdapter("currentPage")
    public static void setCurrentPage(ViewPager view, int currentPage) {
        view.setCurrentItem(currentPage);
    }

    @BindingAdapter("listenerCheakBox")
    public static void setListnerCheakBox(CheckBox view, CheckBox.OnCheckedChangeListener onCheckedChangeListener) {
        view.setOnCheckedChangeListener(onCheckedChangeListener);
    }

    @BindingAdapter("photosAdapter")
    public static void setPhotosAdapter(RecyclerView view, SingleCitiesAdapter adapter) {
        view.setAdapter(adapter);

    }

    @BindingAdapter("CarsAdapter")
    public static void setCarAdapter(RecyclerView view, VehiclesAdapter adapter) {
        view.setAdapter(adapter);

    }

    @BindingAdapter("adapter")
    public static void setAdapter(RecyclerView view, RecyclerView.Adapter adapter) {
        view.setAdapter(adapter);
    }

    @BindingAdapter("CheakedBoxClicked")
    public static void clickCheakBox(CheckBox view, Boolean position) {
        if (position) {
            view.setChecked(false);
        } else {
            view.setChecked(true);
        }
    }

    @BindingAdapter("text")
    public static void setCarAdapter(TextView view, int text) {
        view.setText(R.string.Vehicles);

    }

    @BindingAdapter("changeTextViewColour")
    public static void changeBackgroundTextView(View view, boolean isClickable) {
        if (isClickable) {
            view.setBackgroundResource(R.drawable.rectangular_red_shape);
        } else {
            view.setBackgroundResource(R.drawable.confirm_rectangular_shape);


        }
    }

}
