package com.example.rm.androidbaseexemplo.ui.home;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rm.androidbaseexemplo.BR;
import com.example.rm.androidbaseexemplo.R;
import com.example.rm.androidbaseexemplo.databinding.MockListItemBinding;
import com.example.rm.androidbaseexemplo.ui.BaseAdapter;
import com.example.rm.androidbaseexemplo.ui.home.loremipsumlist.MockListItem;
import com.example.rm.androidbaseexemplo.ui.home.sidemenu.SideMenuItem;

import java.util.List;


class MockListAdapter extends BaseAdapter<MockListItem> {

    public void setData(List<MockListItem> list){
        this.enableItemPairClick();
        dataList = list;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolderBase(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mock_list_item,parent,false);
        return new MockListAdapter.MockListViewHolder(view);
    }

    @Override
    public void onBindViewHolderBase(RecyclerView.ViewHolder holder, int position) {
        MockListItem mockListItem = dataList.get(position);
        MockListItemBinding binding = ((MockListViewHolder)holder).getBinding();
        binding.setVariable(BR.mockListItem, mockListItem);
        binding.executePendingBindings();
    }


    public class MockListViewHolder extends RecyclerView.ViewHolder {
        MockListItemBinding mBinding;

        public MockListViewHolder(View view) {
            super(view);
            mBinding = DataBindingUtil.bind(view);
        }

        MockListItemBinding getBinding(){
            return mBinding;
        }
    }
}
