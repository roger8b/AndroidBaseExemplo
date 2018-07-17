package com.example.rm.androidbaseexemplo.ui.home.sidemenu;

import android.support.v4.text.util.LinkifyCompat;
import android.view.ViewDebug;


public class SideMenuItem {

    public static final int ITEM_1_ID = 1001;
    public static final int ITEM_2_ID = 1002;
    public static final int ITEM_3_ID = 1003;
    public static final int ITEM_4_ID = 1004;
    public static final int ITEM_5_ID = 1005;
    public static final int ITEM_6_ID = 1006;
    public static final int ITEM_7_ID = 1007;
    private int id;
    private int itemTitle;
    private int itemIcon;


    public SideMenuItem(int id, int itemTitle, int itemIcon) {
        this.id = id;
        this.itemTitle = itemTitle;
        this.itemIcon = itemIcon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(int itemTitle) {
        this.itemTitle = itemTitle;
    }

    public int getItemIcon() {
        return itemIcon;
    }

    public void setItemIcon(int itemIcon) {
        this.itemIcon = itemIcon;
    }
}
