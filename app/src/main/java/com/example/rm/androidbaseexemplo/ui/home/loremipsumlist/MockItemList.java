package com.example.rm.androidbaseexemplo.ui.home.loremipsumlist;

import android.content.Context;

import com.example.rm.androidbaseexemplo.R;

import java.util.LinkedList;
import java.util.List;

public class MockItemList {

    Context mContext;

    public MockItemList(Context mContext) {
        this.mContext = mContext;
    }

    private List<MockListItem> mockListItems;


    private void listCreate(){

        mockListItems = new LinkedList<>();

        for (int i = 0; i < 20; i++) {
            mockListItems.add( new MockListItem(mContext.getString(R.string.loremipsunlist,i)));
        }
    }

    public List<MockListItem> getMockListItems() {
        listCreate();
        return mockListItems;
    }
}
