
package com.soulrelay.loginclient.Activity;

import android.support.v7.app.AppCompatActivity;

import rx.Subscription;

/**
 * Created by sushuai on 2016/3/25.
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected Subscription subscription;


    @Override
    public void onDestroy() {
        super.onDestroy();
        unsubscribe();
    }

    protected void unsubscribe() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

}
