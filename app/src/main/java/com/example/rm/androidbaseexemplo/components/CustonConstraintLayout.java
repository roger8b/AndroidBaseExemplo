package com.example.rm.androidbaseexemplo.components;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.KeyEvent;

public class CustonConstraintLayout extends ConstraintLayout {

    private boolean isKeyboardShown;
    private SoftKeyboardVisibilityChangeListener listener;

    public CustonConstraintLayout(Context context) {
        super(context);
    }

    public CustonConstraintLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustonConstraintLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int proposedheight = MeasureSpec.getSize(heightMeasureSpec);
        final int actualHeight = getHeight();
        if (actualHeight > proposedheight) {
            // Keyboard is shown
            if (!isKeyboardShown) {
                isKeyboardShown = true;
                listener.onSoftKeyboardShow();
            }
        } else {
            // Keyboard is hidden <<< this doesn't work sometimes, so I don't use it
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    public boolean dispatchKeyEventPreIme(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            // Keyboard is hidden <<< RIGHT
            if (isKeyboardShown) {
                isKeyboardShown = false;
                listener.onSoftKeyboardHide();
            }
        }
        return super.dispatchKeyEventPreIme(event);
    }

    public void setOnSoftKeyboardVisibilityChangeListener(SoftKeyboardVisibilityChangeListener listener) {
        this.listener = listener;
    }

    // Callback
    public interface SoftKeyboardVisibilityChangeListener {
        public void onSoftKeyboardShow();
        public void onSoftKeyboardHide();
    }

}
