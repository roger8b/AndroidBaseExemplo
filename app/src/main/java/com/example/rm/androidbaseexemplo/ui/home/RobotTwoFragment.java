package com.example.rm.androidbaseexemplo.ui.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rm.androidbaseexemplo.R;
import com.example.rm.androidbaseexemplo.databinding.FragmentRobotTwoBinding;
import com.example.rm.androidbaseexemplo.ui.BaseFragment;

public class RobotTwoFragment  extends BaseFragment {

    FragmentRobotTwoBinding mBinding;

    public static RobotTwoFragment newInstance(){
        RobotTwoFragment robotTwoFragment = new RobotTwoFragment();
        return robotTwoFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_robot_two,container,false);

        return mBinding.getRoot();

    }
}
