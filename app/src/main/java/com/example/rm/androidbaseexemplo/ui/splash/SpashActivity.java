package com.example.rm.androidbaseexemplo.ui.splash;

import android.app.ActivityOptions;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.view.Window;

import com.example.rm.androidbaseexemplo.R;
import com.example.rm.androidbaseexemplo.databinding.ActivitySpashBinding;
import com.example.rm.androidbaseexemplo.ui.BaseActivity;
import com.example.rm.androidbaseexemplo.ui.login.LoginActivity;
import com.example.rm.androidbaseexemplo.util.GlideApp;

import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class SpashActivity extends BaseActivity {

    ActivitySpashBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_spash);


        loadData();


    }

    private void loadData() {

        loadImages();


        Completable.timer(2 , TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        homeActivity();

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    private void loadImages() {
        GlideApp.with(this)
                .load(R.drawable.ic_brook_logo)
                .into(mBinding.ivSplashLogo);
    }

    private void homeActivity() {
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
        finish();

    }

}
