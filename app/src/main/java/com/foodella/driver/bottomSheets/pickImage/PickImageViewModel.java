package com.foodella.driver.bottomSheets.pickImage;

import androidx.lifecycle.MutableLiveData;

import com.foodella.driver.base.BaseViewModel;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import static com.foodella.driver.utils.AppAction.ACTION_DISMESS_DIALOG;
import static com.foodella.driver.utils.AppAction.ACTION_GO_CAMERA;
import static com.foodella.driver.utils.AppAction.ACTION_GO_GALLERY;

public class PickImageViewModel extends BaseViewModel {



    public PickImageViewModel() {
        bottomSheetState.setValue(BottomSheetBehavior.STATE_EXPANDED);
    }

    public void onStart() {
    }

    public void onClickGallery() {
        doAction.setValue(ACTION_GO_GALLERY);
    }

    public void onClickCamera() {
        doAction.setValue(ACTION_GO_CAMERA);
    }

    public void onClickDismissDialog() {
        doAction.setValue(ACTION_DISMESS_DIALOG);
    }


}
