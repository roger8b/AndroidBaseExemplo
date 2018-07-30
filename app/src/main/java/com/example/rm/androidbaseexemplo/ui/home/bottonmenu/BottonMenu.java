package com.example.rm.androidbaseexemplo.ui.home.bottonmenu;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class BottonMenu extends ConstraintLayout implements CoordinatorLayout.AttachedBehavior {


    public BottonMenu(Context context) {
        super(context);
    }

    public BottonMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BottonMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @NonNull
    @Override
    public CoordinatorLayout.Behavior getBehavior()
    {
        return new BottonMenuBehavior();
    }
}
