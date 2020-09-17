package com.foodella.driver.features.main.nav;

import androidx.lifecycle.MutableLiveData;

import com.foodella.driver.base.BaseViewModel;
import com.foodella.driver.features.main.nav.Menu;
import com.foodella.driver.utils.AppAction;
import com.foodella.driver.utils.eventBusModel.ActionEvent;

import org.greenrobot.eventbus.EventBus;

public class MenuItemViewModel extends BaseViewModel {

    private Menu menu;
    private int position;

    public MutableLiveData<Integer> menuTitle = new MutableLiveData<>();
    public MutableLiveData<Integer> icon = new MutableLiveData<>();
    public MutableLiveData<String> count = new MutableLiveData<>();
    public MutableLiveData<Boolean> isShowCount = new MutableLiveData<>();

    public MenuItemViewModel(Menu menu) {
        this.menu = menu;
    }

    public void onStart() {
        menuTitle.setValue(menu.getMenuTitle());
        icon.setValue(menu.getMenuIcon());
        count.setValue(String.valueOf(menu.getOrderNo()));
        isShowCount.setValue(menu.getOrderNo() != 0);
    }

    public void onClickItem() {
        EventBus.getDefault().post(new ActionEvent(AppAction.ACTION_NAV_ITEM, menu, position));
    }
}
