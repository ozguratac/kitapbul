package com.mozosoft.zgr.kitapbul;


import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleObserver;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.appopen.AppOpenAd;

public class AppOpenManager implements Application.ActivityLifecycleCallbacks, LifecycleObserver {

    private static final String AD_UNIT_ID = "ca-app-pub-4061139306454785/2355546158";
    private AppOpenAd appOpenAd = null;
    private AppOpenAd.AppOpenAdLoadCallback loadCallback;
    private final Application myApplication;
    private Activity currentActivity;

    public AppOpenManager(Application application) {
        myApplication = application;
        myApplication.registerActivityLifecycleCallbacks(this);
    }

    public void loadAd() {
        if (appOpenAd != null) {
            System.out.println("Reklam yüklü galiba.");

            return; // Eğer reklam zaten yüklüyse, tekrar yükleme
        }

        AdRequest request = getAdRequest();
        AppOpenAd.load(myApplication, AD_UNIT_ID, request, AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT,
                new AppOpenAd.AppOpenAdLoadCallback() {
                    @Override
                    public void onAdLoaded(AppOpenAd ad) {
                        appOpenAd = ad;
                        Log.d("AppOpenAd", "Reklam başarıyla yüklendi.");
                        showAdIfAvailable();

                    }

                    @Override
                    public void onAdFailedToLoad(LoadAdError loadAdError) {
                        Log.d("AppOpenAd", "Reklam yüklenemedi: " + loadAdError.getMessage());
                    }
                });
    }

    private AdRequest getAdRequest() {
        return new AdRequest.Builder().build();
    }

    public void showAdIfAvailable() {
        if (appOpenAd != null) {
            appOpenAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdDismissedFullScreenContent() {
                    // Reklam kapandıktan sonra yeniden yükleyin
                    appOpenAd = null;
                  //  loadAd();
                }

                @Override
                public void onAdFailedToShowFullScreenContent(AdError adError) {
                    // Reklam gösterilemediğinde yeniden yükleyin
                    appOpenAd = null;
                    loadAd();
                }
            });

            appOpenAd.show(currentActivity);
        } else {
            loadAd();
        }
    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, Bundle savedInstanceState) {}

    @Override
    public void onActivityStarted(@NonNull Activity activity) {
        currentActivity = activity;
    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {}

    @Override
    public void onActivityPaused(@NonNull Activity activity) {}

    @Override
    public void onActivityStopped(@NonNull Activity activity) {
        currentActivity = null;
    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {}

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {}
}

