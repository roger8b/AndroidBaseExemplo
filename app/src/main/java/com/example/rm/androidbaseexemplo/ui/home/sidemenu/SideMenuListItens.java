package com.example.rm.androidbaseexemplo.ui.home.sidemenu;

import com.example.rm.androidbaseexemplo.R;

import java.util.LinkedList;
import java.util.List;

public class SideMenuListItens {

    private List<SideMenuItem> sideMenuList;

    private void createList(){

        sideMenuList = new LinkedList<>();

        sideMenuList.add(new SideMenuItem(SideMenuItem.ITEM_1_ID,R.string.menu_item_1,R.drawable.ic_robot_1));
        sideMenuList.add(new SideMenuItem(SideMenuItem.ITEM_2_ID,R.string.menu_item_2,R.drawable.ic_robot_2));
        sideMenuList.add(new SideMenuItem(SideMenuItem.ITEM_3_ID,R.string.menu_item_3,R.drawable.ic_robot_3));
        sideMenuList.add(new SideMenuItem(SideMenuItem.ITEM_4_ID,R.string.menu_item_4,R.drawable.ic_robot_4));
        sideMenuList.add(new SideMenuItem(SideMenuItem.ITEM_5_ID,R.string.menu_item_5,R.drawable.ic_robot_5));
        sideMenuList.add(new SideMenuItem(SideMenuItem.ITEM_6_ID,R.string.menu_item_6,R.drawable.ic_robot_6));
        sideMenuList.add(new SideMenuItem(SideMenuItem.ITEM_7_ID,R.string.menu_item_7,R.drawable.ic_robot_7));

    }

    public List<SideMenuItem> getSideMenuList() {
        createList();
        return sideMenuList;
    }
}
