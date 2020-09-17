package com.foodella.driver.features.main.profile;

import androidx.lifecycle.MutableLiveData;

import com.foodella.driver.base.BaseViewModel;
import com.foodella.driver.features.editProfile.Profile;

import static com.foodella.driver.utils.AppAction.GO_TO_EDIT_PROFILE;

public class MyProfileViewModel extends BaseViewModel {

    public MutableLiveData<String> name = new MutableLiveData<>();
    public MutableLiveData<String> dataOFBirth = new MutableLiveData<>();
    public MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<String> nationality = new MutableLiveData<>();
    public MutableLiveData<String> city = new MutableLiveData<>();
    public MutableLiveData<String> job = new MutableLiveData<>();
    public MutableLiveData<String> userImage = new MutableLiveData<>();
    public MutableLiveData<String> namberOfNational = new MutableLiveData<>();

    public MutableLiveData<String>  idcardImge = new MutableLiveData<>();
    public MutableLiveData<String>  drivercardImge = new MutableLiveData<>();
    public Profile profile;

    public MyProfileViewModel() {
        name.setValue("محمد احمد");
        dataOFBirth.setValue("12-12-2000");
        email.setValue("a@hotmail.com");
        nationality.setValue("Palestinian");
        city.setValue("Gaza");
        job.setValue("Android developer");
        namberOfNational.setValue("12475866935");
        idcardImge.setValue("");
        drivercardImge.setValue("");
        profile=new Profile(name.getValue(),dataOFBirth.getValue(),email.getValue(),nationality.getValue(),city.getValue(),job.getValue(),namberOfNational.getValue(),idcardImge.getValue(),drivercardImge.getValue());
    }

    public void onClickEditProfile() {
        doAction.setValue(GO_TO_EDIT_PROFILE);
    }

    public void setProfileData(Profile profile) {
        userImage.setValue(profile.getImgeUrl());
        name.setValue(profile.getName());
        dataOFBirth.setValue(profile.getDateOfBirth());
        email.setValue(profile.getEmail());
        nationality.setValue(profile.getNationality());
        city.setValue(profile.getCity());
        job.setValue(profile.getJob());
        namberOfNational.setValue(profile.getNationalityNumber());
        idcardImge.setValue(profile.getPersonalImage());
        drivercardImge.setValue(profile.getDriverImage());
    }
}