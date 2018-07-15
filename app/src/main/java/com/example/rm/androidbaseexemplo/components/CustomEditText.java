package com.example.rm.androidbaseexemplo.components;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;

public class CustomEditText extends AppCompatEditText {

    private CustomEditText.Listener mListener;

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {

        if (event.getAction()!=KeyEvent.ACTION_DOWN)
            return true;
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // User has pressed Back key. So hide the keyboard
            InputMethodManager mgr = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

            if (mgr != null) {
                mgr.hideSoftInputFromWindow(this.getWindowToken(), 0);
            }

            if (mListener != null) {
                mListener.onKeyboardClosed();
            }
            return true;
        }
        return super.onKeyPreIme(keyCode, event);
    }

    public void setListener(Listener mListener) {
        this.mListener = mListener;
    }

    public interface Listener {
        void onKeyboardClosed();
    }
}
