package com.foodella.driver.features.main.more;

import androidx.lifecycle.MutableLiveData;

import com.foodella.driver.R;
import com.foodella.driver.base.BaseViewModel;
import com.foodella.driver.features.main.nav.Menu;
import com.foodella.driver.features.main.nav.MenuAdapter;
import com.foodella.driver.utils.AppAction;

import java.util.ArrayList;
import java.util.Objects;

public class MoreViewModel extends BaseViewModel {

    public MutableLiveData<MenuAdapter> menuAdapter = new MutableLiveData<>();

    public MoreViewModel() {
    }

    public void onStart() {
        menuAdapter.setValue(new MenuAdapter());
        setData();
    }

    public void handleItemNavSelection(Menu menu) {
        doAction.setValue(menu.getAction());
    }


    private void setData() {
        ArrayList<Menu> menus = new ArrayList<>();
        menus.add(new Menu((R.string.payment_method), R.drawable.ic_payment_methods, 0, AppAction.ACTION_NAV_PAYMENTS_METHODS));
        menus.add(new Menu((R.string.messages), R.drawable.ic_message, 0, AppAction.ACTION_NAV_MORE_MESSAGES));
        menus.add(new Menu((R.string.terms_of_use), R.drawable.ic_terms_of_use, 0, AppAction.ACTION_NAV_TERMS_OF_USE));
        menus.add(new Menu((R.string.privacy_policy), R.drawable.ic_privacy_policy, 0, AppAction.ACTION_NAV_PRIVACY_POLICY));
        menus.add(new Menu((R.string.contact_us), R.drawable.ic_contact_us, 0, AppAction.ACTION_NAV_CONTACT_US));
        menus.add(new Menu((R.string.about), R.drawable.ic_about, 0, AppAction.ACTION_NAV_ABOUT));
        menus.add(new Menu((R.string.language), R.drawable.ic_language, 0, AppAction.ACTION_NAV_LANGUAGE));
        menus.add(new Menu((R.string.share), R.drawable.ic_share_app, 0, AppAction.ACTION_NAV_SHARE));
        Objects.requireNonNull(menuAdapter.getValue()).setData(menus);
    }
}