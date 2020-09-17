package com.foodella.driver.utils.eventBusModel;

import android.view.View;

import com.foodella.driver.utils.AppAction;

public class ActionEvent {
    private AppAction actions;
    private Object data = null;
    private int position = -1;
    private View view;

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public ActionEvent(AppAction actions, View view) {
        this.actions = actions;
        this.view = view;
    }

    public ActionEvent(AppAction actions, int position) {
        this.actions = actions;
        this.position = position;
    }




    public ActionEvent(AppAction actions) {
        this.actions = actions;
    }

    public ActionEvent(AppAction actions, Object data) {
        this.actions = actions;
        this.data = data;
    }

    public ActionEvent(AppAction actions, Object data, int position) {
        this.actions = actions;
        this.data = data;
        this.position = position;
    }


    public ActionEvent(String action, Object data, int position) {
        this.data = data;
        this.position = position;
    }


    public AppAction getActions() {
        return actions;
    }

    public void setActions(AppAction actions) {
        this.actions = actions;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
