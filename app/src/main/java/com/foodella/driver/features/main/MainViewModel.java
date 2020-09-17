package com.foodella.driver.features.main;

import androidx.lifecycle.MutableLiveData;

import com.foodella.driver.R;
import com.foodella.driver.base.BaseViewModel;
import com.foodella.driver.features.main.nav.MenuAdapter;
import com.foodella.driver.features.main.nav.Menu;
import com.foodella.driver.utils.AppAction;

import java.util.ArrayList;
import java.util.Objects;

public class MainViewModel extends BaseViewModel {

    public MutableLiveData<MenuAdapter> menuAdapter = new MutableLiveData<>();
    public MutableLiveData<String> userName = new MutableLiveData<>();
    public MutableLiveData<String> userPhone = new MutableLiveData<>();
    public MutableLiveData<String> image = new MutableLiveData<>();
    private ArrayList<Menu> menus;

    private Menu selectedMenu = null;

    public MainViewModel() {

        userName.setValue("رياض أحمد");
        userPhone.setValue("00960123456789");
        image.setValue("");

        menuAdapter.setValue(new MenuAdapter());
        setData();
    }

    public void onStart() {
        setData();
    }

    public void handleItemNavSelection(Menu menu) {
        selectedMenu = menu;
        doAction.setValue(menu.getAction());
    }

    public void setData() {
        menus = new ArrayList<>();
        menus.add(new Menu((R.string.nav_home), R.drawable.ic_home, 0, AppAction.ACTION_NAV_HOME));
        menus.add(new Menu((R.string.nav_orders), R.drawable.ic_orders, 1, AppAction.ACTION_NAV_ORDERS));
        menus.add(new Menu((R.string.nav_payments), R.drawable.ic_bill, 0, AppAction.ACTION_NAV_PAYMENTS));
        menus.add(new Menu((R.string.nav_cars), R.drawable.ic_car, 0, AppAction.ACTION_NAV_CARS));
        menus.add(new Menu((R.string.nav_services), R.drawable.ic_shopping_bag, 0, AppAction.ACTION_NAV_SERVICES));
        menus.add(new Menu((R.string.nav_chats), R.drawable.ic_chat, 0, AppAction.ACTION_NAV_CHATS));
        menus.add(new Menu((R.string.nav_profile), R.drawable.ic_user_circle, 0, AppAction.ACTION_NAV_PROFILE));
        menus.add(new Menu((R.string.nav_more), R.drawable.ic_more, 0, AppAction.ACTION_NAV_MORE));
        menus.add(new Menu((R.string.nav_sign_out), R.drawable.ic_sign_out, 0, AppAction.ACTION_NAV_SIGN_OUT));
        handleItemNavSelection(menus.get(0));
        Objects.requireNonNull(menuAdapter.getValue()).setData(menus);
    }

    public void onBackPressed() {
        if (selectedMenu.getAction() != AppAction.ACTION_NAV_HOME) {
            handleItemNavSelection(menus.get(0));
        } else {
            doAction.setValue(AppAction.ACTION_QUIT);
        }
    }
}
