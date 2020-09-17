package com.foodella.driver.features.main.nav;

import com.foodella.driver.utils.AppAction;

import java.io.Serializable;

public class Menu implements Serializable {

    private int menuTitle = 0;
    private int orderNo = 0;
    private int menuIcon = 0;
    private AppAction action;

    public Menu() {
    }

    public Menu(int menuTitle, int menuIcon, int orderNo, AppAction action) {
        this.menuTitle = menuTitle;
        this.orderNo = orderNo;
        this.menuIcon = menuIcon;
        this.action = action;
    }

    public int getMenuTitle() {
        return menuTitle;
    }

    public void setMenuTitle(int menuTitle) {
        this.menuTitle = menuTitle;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public int getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(int menuIcon) {
        this.menuIcon = menuIcon;
    }

    public AppAction getAction() {
        return action;
    }

    public void setAction(AppAction action) {
        this.action = action;
    }
}
