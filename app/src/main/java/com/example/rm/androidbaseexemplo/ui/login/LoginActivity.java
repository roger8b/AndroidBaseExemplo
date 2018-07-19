package com.example.rm.androidbaseexemplo.ui.login;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.transition.AutoTransition;
import android.transition.Explode;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;

import com.example.rm.androidbaseexemplo.R;
import com.example.rm.androidbaseexemplo.components.CustomEditText;
import com.example.rm.androidbaseexemplo.components.CustonConstraintLayout;
import com.example.rm.androidbaseexemplo.databinding.ActivityLoginBinding;
import com.example.rm.androidbaseexemplo.ui.BaseActivity;
import com.example.rm.androidbaseexemplo.ui.home.HomeActivity;
import com.example.rm.androidbaseexemplo.util.GlideApp;

public class LoginActivity extends AppCompatActivity implements View.OnFocusChangeListener, CustomEditText.Listener, View.OnClickListener, View.OnTouchListener {

    ActivityLoginBinding mBinding;
    ConstraintSet constraintSet1;
    ConstraintSet constraintSet2;
    Boolean layoutFlag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        loadData();

        CustonConstraintLayout mView = (CustonConstraintLayout) mBinding.getRoot();

        mView.setOnSoftKeyboardVisibilityChangeListener(new CustonConstraintLayout.SoftKeyboardVisibilityChangeListener() {
            @Override
            public void onSoftKeyboardShow() {
                //changeLayout(false);
            }

            @Override
            public void onSoftKeyboardHide() {
                //changeLayout(true);

            }
        });

    }

    private void loadData() {


        constraintSet1 = new ConstraintSet();
        constraintSet2 = new ConstraintSet();
        constraintSet1.clone(mBinding.clRoot);
        constraintSet2.clone(this, R.layout.activity_login_up);

        loadImages();

        mBinding.etPassword.setListener(this);
        mBinding.etPassword.setOnClickListener(this);
        mBinding.etPassword.setOnFocusChangeListener(this);

        mBinding.etUserName.setListener(this);
        mBinding.etUserName.setOnClickListener(this);
        mBinding.etUserName.setOnFocusChangeListener(this);


        mBinding.btEnter.setOnClickListener(v -> {
            View view = mBinding.getRoot();
            hideKeyboard(this);
            view.clearFocus();
            changeLayout(true);
            showHomeActivity();
        });


    }

    private void showHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    private void loadImages() {
        GlideApp.with(this)
                .load(R.drawable.ic_brook_logo)
                .fitCenter()
                .into(mBinding.ivLogo);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void closeKeyboard() {
        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputManager != null && inputManager.isAcceptingText()) {
            inputManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
        }
    }

    @Override
    public void onKeyboardClosed() {
        if (!layoutFlag) {
            changeLayout(true);
        } else {
            onBackPressed();
        }

    }

    private void changeLayout(Boolean changed) {
        ConstraintSet constraintSet;
        AutoTransition autoTransition = new AutoTransition();
        autoTransition.setDuration(300);
        TransitionManager.beginDelayedTransition(mBinding.clRoot, autoTransition);
        if (changed) {
            constraintSet = constraintSet1;
            layoutFlag = true;
        } else {
            //mBinding.ivLoginLogo.setVisibility(View.GONE);
            constraintSet = constraintSet2;
            layoutFlag = false;
        }
        constraintSet.applyTo(mBinding.clRoot);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.et_user_name || v.getId() == R.id.et_password) {
            changeLayout(false);
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            changeLayout(false);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (v.getId() == R.id.et_password || v.getId() == R.id.et_user_name) {
            changeLayout(false);
        }
        return false;
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
