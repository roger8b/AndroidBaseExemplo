package com.example.rm.androidbaseexemplo.ui.home.bottonmenu;

import android.graphics.Rect;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.view.View;

class BottonMenuBehavior extends CoordinatorLayout.Behavior {

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof AppBarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        if(dependency instanceof AppBarLayout){
            AppBarLayout appBarLayout = (AppBarLayout) dependency;

            Rect rect = new Rect();
            appBarLayout.getHitRect(rect);

            if (rect.bottom <= ViewCompat.getMinimumHeight(appBarLayout)){
                hide(child, parent.getHeight());
            }
            else
            {
                show(child,parent.getHeight() - child.getHeight());
            }

            return true;
        }

        return false;

    }

    private void show(View view, float y) {
        view.animate().y(y);
    }

    private void hide(View view, float y) {
        view.animate().y(y);
    }
}
