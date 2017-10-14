package com.demo.androidbootstrap;

import android.app.Application;
import android.content.Context;

import com.demo.androidbootstrap.di.components.AppComponent;
import com.demo.androidbootstrap.di.components.DaggerAppComponent;
import com.demo.androidbootstrap.di.modules.AppModule;
import com.demo.androidbootstrap.di.modules.NetworkModule;

/**
 * Created by sahil on 10/14/17.
 */

public class BootstrapApplication extends Application {

    //Dependency Injection
    private static AppComponent sAppComponent;
    private static Object sObjectLock = new Object();

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static AppComponent getAppComponent(Context context) {
        BootstrapApplication application = (BootstrapApplication) context.getApplicationContext();
        if (sAppComponent == null)
            synchronized (sObjectLock) {
                if (sAppComponent == null) {
                    sAppComponent = DaggerAppComponent.
                            builder()
                            .appModule(application.getApplicationModule())
                            .networkModule(application.getNetworkModule())
                            .build();
                }
            }
        return sAppComponent;
    }

    private AppModule getApplicationModule() {
        return new AppModule(this);
    }

    private NetworkModule getNetworkModule() {
        NetworkModule networkModule = new NetworkModule("");
        return networkModule;
    }
}
