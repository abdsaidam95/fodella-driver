package com.foodella.driver.features.orderDetails.fragments.ratingFragment;

import androidx.lifecycle.MutableLiveData;

import com.foodella.driver.base.BaseViewModel;

public class RatingViewModel extends BaseViewModel {

    public MutableLiveData<String> image = new MutableLiveData<>();
    public MutableLiveData<String> rating = new MutableLiveData<>();
    public MutableLiveData<String> ratingDescription = new MutableLiveData<>();
    public MutableLiveData<String> date = new MutableLiveData<>();


    public RatingViewModel() {
        rating.setValue(String.valueOf(3.5));

        ratingDescription.setValue("rating rating rating rating rating rating rating rating rating rating rating rating rating rating rating rating \n  rating rating rating rating rating rating rating rating rating rating rating rating rating rating  ");
        date.setValue("8/8/2020");
    }
    // TODO: 20-Aug-20 make rating request
    public void makeRatingRequest(String image,String rating,String ratingDescription,String date){

    }


    public void onStart() {
    }

}
