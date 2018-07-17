package com.example.rm.androidbaseexemplo.components.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public abstract class BaseAdapter  <T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int lastPosition = -1;
    private boolean hasAnimation = false;
    private boolean itemPairClick = false;
    protected List<T> dataList = new ArrayList<>();

    private final PublishSubject<T> onItemClick = PublishSubject.create();
    private final PublishSubject<Pair<T, Integer>> onItemPairClick = PublishSubject.create();

    protected void enableAnimation(){
        this.hasAnimation = true;
    }

    protected void enableItemPairClick(){
        this.itemPairClick = true;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return onCreateViewHolderBase(parent, viewType);
    }

    public abstract RecyclerView.ViewHolder onCreateViewHolderBase(ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        this.onBindViewHolderBase(holder, position);

        if (itemPairClick) {
            holder.itemView.setOnClickListener(v -> onItemPairClick.onNext(
                    new Pair<>(getItem(position), position)));
        } else {
            holder.itemView.setOnClickListener(v -> onItemClick.onNext(dataList.get(position)));
        }

        animationItem(holder.itemView, holder.getAdapterPosition());
    }

    public abstract void onBindViewHolderBase(RecyclerView.ViewHolder holder, int position);

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return dataList != null && dataList.size() > 0 ? dataList.size() : 0;
    }

    private T getItem(int index) {
        if (dataList != null && dataList.get(index) != null) {
            return dataList.get(index);
        } else {
            throw new IllegalArgumentException("Item with index " + index + " doesn't exist, dataSet is " + dataList);
        }
    }

    private void animationItem(View viewToAnimate, int position) {
        if (position > lastPosition && hasAnimation) {
            YoYo.with(Techniques.BounceInLeft).playOn(viewToAnimate);
            lastPosition = position;
        }
    }

    public Observable<T> observableItemClick() {
        return onItemClick;
    }

    protected PublishSubject<T> getItemClick() {
        return onItemClick;
    }

    public Observable<Pair<T, Integer>> observableItemPairClick()
    {
        return onItemPairClick;
    }

    public PublishSubject<Pair<T, Integer>> getItemPairClick() {
        return onItemPairClick;
    }
}

