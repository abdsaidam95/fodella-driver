package com.foodella.driver.features.main.orders;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;

import com.foodella.driver.R;
import com.foodella.driver.base.BaseActivity;
import com.foodella.driver.base.BaseFragment;
import com.foodella.driver.bottomSheets.cancelOrder.CancelOrderBottomSheet;
import com.foodella.driver.bottomSheets.approvalOrder.ApprovalOrderBottomSheet;
import com.foodella.driver.databinding.FragmentOrdersBinding;
import com.foodella.driver.features.main.home.HomeFragment;
import com.foodella.driver.features.main.orders.adapter.Order;
import com.foodella.driver.features.orderDetails.OrderDetailsActivity;
import com.foodella.driver.utils.AppAction;
import com.foodella.driver.utils.eventBusModel.ActionEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.jetbrains.annotations.NotNull;

public class OrdersFragment extends BaseFragment {

    private FragmentOrdersBinding binding;
    private OrdersViewModel viewModel;

    public OrdersFragment() {
    }

    public static OrdersFragment newInstance() {
        OrdersFragment fragment = new OrdersFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentOrdersBinding.inflate(inflater, container, false);

        ViewModelProvider.Factory factory = new ViewModelProvider.NewInstanceFactory();
        viewModel = new ViewModelProvider(requireActivity(), factory).get(OrdersViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        viewModel.onStart();

        initializeView();

        return binding.getRoot();
    }

    private void initializeView() {
        viewModel.showMessage.observe(this, this::showToast);
        viewModel.showMessageRes.observe(this, this::showToast);
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void displayPopupWindow(View anchorView) {

        View layout = getLayoutInflater().inflate(R.layout.popup_window, null);
        PopupWindow popup = new PopupWindow(layout);
        popup.setContentView(layout);
        popup.setHeight(ConstraintLayout.LayoutParams.WRAP_CONTENT);
        popup.setWidth(400);
        popup.setOutsideTouchable(true);
        popup.setFocusable(true);
        popup.setBackgroundDrawable(new BitmapDrawable());
        popup.showAsDropDown(anchorView, 300, -500);
        popup.showAsDropDown(anchorView);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


    @Subscribe
    public void onActionEvent(ActionEvent actionEvent) {
        if (actionEvent.getActions() == AppAction.ACTION_SHOW_MENU_MESSAGE) {
            displayPopupWindow(actionEvent.getView());
        } else if (actionEvent.getActions() == AppAction.ACTION_ORDER_ITEM) {

            Object data = actionEvent.getData();
            if (data != null && data instanceof Order) {
                ((BaseActivity) requireActivity()).startNewActivity(OrderDetailsActivity.class);
            }

        } else if (actionEvent.getActions() == AppAction.ACTION_ACCEPTT) {
            showApprovalOrderDialog();
        } else if (actionEvent.getActions() == AppAction.ACTION_REJECT) {
            showCancelOrderDialog();
        }
    }

    private void showApprovalOrderDialog() {
        ApprovalOrderBottomSheet bottomSheetFragment = new ApprovalOrderBottomSheet();
        bottomSheetFragment.show(getChildFragmentManager(), "bottomSheetFragment.getTag()");
    }

    private void showCancelOrderDialog() {
        CancelOrderBottomSheet bottomSheetFragment = new CancelOrderBottomSheet();
        bottomSheetFragment.show(getChildFragmentManager(), bottomSheetFragment.getTag());
    }


}