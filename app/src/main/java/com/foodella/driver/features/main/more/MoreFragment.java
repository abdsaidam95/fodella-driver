package com.foodella.driver.features.main.more;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;

import com.foodella.driver.base.BaseActivity;
import com.foodella.driver.base.BaseFragment;
import com.foodella.driver.bottomSheets.languageBottomSheet.LanguageBottomSheet;
import com.foodella.driver.databinding.FragmentMoreBinding;
import com.foodella.driver.features.aboutApp.AboutAppActivity;
import com.foodella.driver.features.contactUs.ContactUsActivity;
import com.foodella.driver.features.messages.MessagesActivity;
import com.foodella.driver.features.paymentMethods.PaymentMethodsActivity;
import com.foodella.driver.features.privacyPolicy.PrivacyPolicyActivity;
import com.foodella.driver.features.termsOfUse.TermsOfUseActivity;
import com.foodella.driver.utils.AppAction;
import com.foodella.driver.utils.From;
import com.foodella.driver.utils.eventBusModel.ActionEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.jetbrains.annotations.NotNull;

public class MoreFragment extends BaseFragment {

    private FragmentMoreBinding binding;
    private MoreViewModel viewModel;

    public static MoreFragment newInstance() {
        return new MoreFragment();
    }


    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMoreBinding.inflate(inflater, container, false);
        ViewModelProvider.Factory factory = new ViewModelProvider.NewInstanceFactory();
        viewModel = new ViewModelProvider(requireActivity(), factory).get(MoreViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        viewModel.onStart();

        initializeView();

        return binding.getRoot();
    }


    private void initializeView() {
        viewModel.showMessage.observe(this, this::showToast);
        viewModel.showMessageRes.observe(this, this::showToast);


        viewModel.doAction.observe(this, appAction -> {
            switch (appAction) {
                case ACTION_NAV_PAYMENTS_METHODS:
                    ((BaseActivity) requireActivity()).startNewActivity(PaymentMethodsActivity.class);
                    break;
                case ACTION_NAV_MORE_MESSAGES:
                    ((BaseActivity) requireActivity()).startNewActivity(MessagesActivity.class);
                    break;
                case ACTION_NAV_TERMS_OF_USE:
                    ((BaseActivity) requireActivity()).startNewActivity(TermsOfUseActivity.class);
                    break;
                case ACTION_NAV_PRIVACY_POLICY:
                    ((BaseActivity) requireActivity()).startNewActivity(PrivacyPolicyActivity.class);
                    break;
                case ACTION_NAV_CONTACT_US:
                    ((BaseActivity) requireActivity()).startNewActivity(ContactUsActivity.class);
                    break;
                case ACTION_NAV_ABOUT:
                    ((BaseActivity) requireActivity()).startNewActivity(AboutAppActivity.class);
                    break;
                case ACTION_NAV_LANGUAGE:
                    showLanguageDialog();
                    break;
                case ACTION_NAV_SHARE:
                    EventBus.getDefault().post(new ActionEvent(AppAction.SHARE_APP));
                    break;
            }
        });
    }

    private void showLanguageDialog() {
        LanguageBottomSheet bottomSheetFragment = new LanguageBottomSheet(From.FROM_MORE_FRAGMENT);
        bottomSheetFragment.show(getChildFragmentManager(), bottomSheetFragment.getTag());
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
        if (actionEvent.getActions() == AppAction.ACTION_NAV_ITEM) {
            Object data = actionEvent.getData();
            if (data != null && data instanceof com.foodella.driver.features.main.nav.Menu) {
                viewModel.handleItemNavSelection((com.foodella.driver.features.main.nav.Menu) actionEvent.getData());
            }
        }
    }

}