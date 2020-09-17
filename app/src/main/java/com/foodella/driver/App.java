package com.foodella.driver;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.orhanobut.hawk.Hawk;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initializeHawk();
        initializeFresco();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }


    private void initializeFresco() {
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                .setMainDiskCacheConfig(DiskCacheConfig.newBuilder(this).build())
                .setDownsampleEnabled(true)
                .build();
        Fresco.initialize(this, config);
    }

    private void initializeHawk() {
        Hawk.init(this).build();
    }

}
