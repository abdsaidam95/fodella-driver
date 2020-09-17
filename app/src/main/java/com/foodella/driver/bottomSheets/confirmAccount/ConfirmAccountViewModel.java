package com.foodella.driver.bottomSheets.confirmAccount;

import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.foodella.driver.R;
import com.foodella.driver.base.BaseViewModel;
import com.foodella.driver.utils.AppAction;
import com.foodella.driver.utils.dataBinding.TextChange;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import static java.lang.Math.max;

public class ConfirmAccountViewModel extends BaseViewModel {

    public MutableLiveData<String> timer = new MutableLiveData<>();
    public MutableLiveData<String> code = new MutableLiveData<>();
    public MutableLiveData<Boolean> isTimerFinished = new MutableLiveData<>();

    public MutableLiveData<Integer> codeError = new MutableLiveData<>();
    public TextChange codeTextChange = value -> codeError.setValue(0);

    public static final long START_TIME_IN_MILLIS = 41;
    public CountDownTimer mCountDownTimer;


    public ConfirmAccountViewModel() {
        codeError.setValue(0);
        isTimerFinished.setValue(false);
        bottomSheetState.setValue(BottomSheetBehavior.STATE_EXPANDED);

    }

    public void onStart() {
        startTimer();
    }

    public void onClickBackArrow() {
        doAction.setValue(AppAction.ACTION_GO_BACK);
    }

    public void onClickOk() {
        boolean v1 = validationActivationCode();
        if (v1) {
            doAction.setValue(AppAction.ACTION_PROFILE);
        }
    }

    public void onClickResend() {
        startTimer();
    }

    public void startTimer() {
        stopTimer();
        isTimerFinished.setValue(false);
        mCountDownTimer = new CountDownTimer((START_TIME_IN_MILLIS * 1000), 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long remainingSeconds = (millisUntilFinished / 1000) - 1;
                remainingSeconds = max(0, remainingSeconds);
                updateCountDownText(remainingSeconds);
            }

            @Override
            public void onFinish() {
                isTimerFinished.setValue(true);
            }
        }.start();
    }

    public void stopTimer() {
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
    }

    private void updateCountDownText(long remainingSeconds) {
        int minutes = (int) (remainingSeconds) / 60;
        int seconds = (int) (remainingSeconds) % 60;
        timer.setValue(minutes + "0:" + seconds);
    }

    private boolean validationActivationCode() {
        Log.d("ABR", "validationActivationCode: " + code.getValue() + "");
        if (TextUtils.isEmpty(code.getValue())) {
            codeError.setValue((R.string.please_enter_activation_code));
            return false;

        } else if (code.getValue().length() != 4) {
            codeError.setValue((R.string.please_enter_activation_code));
            return false;
        } else {
            codeError.setValue(0);
            return true;
        }
    }
}