package com.foodella.driver.features.profile.info;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.model.Image;
import com.foodella.driver.base.BaseFragment;
import com.foodella.driver.bottomSheets.pickImage.PickImageBottomSheet;
import com.foodella.driver.bottomSheets.selectCity.SelectSingleCityBottomSheet;
import com.foodella.driver.bottomSheets.selectMultiCity.model.City;
import com.foodella.driver.databinding.FragmentInfoBinding;
import com.foodella.driver.utils.AppAction;
import com.foodella.driver.utils.eventBusModel.ActionEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.jetbrains.annotations.NotNull;

import java.util.Calendar;

import static com.foodella.driver.utils.AppAction.ACTION_GET_CITY;
import static com.foodella.driver.utils.AppAction.ACTION_GO_CAMERA;
import static com.foodella.driver.utils.AppAction.ACTION_SHOW_BOTOOM_SHEET_CITY;
import static com.foodella.driver.utils.AppAction.ACTION_SHoW_BOTOOM_SHEET_IMGE;
import static com.foodella.driver.utils.AppAction.SHOW_DIALOG_DATE;

public class InfoFragment extends BaseFragment {

    private FragmentInfoBinding binding;
    private InfoViewModel viewModel;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentInfoBinding.inflate(inflater, container, false);
        ViewModelProvider.Factory factory = new ViewModelProvider.NewInstanceFactory();
        viewModel = new ViewModelProvider(requireActivity(), factory).get(InfoViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.doAction.observe(this, appAction -> {
            if (appAction == ACTION_SHoW_BOTOOM_SHEET_IMGE) {
                PickImageBottomSheet pickImageBottomSheet = new PickImageBottomSheet();
                pickImageBottomSheet.show(getChildFragmentManager(), "exampleBottomSheetImge");
            } else if (appAction == ACTION_SHOW_BOTOOM_SHEET_CITY) {
                SelectSingleCityBottomSheet cityBottomSheet = new SelectSingleCityBottomSheet();
                cityBottomSheet.show(getChildFragmentManager(), "exampleBottomSheetCity");

            }
            else if(appAction == SHOW_DIALOG_DATE){
                new DatePickerDialog(getContext(), viewModel.date, viewModel.calendar
                        .get(Calendar.YEAR), viewModel.calendar.get(Calendar.MONTH),
                        viewModel.calendar.get(Calendar.DAY_OF_MONTH)).show();
            }

        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            Image image = ImagePicker.getFirstImageOrNull(data);
            if (image != null) {
                viewModel.onSelectedImage(image);
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
        } else if (actionEvent.getActions() == ACTION_GET_CITY) {
            City city = (City) actionEvent.getData();
            viewModel.city.setValue(city.getCityName());
        }
    }


}