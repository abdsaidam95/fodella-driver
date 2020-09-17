package com.foodella.driver.features.profile.info;

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

import static com.foodella.driver.utils.AppAction.ACTION_GO_DECUMENT;
import static com.foodella.driver.utils.AppAction.ACTION_SHOW_BOTOOM_SHEET_CITY;
import static com.foodella.driver.utils.AppAction.ACTION_SHoW_BOTOOM_SHEET_IMGE;
import static com.foodella.driver.utils.AppAction.SHOW_DIALOG_DATE;
import static com.foodella.driver.utils.AppUtil.isEmailValid;

@RequiresApi(api = Build.VERSION_CODES.N)
public class InfoViewModel extends BaseViewModel {

    public MutableLiveData<String> name = new MutableLiveData<>();
    public MutableLiveData<String> dateOfBirth = new MutableLiveData<>();
    public MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<String> contryName = new MutableLiveData<>();
    public MutableLiveData<String> city = new MutableLiveData<>();
    public MutableLiveData<Integer> viableErrorTextCity = new MutableLiveData<>();
    public MutableLiveData<Integer> viableErrorimage = new MutableLiveData<>();
    public MutableLiveData<String> job = new MutableLiveData<>();
    String myFormat;

    public MutableLiveData<Integer> nameError = new MutableLiveData<>();
    public MutableLiveData<Integer> dateError = new MutableLiveData<>();
    public MutableLiveData<Integer> emailError = new MutableLiveData<>();
    public MutableLiveData<Integer> nationalError = new MutableLiveData<>();
    public MutableLiveData<Integer> jobError = new MutableLiveData<>();

    public MutableLiveData<String> userImage = new MutableLiveData<>();

    public TextChange phoneTextChange = value -> nameError.setValue(0);
    public TextChange emailTextChange = value -> emailError.setValue(0);
    public TextChange passwordTextChange = value -> dateError.setValue(0);
    public TextChange nationalTextChange = value -> nationalError.setValue(0);
    public TextChange jobTextChange = value -> jobError.setValue(0);


    public final Calendar calendar = Calendar.getInstance();
    public DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };

    public void onClickPhotoButton() {
        doAction.setValue(ACTION_SHoW_BOTOOM_SHEET_IMGE);
    }

    public void onClickCityDialog() {
        doAction.setValue(ACTION_SHOW_BOTOOM_SHEET_CITY);
    }

    public InfoViewModel() {
        nameError.setValue(0);
        dateError.setValue(0);
        emailError.setValue(0);
        nationalError.setValue(0);
        jobError.setValue(0);

        viableErrorTextCity.setValue(View.INVISIBLE);
        viableErrorimage.setValue(View.GONE);
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

    // TODO:  api request Method
    public void makeInfoRequest(String name, String dateBirth, String email, String nationality, String city, String job, String imgeUrl) {

    }

    public void onClickButton() {
        boolean v1 = validationDate();
        boolean v2 = validationName();
        boolean v3 = validationEmail();
        boolean v4 = validationJob();
        boolean v5 = validationNational();

        String cityStr = city.getValue();
        if (cityStr == null || TextUtils.isEmpty(cityStr)) {
            viableErrorTextCity.setValue(View.VISIBLE);
        } else {
            viableErrorTextCity.setValue(View.INVISIBLE);
        }
        if (userImage == null || TextUtils.isEmpty(userImage.getValue())) {
            viableErrorimage.setValue(View.VISIBLE);
        } else {
            viableErrorimage.setValue(View.GONE);
        }

        if (v1 && v2 && v3 && v4 && v5 && cityStr != null && userImage.getValue() != null) {
            EventBus.getDefault().post(new ActionEvent(ACTION_GO_DECUMENT));
        }
    }

    public void onSelectedImage(Image image) {
        userImage.setValue(image.getPath());
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void updateLabel() {
        if (AppLanguageContextWrapper.getAppLanguage().equals("ar")) {
             myFormat = "yy/MM/dd";
        }
        else {
            myFormat = "dd/MM/yy"; //In which you need put here
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(myFormat, Locale.getDefault());
        dateOfBirth.setValue(simpleDateFormat.format(calendar.getTime()));
    }

    public void onShowDialogDataPicker() {
        doAction.setValue(AppAction.SHOW_DIALOG_DATE);
    }
}

