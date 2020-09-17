package com.foodella.driver.features.addCar.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class AddCarViewPagerAdapter extends FragmentStateAdapter {

    private FragmentManager fragmentManager;
    private ArrayList<Fragment> arrayList = new ArrayList<>();

    public AddCarViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public AddCarViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, ArrayList<Fragment> arrayList) {
        super(fragmentActivity);
        this.arrayList = arrayList;
    }

    public AddCarViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, @NonNull FragmentManager fragmentManager, ArrayList<Fragment> arrayList) {
        super(fragmentActivity);
        this.arrayList = arrayList;
        this.fragmentManager = fragmentManager;
    }

    public AddCarViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, @NonNull FragmentActivity fragmentActivity, ArrayList<Fragment> arrayList) {
        super(fragmentManager, lifecycle);
        this.arrayList = arrayList;


    }

    public void addFragment(Fragment fragment) {
        arrayList.add(fragment);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return arrayList.get(position);
    }
}



