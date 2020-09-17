package com.foodella.driver.features.editProfile;

import android.app.DatePickerDialog;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.MutableLiveData;

import com.esafirm.imagepicker.model.Image;
import com.foodella.driver.R;
import com.foodella.driver.base.BaseViewModel;
import com.foodella.driver.utils.AppAction;
import com.foodella.driver.utils.AppLanguageContextWrapper;
import com.foodella.driver.utils.dataBinding.TextChange;
import com.foodella.driver.utils.eventBusModel.ActionEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.Calendar;
import java.util.Locale;

import static com.foodella.driver.utils.AppAction.ACTION_SHOW_BOTOOM_SHEET_CITY;
import static com.foodella.driver.utils.AppAction.ACTION_SHoW_BOTOOM_SHEET_IMGE;
import static com.foodella.driver.utils.AppUtil.isEmailValid;

@RequiresApi(api = Build.VERSION_CODES.N)
public class EditProfileViewModel extends BaseViewModel {
    public MutableLiveData<String> name = new MutableLiveData<>();
    public MutableLiveData<String> dateOfBirth = new MutableLiveData<>();
    public MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<String> contryName = new MutableLiveData<>();
    public MutableLiveData<String> city = new MutableLiveData<>();
    public MutableLiveData<String> national = new MutableLiveData<>();
    public MutableLiveData<String> idcardImge = new MutableLiveData<>();
    public MutableLiveData<String> drivercardImge = new MutableLiveData<>();
    public MutableLiveData<String> userImage = new MutableLiveData<>();
    public MutableLiveData<Integer> viableErrorDriverImage = new MutableLiveData<>();
    public MutableLiveData<Integer> viableErrorPersonalImage = new MutableLiveData<>();
    public MutableLiveData<Integer> position = new MutableLiveData<>();

    public MutableLiveData<Integer> viableErrorTextCity = new MutableLiveData<>();
    public MutableLiveData<String> job = new MutableLiveData<>();


    public MutableLiveData<Integer> nameError = new MutableLiveData<>();
    public MutableLiveData<Integer> dateError = new MutableLiveData<>();
    public MutableLiveData<Integer> emailError = new MutableLiveData<>();

    public MutableLiveData<Integer> nationalError = new MutableLiveData<>();
    public MutableLiveData<Integer> jobError = new MutableLiveData<>();


    public TextChange phoneTextChange = value -> nameError.setValue(0);
    public TextChange emailTextChange = value -> emailError.setValue(0);
    public TextChange passwordTextChange = value -> dateError.setValue(0);
    public TextChange nationalTextChange = value -> nationalError.setValue(0);
    public TextChange jobTextChange = value -> jobError.setValue(0);

    public MutableLiveData<Boolean> images = new MutableLiveData<>();
    private String myFormat;

    public final Calendar calendar = Calendar.getInstance();

    public void onClickPhotoButton() {
        position.setValue(1);

        doAction.setValue(ACTION_SHoW_BOTOOM_SHEET_IMGE);
    }

    public void onClickCityDialog() {
        doAction.setValue(ACTION_SHOW_BOTOOM_SHEET_CITY);
    }

    public EditProfileViewModel() {
        nameError.setValue(0);
        dateError.setValue(0);
        emailError.setValue(0);
        nationalError.setValue(0);
        jobError.setValue(0);
        viableErrorDriverImage.setValue(View.GONE);
        viableErrorPersonalImage.setValue(View.GONE);

        viableErrorTextCity.setValue(View.INVISIBLE);
        city.setValue("");
    }

    private boolean validationName() {
        if (TextUtils.isEmpty(name.getValue())) {
            nameError.setValue((R.string.please_enter_name));
            return false;
        } else {
            nameError.setValue(0);
            return true;
        }
    }

    private boolean validationEmail() {
        if (TextUtils.isEmpty(email.getValue())) {
            emailError.setValue((R.string.please_enter_email));
            return false;
        } else if (!isEmailValid(email.getValue())) {
            emailError.setValue((R.string.please_enter_email_valid_email));
            return false;
        } else {
            emailError.setValue(0);
            return true;
        }
    }

    private boolean validationNational() {
        if (TextUtils.isEmpty(contryName.getValue())) {
            nationalError.setValue((R.string.please_enter_national));
            return false;
        } else {
            nationalError.setValue(0);
            return true;
        }
    }

    private boolean validationJob() {
        if (TextUtils.isEmpty(job.getValue())) {
            jobError.setValue((R.string.please_enter_Job));
            return false;
        } else {
            jobError.setValue(0);
            return true;
        }
    }

    private boolean validationDate() {
        if (TextUtils.isEmpty(dateOfBirth.getValue())) {
            dateError.setValue((R.string.please_enter_date));
            return false;
        } else {
            dateError.setValue(0);
            return true;
        }
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


    public void onClickButton() {
        boolean v1 = validationDate();
        boolean v2 = validationName();
        boolean v3 = validationEmail();
        boolean v4 = validationJob();
        boolean v5 = validationNational();
        boolean v6 = validationNumber();
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

        String cityStr = city.getValue();
        if (cityStr == null || TextUtils.isEmpty(cityStr)) {
            viableErrorTextCity.setValue(View.VISIBLE);
        } else {
            viableErrorTextCity.setValue(View.GONE);
        }

        if (v1 && v2 && v3 && v4 && v5 && cityStr != null && v6 && idcardImge.getValue() != null && drivercardImge.getValue() != null && userImage != null) {
            EventBus.getDefault().post(new ActionEvent(AppAction.ACTION_GO_PROFILE_FRAGMENT));
        }
    }


    public Profile getProfile() {
        Profile profile = new Profile(name.getValue(), dateOfBirth.getValue(), email.getValue(), contryName.getValue(), city.getValue(), job.getValue(), national.getValue(), idcardImge.getValue(), drivercardImge.getValue(), userImage.getValue());
        return profile;
    }

    public void setInisializeData(Profile profile) {
        userImage.setValue(profile.getImgeUrl());
        name.setValue(profile.getName());
        dateOfBirth.setValue(profile.getDateOfBirth());
        email.setValue(profile.getEmail());
        contryName.setValue(profile.getNationality());
        city.setValue(profile.getCity());
        job.setValue(profile.getJob());
        national.setValue(profile.getNationalityNumber());
        idcardImge.setValue(profile.getPersonalImage());
        drivercardImge.setValue(profile.getDriverImage());

    }

    public void onClick() {
        position.setValue(2);
        doAction.setValue(ACTION_SHoW_BOTOOM_SHEET_IMGE);
        if (idcardImge != null) {
            viableErrorPersonalImage.setValue(View.GONE);
        }
    }

    public void onClickPersonalDraive() {
        position.setValue(3);
        images.setValue(false);
        doAction.setValue(ACTION_SHoW_BOTOOM_SHEET_IMGE);
        if (drivercardImge != null) {
            viableErrorDriverImage.setValue(View.GONE);
        }
    }

    public void onSelectedImage(Image image) {
        if (position.getValue() == 1) {
            userImage.setValue(image.getPath());

        } else if (position.getValue() == 2) {
            idcardImge.setValue(image.getPath());
        } else {
            drivercardImge.setValue(image.getPath());

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void updateLabel() {
        if (AppLanguageContextWrapper.getAppLanguage().equals("ar")) {
            myFormat = "yy/MM/dd";
        } else {
            myFormat = "dd/MM/yy"; //In which you need put here
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(myFormat, Locale.getDefault());
        dateOfBirth.setValue(simpleDateFormat.format(calendar.getTime()));
    }

    public void onShowDialogDataPicker() {
        doAction.setValue(AppAction.SHOW_DIALOG_DATE);
    }

    public DatePickerDialog.OnDateSetListener date = (view, year, month, dayOfMonth) -> {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        updateLabel();
    };


}
