package com.mybox.englishbox;

import android.app.Application;
import android.util.Log;

import com.oovoo.core.LoggerListener;
import com.oovoo.core.sdk_error;
import com.oovoo.sdk.api.ooVooClient;
import com.oovoo.sdk.interfaces.ooVooSdkResult;
import com.oovoo.sdk.interfaces.ooVooSdkResultListener;

/**
 * Created by jack on 4/27/16.
 */
public class MBApplication extends Application {


    private String TAG = "MBApplication";
    private ooVooClient sdk = null;
    private static final String token = "MDAxMDAxAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACRQmgIPGNwYxY3uJgssGJSlZip3%2BJtJ000V8R3ggUR7173aXHD9xp8ufQQ59xl6AYcwPWq3FpZh4jmd7eJwQLliy9kOfyLEoeTFMC8dNQEcN1GicCHrNZ%2BLHwrmsIW3XM%3D";

    @Override
    public void onCreate() {
        super.onCreate();

        ooVooClient.setContext(this);
        ooVooClient.setLogger((LoggerListener) this, LoggerListener.LogLevel.Debug);
        try {

            sdk = ooVooClient.sharedInstance();

            sdk.authorizeClient(token, new ooVooSdkResultListener() {
                @Override
                public void onResult(ooVooSdkResult result) {
                    if (result.getResult() == sdk_error.OK) {
                        // You are authorized!
                    } else {
                        //Oops, you are not authorized , you can see reason of error by call result.getDescription()
                    }
                }
            });
        } catch (InstantiationException e) {
            Log.e(TAG, e.getMessage(), e);
        } catch (IllegalAccessException e) {
            Log.e(TAG, e.getMessage(), e);
        }

        sdk.getAccount().login("username", new ooVooSdkResultListener() {
            @Override
            public void onResult(ooVooSdkResult autorize_result) {
                if (autorize_result.getResult() == sdk_error.OK) {
                    // You are logged in
                    Log.d(TAG, "login okay");
                } else {
                    // An error happened
                    Log.d(TAG, "login fail");
                }
            }

        });
    }
}
