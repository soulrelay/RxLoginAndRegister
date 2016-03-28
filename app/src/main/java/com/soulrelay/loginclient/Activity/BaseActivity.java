
package com.soulrelay.loginclient.Activity;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.AbsListView;
import android.widget.AbsSpinner;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.soulrelay.loginclient.R;
import com.soulrelay.loginclient.view.LoadingView;

import rx.Subscription;

/**
 * Created by sushuai on 2016/3/25.
 *
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
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

    protected void unbindDrawables(View view) {
        if (view != null) {
            if (view.getBackground() != null) {
                view.getBackground().setCallback(null);
            }
            if (view instanceof ImageView) {
                ImageView imageView = (ImageView) view;
                imageView.setImageDrawable(null);
            }
            if (view instanceof ViewGroup && !(view instanceof AdapterView)) {
                for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                    unbindDrawables(((ViewGroup) view).getChildAt(i));
                }
                if (!(view instanceof AbsSpinner) && !(view instanceof AbsListView)) {
                    ((ViewGroup) view).removeAllViews();
                }
            }
        }
    }

    protected View inflateSubView(int subId, int inflateId) {
        View noNetSubTree = findViewById(inflateId);
        if (noNetSubTree == null) {
            ViewStub viewStub = (ViewStub) findViewById(subId);
            noNetSubTree = viewStub.inflate();
        }
        noNetSubTree.setVisibility(View.VISIBLE);
        return noNetSubTree;
    }

    protected void showLoadingView() {
        View view = inflateSubView(R.id.activity_loading_stub,
                R.id.activity_loading_subTree);
        if (view != null) {
            LoadingView loadingView = (LoadingView) view.findViewById(R.id.loading_view);
            if (loadingView != null) {
                loadingView.showLoading(true, R.string.loading_busy, 0);
            }
        }
    }

    protected void dismissLoadingView() {
        View view = findViewById(R.id.activity_loading_subTree);
        if (view != null) {
            LoadingView loadingView = (LoadingView) view.findViewById(R.id.loading_view);
            if (loadingView != null) {
                loadingView.hidenLoading();
            }
        }
    }

    protected void showNetErroView(int id) {
        View view = inflateSubView(R.id.activity_net_error_stub,
                R.id.activity_net_error_subTree);
        if (view != null) {
            view.setVisibility(View.VISIBLE);
            //注意这里是fragment_net_error_subTree
            //add By SuS
            View refresh = view.findViewById(R.id.activity_net_error_subTree);
            TextView txtView = (TextView) view.findViewById(R.id.error_saying_bg_textview);
            if (txtView != null) {
                txtView.setText(id);
            }
            if (refresh != null) {
                refresh.setOnClickListener(this);
            }
        }
    }

    protected void dismissNetErroView() {
        View view = findViewById(R.id.activity_net_error_subTree);
        if (view != null) {
            view.setVisibility(View.GONE);
        }
    }

    /**
     * 内容为空的时候显示
     */
    protected void showContentEmptyView() {
        View view = inflateSubView(R.id.activity_empty_stub,
                R.id.activity_empty_subTree);
        view.setVisibility(View.VISIBLE);
    }

    protected void dismissContentEmptyView() {
        View view = findViewById(R.id.activity_empty_subTree);
        if (view != null) {
            view.setVisibility(View.GONE);
        }
    }


}
