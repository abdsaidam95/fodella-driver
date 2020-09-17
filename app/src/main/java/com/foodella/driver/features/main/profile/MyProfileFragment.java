package com.foodella.driver.features.main.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.foodella.driver.base.BaseActivity;
import com.foodella.driver.base.BaseFragment;
import com.foodella.driver.bottomSheets.pickImage.PickImageBottomSheet;
import com.foodella.driver.bottomSheets.selectCity.SelectSingleCityBottomSheet;
import com.foodella.driver.databinding.FragmentMyProfileBinding;
import com.foodella.driver.features.addCar.AddCarActivity;
import com.foodella.driver.features.addService.AddServiceActivity;
import com.foodella.driver.features.editProfile.EditProfileActivity;
import com.foodella.driver.features.editProfile.Profile;
import com.foodella.driver.features.main.home.HomeFragment;
import com.foodella.driver.features.main.services.Services;
import com.foodella.driver.utils.AppAction;

import static com.foodella.driver.utils.AppAction.ACTION_SHOW_BOTOOM_SHEET_CITY;
import static com.foodella.driver.utils.AppAction.ACTION_SHoW_BOTOOM_SHEET_IMGE;
import static com.foodella.driver.utils.AppAction.GO_TO_EDIT_PROFILE;

public class MyProfileFragment extends BaseFragment {

    private FragmentMyProfileBinding binding;
    private MyProfileViewModel viewModel;

    public static MyProfileFragment newInstance() {
        return new MyProfileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMyProfileBinding.inflate(inflater, container, false);
        ViewModelProvider.Factory factory = new ViewModelProvider.NewInstanceFactory();
        viewModel = new ViewModelProvider(requireActivity(), factory).get(MyProfileViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.doAction.observe(this, appAction -> {
            if (appAction == GO_TO_EDIT_PROFILE) {
                Intent intent = new Intent(getActivity(), EditProfileActivity.class);
                intent.putExtra("profileNotEdit",viewModel.profile);
                startActivityForResult(intent , 200);
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if( requestCode == 200 && data!=null) {
            Profile profile=(Profile) data.getExtras().getSerializable("profile");
            viewModel.setProfileData(profile);
        }


    }

}