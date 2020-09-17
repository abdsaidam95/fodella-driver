package com.foodella.driver.features.profile.documents;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.model.Image;
import com.foodella.driver.base.BaseFragment;
import com.foodella.driver.bottomSheets.pickImage.PickImageBottomSheet;
import com.foodella.driver.bottomSheets.selectCity.SelectSingleCityBottomSheet;
import com.foodella.driver.bottomSheets.selectMultiCity.model.City;
import com.foodella.driver.databinding.FragmentAddDataBinding;
import com.foodella.driver.utils.AppAction;
import com.foodella.driver.utils.eventBusModel.ActionEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import static com.foodella.driver.utils.AppAction.ACTION_GET_CITY;
import static com.foodella.driver.utils.AppAction.ACTION_GO_CAMERA;
import static com.foodella.driver.utils.AppAction.ACTION_GO_GALLERY;
import static com.foodella.driver.utils.AppAction.ACTION_SHOW_BOTOOM_SHEET_CITY;
import static com.foodella.driver.utils.AppAction.ACTION_SHoW_BOTOOM_SHEET_IMGE;
import static com.foodella.driver.utils.AppAction.SHOW_DIALOG_DATE;

public class AddDataFragment extends BaseFragment {

    FragmentAddDataBinding binding;
    AddDataViewModel addDataViewModel;

    public AddDataFragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAddDataBinding.inflate(inflater, container, false);
        ViewModelProvider.Factory factory = new ViewModelProvider.NewInstanceFactory();
        addDataViewModel = new ViewModelProvider(requireActivity(), factory).get(AddDataViewModel.class);
        binding.setViewModel(addDataViewModel);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addDataViewModel.doAction.observe(this, appAction -> {
            if (appAction == ACTION_GO_GALLERY) {
                PickImageBottomSheet pickImageBottomSheet = new PickImageBottomSheet();
                pickImageBottomSheet.show(getChildFragmentManager(), "exampleBottomSheetImge");
            }

        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            Image image = ImagePicker.getFirstImageOrNull(data);
            if (image != null) {
                addDataViewModel.onSelectedImage(image);
            }
        } else {
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe
    public void onActionEvent(ActionEvent actionEvent) {
        if (actionEvent.getActions() == AppAction.ACTION_GO_GALLERY) {
            requestPermissions();
        } else if (actionEvent.getActions() == ACTION_GO_CAMERA) {
            requestCameraPermissions();
        }
    }
}