package com.example.rm.androidbaseexemplo.ui.home;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rm.androidbaseexemplo.BR;
import com.example.rm.androidbaseexemplo.R;
import com.example.rm.androidbaseexemplo.components.adapter.BaseAdapter;
import com.example.rm.androidbaseexemplo.databinding.SideMenuListItemBinding;
import com.example.rm.androidbaseexemplo.ui.home.sidemenu.SideMenuItem;
import com.example.rm.androidbaseexemplo.util.GlideApp;

import java.util.List;

class SideMenuAdapter extends BaseAdapter<SideMenuItem> {

    public void setData(List<SideMenuItem> list){
        this.enableItemPairClick();
        dataList = list;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolderBase(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.side_menu_list_item,parent,false);
        return new SideMenuViewHolder(view);
    }

    @Override
    public void onBindViewHolderBase(RecyclerView.ViewHolder holder, int position) {
        SideMenuItem sideMenuItem = dataList.get(position);
        SideMenuListItemBinding binding = ((SideMenuViewHolder)holder).getBinding();
        binding.setVariable(BR.sideMenuItem,sideMenuItem);
        binding.executePendingBindings();

        GlideApp.with(binding.getRoot().getContext())
                .load(dataList.get(position).getItemIcon())
                .fitCenter()
                .into(binding.ivIcon);

    }

    private class SideMenuViewHolder extends RecyclerView.ViewHolder {
        SideMenuListItemBinding mBinding;

        public SideMenuViewHolder(View view) {
            super(view);
            mBinding = DataBindingUtil.bind(itemView);
        }

        public SideMenuListItemBinding getBinding() {
            return mBinding;
        }
    }
}
