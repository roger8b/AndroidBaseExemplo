package com.example.rm.androidbaseexemplo.ui.home.bottonmenu;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * Responsavel por esonder a bottonNavigation
 */

public class BottonMenu extends FrameLayout implements CoordinatorLayout.AttachedBehavior {


    public BottonMenu(@NonNull Context context) {
        super(context);
    }

    public BottonMenu(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BottonMenu(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public BottonMenu(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @NonNull
    @Override
    public CoordinatorLayout.Behavior getBehavior()
    {
        return new BottonMenuBehavior();
    }
}
