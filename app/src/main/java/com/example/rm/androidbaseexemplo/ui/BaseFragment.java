package com.example.rm.androidbaseexemplo.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rm.androidbaseexemplo.ui.BaseActivity;

public abstract class BaseFragment extends Fragment {

    protected BaseActivity baseActivity;




    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);
        Log.d("BaseFragment", "onAttach");
    }

    @Override
    public  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("BaseFragment", "onCreate");

        addListeners();

    }

    @Nullable
    @Override
    public  View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("BaseFragment", "onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public  void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d("BaseFragment", "onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }

    public  void initActionBar() {
        setHasOptionsMenu(true);
    }

    @Override
    public  void onResume() {
        super.onResume();
        Log.d("BaseFragment", "onResume()");
        initActionBar();
    }

    @Override
    public  void onDestroy() {
        super.onDestroy();
        Log.d("BaseFragment", "onDestroy()");
        removeListeners();
    }

    private  void addListeners() {
    }

    private  void removeListeners() {
    }

    protected  boolean isExistActivity() {
        return ((!isDetached()) && (baseActivity != null));
    }







}
