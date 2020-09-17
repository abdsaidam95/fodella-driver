package com.foodella.driver.features.orderDetails.fragments.addressFragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;

import com.foodella.driver.base.BaseFragment;
import com.foodella.driver.databinding.FragmentAddressAndContactBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.loopj.android.http.HttpGet;

import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;

public class AddressAndContactFragment extends BaseFragment implements OnMapReadyCallback {

    private AddressAndContactViewModel viewModel;
    private FragmentAddressAndContactBinding binding;

    private String STATIC_MAP_API_ENDPOINT = "http://maps.googleapis.com/maps/api/staticmap?size=230x200&path=";
    private String path;


    public static AddressAndContactFragment newInstance() {
        return new AddressAndContactFragment();
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAddressAndContactBinding.inflate(inflater, container, false);
        ViewModelProvider.Factory factory = new ViewModelProvider.NewInstanceFactory();
        viewModel = new ViewModelProvider(requireActivity(), factory).get(AddressAndContactViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        viewModel.onStart();

        initializeView();


        //For test distanceMethod
        double m = calculateDistance(31.5032692, 34.464567, 31.5084178, 34.4566571);
        Toast.makeText(requireActivity(), "ddddddd  " + m, Toast.LENGTH_SHORT).show();

        return binding.getRoot();
    }


    private void initializeView() {

        initializeStaticMap();

        viewModel.showMessage.observe(this, this::showToast);
        viewModel.showMessageRes.observe(this, this::showToast);

        viewModel.doAction.observe(this, appAction -> {
            switch (appAction) {


                case ACTION_REJECT:

                    break;
            }
        });

    }

    private void initializeStaticMap() {
        //ToDo ADD API FOR MAP
        try {
            String marker_me = "color:orange|label:1|Brisbane";
            String marker_dest = "color:orange|label:7|San Francisco,USA";
            marker_me = URLEncoder.encode(marker_me, "UTF-8");

            marker_dest = URLEncoder.encode(marker_dest, "UTF-8");
            path = "weight:3|color:red|geodesic:true|Brisbane,Australia|Hong Kong|Moscow,Russia|London,UK|Reyjavik,Iceland|New York,USA|San Francisco,USA";
            path = URLEncoder.encode(path, "UTF-8");
            STATIC_MAP_API_ENDPOINT = STATIC_MAP_API_ENDPOINT + path + "&markers=" + marker_me + "&markers=" + marker_dest;

            Log.d("STATICMAPS", STATIC_MAP_API_ENDPOINT);

            AsyncTask<Void, Void, Bitmap> setImageFromUrl = new AsyncTask<Void, Void, Bitmap>() {
                @Override
                protected Bitmap doInBackground(Void... params) {
                    Bitmap bmp = null;
                    HttpClient httpclient = new DefaultHttpClient();
                    HttpGet request = new HttpGet(STATIC_MAP_API_ENDPOINT);

                    InputStream in = null;
                    try {
                        HttpResponse response = httpclient.execute(request);
                        in = response.getEntity().getContent();
                        bmp = BitmapFactory.decodeStream(in);
                        in.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return bmp;
                }

                protected void onPostExecute(Bitmap bmp) {
                    if (bmp != null) {
                        //binding.mapImg.setImageBitmap(bmp);
                    }
                }
            };
            setImageFromUrl.execute();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        Polyline path = googleMap.addPolyline(new PolylineOptions()
                .add(
                        new LatLng(38.893596444352134, -77.0381498336792),
                        new LatLng(38.89337933372204, -77.03792452812195),
                        new LatLng(38.89316222242831, -77.03761339187622),
                        new LatLng(38.89316222242831, -77.03549981117249),
                        new LatLng(38.89340438498248, -77.03514575958252),
                        new LatLng(38.893596444352134, -77.0349633693695)
                )
        );
        path.setWidth(10);
        path.setColor(Color.parseColor("#FF0000"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(38.89399, -77.03659), 14));

    }


    private double calculateDistance(double fromLong, double fromLat, double toLong, double toLat) {
        double d2r = Math.PI / 180;
        double dLong = (toLong - fromLong) * d2r;
        double dLat = (toLat - fromLat) * d2r;
        double a = Math.pow(Math.sin(dLat / 2.0), 2) + Math.cos(fromLat * d2r)
                * Math.cos(toLat * d2r) * Math.pow(Math.sin(dLong / 2.0), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = 6367000 * c;
        return Math.round(d);
    }


}
