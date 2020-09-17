package com.foodella.driver.features.profile.documents;

import android.text.TextUtils;
import android.view.View;

import androidx.lifecycle.MutableLiveData;

import com.esafirm.imagepicker.model.Image;
import com.foodella.driver.R;
import com.foodella.driver.base.BaseViewModel;
import com.foodella.driver.utils.AppAction;
import com.foodella.driver.utils.dataBinding.TextChange;
import com.foodella.driver.utils.eventBusModel.ActionEvent;

import org.greenrobot.eventbus.EventBus;

import static com.foodella.driver.utils.AppAction.ACTION_GO_GALLERY;

public class AddDataViewModel extends BaseViewModel {

    public MutableLiveData<Integer> nationalError = new MutableLiveData<>();
    public MutableLiveData<String> national = new MutableLiveData<>();
    public MutableLiveData<String> idcardImge = new MutableLiveData<>();
    public MutableLiveData<String> drivercardImge = new MutableLiveData<>();
    public MutableLiveData<Boolean> images = new MutableLiveData<>();
    public MutableLiveData<Integer> viableErrorPersonalImage = new MutableLiveData<>();
    public MutableLiveData<Integer> viableErrorDriverImage = new MutableLiveData<>();


    public void onClick() {
        images.setValue(true);
        doAction.setValue(ACTION_GO_GALLERY);
        if (idcardImge.getValue() != null) {
            viableErrorPersonalImage.setValue(View.GONE);
        }
    }

    public void onClickPersonalDraive() {
        images.setValue(false);
        doAction.setValue(ACTION_GO_GALLERY);
        if (drivercardImge.getValue() != null) {
            viableErrorDriverImage.setValue(View.GONE);
        }
    }

    public TextChange nationalTextChange = value -> nationalError.setValue(0);

    public AddDataViewModel() {
        images.setValue(false);
        nationalError.setValue(0);
        viableErrorDriverImage.setValue(View.GONE);
        viableErrorPersonalImage.setValue(View.GONE);
    }

    private boolean validationNumber() {
        if (TextUtils.isEmpty(national.getValue())) {
            nationalError.setValue((R.string.please_enter_id_number));
            return false;
        } else {
            nationalError.setValue(0);
            return true;
        }
    }

    // TODO: 20-Aug-20 make request to add document
    public void makeDocumentRequest(String nationalNumber, String idcardImge, String drivercardImge) {

    }

    public void onClickButton() {
        boolean v1 = validationNumber();
        if (idcardImge == null || TextUtils.isEmpty(idcardImge.getValue())) {
            viableErrorPersonalImage.setValue(View.VISIBLE);
        } else {
            viableErrorPersonalImage.setValue(View.GONE);
        }
        if (drivercardImge == null || TextUtils.isEmpty(drivercardImge.getValue())) {
            viableErrorDriverImage.setValue(View.VISIBLE);
        } else {
            viableErrorDriverImage.setValue(View.GONE);
        }
        if (v1 && idcardImge.getValue() != null && drivercardImge.getValue() != null) {
            EventBus.getDefault().post(new ActionEvent(AppAction.ACTION_GO_SURVICE));
        }
    }

    public void onSelectedImage(Image image) {
        if (images.getValue()) {
            idcardImge.setValue(image.getPath());
        } else {
            drivercardImge.setValue(image.getPath());
        }
    }


}
