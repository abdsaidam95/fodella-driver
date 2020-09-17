package com.foodella.driver.features.privacyPolicy;

import androidx.lifecycle.MutableLiveData;

import com.foodella.driver.base.BaseViewModel;
import com.foodella.driver.utils.AppAction;

public class PrivacyPolicyViewModel extends BaseViewModel {

    public MutableLiveData<String> privacyPolicy = new MutableLiveData<>();

    public PrivacyPolicyViewModel() {
        privacyPolicy.setValue("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus elit libero, aliquam eu lobortis venenatis, \n" +
                " cursus vel ipsum. Curabitur urna urna, pellentesque a porttitor sed, eleifend et arcu. Vivamus at neque at nibh pellentesque \n" +
                " pharetra ac eu nunc. Duis risus ipsum, ornare tincidunt turpis at, sagittis rhoncus sem. Suspendisse eu blandit nisi, sit amet sagittis lorem.\n" +
                " Sed sagittis sollicitudin leo, eu varius odio accumsan id. Ut et interdum tortor. Nam pretium, \n" +
                " metus ut fermentum vehicula, risus quam imperdiet lacus, a ultricies lacus metus quis purus.");
    }

    public void onStart() {
    }

    public void onClickBackArrow() {
        doAction.setValue(AppAction.ACTION_BACK);
    }

    public void makePrivacyPolicyRequest(){}
}
