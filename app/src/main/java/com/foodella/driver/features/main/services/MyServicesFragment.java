package com.foodella.driver.features.main.services;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.foodella.driver.R;
import com.foodella.driver.base.BaseActivity;
import com.foodella.driver.base.BaseFragment;
import com.foodella.driver.bottomSheets.pickImage.PickImageBottomSheet;
import com.foodella.driver.bottomSheets.selectCity.SelectSingleCityBottomSheet;
import com.foodella.driver.databinding.FragmentMyServicesBinding;
import com.foodella.driver.features.addService.AddServiceActivity;
import com.foodella.driver.features.main.home.HomeFragment;
import com.foodella.driver.utils.AppAction;
import com.foodella.driver.utils.From;
import com.foodella.driver.utils.eventBusModel.ActionEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import static com.foodella.driver.features.addService.AddServiceActivity.RESULT_OK;
import static com.foodella.driver.utils.AppAction.ACTION_SHOW_BOTOOM_SHEET_CITY;
import static com.foodella.driver.utils.AppAction.ACTION_SHoW_BOTOOM_SHEET_IMGE;
import static com.foodella.driver.utils.AppAction.GO_TO_PASS_OBJECT;
import static com.foodella.driver.utils.AppAction.GO_TO_SERVICE_ACTIVITY;
import static com.foodella.driver.utils.AppConst.RES_NOT_OK;
import static com.foodella.driver.utils.AppConst.RES_OK;

public class MyServicesFragment extends BaseFragment {

    private MyServicesViewModel viewModel;
    private FragmentMyServicesBinding binding;
    public int position;
    Services services;
    int flag;


    public static MyServicesFragment newInstance() {
        return new MyServicesFragment();
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMyServicesBinding.inflate(inflater, container, false);

        ViewModelProvider.Factory factory = new ViewModelProvider.NewInstanceFactory();
        viewModel = new ViewModelProvider(requireActivity(), factory).get(MyServicesViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        viewModel.onStart();
        return binding.getRoot();
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
        if (actionEvent.getActions() == AppAction.DELETE_ITEM) {
            position = actionEvent.getPosition();
            viewModel.deleteItemService(position);
            viewModel.items.remove(position);
            if (viewModel.items.isEmpty()) {
                viewModel.isEmpty.setValue(true);
                viewModel.emptyIcon.setValue(R.drawable.ic_close);
                viewModel.emptyText.setValue((R.string.service_empty));
            }
        } else if (actionEvent.getActions() == AppAction.GO_TO_SERVICE_ACTIVITY) {
            position = actionEvent.getPosition();
            flag=1;
            services= (Services) actionEvent.getData();
            Intent intent = new Intent(getActivity(), AddServiceActivity.class);
            intent.putExtra("flag",flag);
            intent.putExtra("sarvice",services);
            startActivityForResult(intent, 200);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && data != null) {
            Services service = (Services) data.getExtras().getSerializable("service");
            viewModel.setAppendAdapterService(service);

        } else if (requestCode == 200 && data != null) {
            Services service = (Services) data.getExtras().getSerializable("service");
            viewModel.editItem(position, service);


        }


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inisalizeAction();
    }

    public void inisalizeAction() {
        viewModel.doAction.observe(this, new Observer<AppAction>() {
            @Override
            public void onChanged(AppAction appAction) {
                if (appAction == GO_TO_SERVICE_ACTIVITY) {
                    flag=2;
                    Intent intent = new Intent(getActivity(), AddServiceActivity.class);
                    intent.putExtra("flag",flag);
                    startActivityForResult(intent, 100);
                }
            }
        });
    }

}