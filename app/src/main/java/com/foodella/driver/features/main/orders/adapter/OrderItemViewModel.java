package com.foodella.driver.features.main.orders.adapter;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.MutableLiveData;

import com.foodella.driver.R;
import com.foodella.driver.base.BaseViewModel;
import com.foodella.driver.bottomSheets.signUp.SignUpBottomSheet;
import com.foodella.driver.features.main.chats.adapter.Chat;
import com.foodella.driver.utils.AppAction;
import com.foodella.driver.utils.OrderStatus;
import com.foodella.driver.utils.eventBusModel.ActionEvent;

import org.greenrobot.eventbus.EventBus;

import static com.foodella.driver.utils.AppAction.ACTION_CLICK_BUTTON;
import static com.foodella.driver.utils.AppAction.ACTION_REJECT;

public class OrderItemViewModel extends BaseViewModel {

    public MutableLiveData<Integer> dateOrderLiveData = new MutableLiveData<>();
    public MutableLiveData<Integer> dateReciveLiveData = new MutableLiveData<>();
    public MutableLiveData<Integer> textButtonAccept = new MutableLiveData<>();
    public MutableLiveData<Integer> textButtonReject = new MutableLiveData<>();
    public MutableLiveData<Integer> delivaryTimeLiveData = new MutableLiveData<>();
    public MutableLiveData<Integer> visbility = new MutableLiveData<>();
    public MutableLiveData<Integer> visbilityPriceText = new MutableLiveData<>();
    public MutableLiveData<Integer> visbilityTab3 = new MutableLiveData<>();
    public MutableLiveData<Integer> visbilityratingBar = new MutableLiveData<>();
    public MutableLiveData<Boolean> isShowPopUp = new MutableLiveData<>();
    public MutableLiveData<Integer> textNotEvaluation = new MutableLiveData<>();
    public MutableLiveData<Integer> typeOrderLiveData = new MutableLiveData<>();
    public MutableLiveData<Integer> priceOrder = new MutableLiveData<>();
    public Order order;

    private int position;


    public OrderItemViewModel() {
    }

    public OrderItemViewModel(Order order) {
        this.order = order;
        //isShowPopUp.setValue(true);
    }

    public View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EventBus.getDefault().post(new ActionEvent(AppAction.ACTION_SHOW_MENU_MESSAGE, v));
        }
    };

    public void onStart(OrderStatus mOrderStatus) {

        switch (mOrderStatus) {
            case PENDING:
                visbility.setValue(View.GONE);
                textButtonAccept.setValue(R.string.acceptance);
                textButtonReject.setValue(R.string.refusal);
                textNotEvaluation.setValue(View.GONE);
                visbilityPriceText.setValue(View.GONE);
                visbilityTab3.setValue(View.VISIBLE);
                visbilityratingBar.setValue(View.GONE);
                //acceptBtnVis.setvalue(true);
                break;
            case IN_PROGRESS:
                visbility.setValue(View.VISIBLE);
                textButtonAccept.setValue(R.string.acceptance);
                textButtonReject.setValue(R.string.cancel);
                visbilityPriceText.setValue(View.VISIBLE);
                visbilityTab3.setValue(View.VISIBLE);
                visbilityratingBar.setValue(View.GONE);
                textNotEvaluation.setValue(View.GONE);


                break;
            case HISTORY:
                visbilityTab3.setValue(View.INVISIBLE);
                visbility.setValue(View.GONE);
                visbilityratingBar.setValue(View.VISIBLE);
                visbilityPriceText.setValue(View.VISIBLE);
                textNotEvaluation.setValue(View.GONE);
                break;
            case RETURNED:
                visbilityTab3.setValue(View.INVISIBLE);
                visbility.setValue(View.GONE);
                visbilityPriceText.setValue(View.VISIBLE);
                visbilityratingBar.setValue(View.INVISIBLE);
                textNotEvaluation.setValue(View.GONE);
                break;
        }

        dateOrderLiveData.setValue(R.string.dataOrder);
        dateReciveLiveData.setValue(R.string.dataRecive);
        delivaryTimeLiveData.setValue(R.string.delivaryTime);
        typeOrderLiveData.setValue(R.string.priceOrder);
        priceOrder.setValue(R.string.type);
    }

    public void onClick() {
        doAction.setValue(ACTION_CLICK_BUTTON);
    }


    public void onClickAccept() {
        EventBus.getDefault().post(new ActionEvent(AppAction.ACTION_ACCEPTT));
    }


    public void onClickReject() {
        EventBus.getDefault().post(new ActionEvent(ACTION_REJECT));
    }


    public void onClickDetails() {
        EventBus.getDefault().post(new ActionEvent(AppAction.ACTION_ORDER_ITEM, order, position));
    }

    public void onClickMore() {
        isShowPopUp.setValue(true);
    }


}
