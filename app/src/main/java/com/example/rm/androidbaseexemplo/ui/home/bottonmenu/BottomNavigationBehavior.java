package com.example.rm.androidbaseexemplo.ui.home.bottonmenu;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

public class BottomNavigationBehavior extends CoordinatorLayout.Behavior<BottomNavigationView> {

    public BottomNavigationBehavior() {
        super();
    }

    public BottomNavigationBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, BottomNavigationView child, View dependency) {
        boolean dependsOn = dependency instanceof FrameLayout;
        return dependsOn;
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull BottomNavigationView child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }


    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull BottomNavigationView child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        if (dy < 0) {
            showBottomNavigationView(child, coordinatorLayout.getHeight()-child.getHeight());
        } else if (dy > 0) {
            hideBottomNavigationView(child, coordinatorLayout.getHeight());
        }
    }


    private void hideBottomNavigationView(BottomNavigationView view, int height) {
        //view.animate().translationY(view.getHeight());
        view.animate().y(height);
    }

    private void showBottomNavigationView(BottomNavigationView view, int height) {
        //view.animate().translationY(0);
        view.animate().y(height);
    }
}
