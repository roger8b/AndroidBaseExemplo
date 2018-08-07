package com.example.rm.androidbaseexemplo.ui.home.homefragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rm.androidbaseexemplo.R;
import com.example.rm.androidbaseexemplo.databinding.FragmentHomeBinding;
import com.example.rm.androidbaseexemplo.ui.BaseFragment;
import com.example.rm.androidbaseexemplo.ui.home.loremipsumlist.MockItemList;

public class HomeFragment  extends BaseFragment {

    FragmentHomeBinding mBinding;



    public static HomeFragment newInstance(){
        HomeFragment homeFragment = new HomeFragment();
        return homeFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false);

        loadMockList();


        return mBinding.getRoot();

    }

    private void loadMockList() {

        MockItemList mockItemList = new MockItemList(getContext());
        mBinding.rvMockData.setLayoutManager(new LinearLayoutManager(getContext()));
        MockListAdapter mockListAdapter = new MockListAdapter();
        mockListAdapter.setData(mockItemList.getMockListItems());
        mBinding.rvMockData.setAdapter(mockListAdapter);
    }
}
