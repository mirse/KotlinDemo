package com.wdz.common.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.PopupWindow;


/**
 * popupWindow基类
 */
public abstract class BasePopupWindow extends PopupWindow {
    private final String TAG = this.getClass().getSimpleName();
    private Context context;
    private View mView;

    public BasePopupWindow(Context context) {
        super(context);
        this.context = context;
        initView();
        initData();
    }

    protected abstract void initData();


    protected void initView() {
        mView = LayoutInflater.from(getContext()).inflate(getLayoutId(), null);
        this.setContentView(mView);
        this.setAnimationStyle(getPopupAnimationStyle());
        this.setWidth(getPopupWidth());
        this.setHeight(getPopupHeight());
        this.setBackgroundDrawable(getBackgroundDrawable());
        this.setFocusable(isPopupFocus());
        mView.getRootView().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                dismiss();
                return true;
            }
        });
    }
    /**
     * 获取子View
     * @param viewId
     * @return
     */
    public View getItemView(int viewId){
        if (mView!=null){
            return mView.findViewById(viewId);
        }
        return null;
    }

    public Context getContext(){
        return context;
    }

    /**
     * 获取popWindow layoutId
     *
     * @return
     */
    public abstract int getLayoutId();
    /**
     * 获取popWindow animation
     *
     * @return
     */
    public abstract int getPopupAnimationStyle();

    /**
     * 获取popWindow width
     *
     * @return
     */
    public abstract int getPopupWidth();

    /**
     * 获取popWindow height
     *
     * @return
     */
    public abstract int getPopupHeight();

    /**
     * Popup是否有焦点
     *
     * @return
     */
    public abstract boolean isPopupFocus();

    /**
     * 获取popWindow BackgroundDrawable
     *
     * @return
     */
    public abstract ColorDrawable getBackgroundDrawable();



}
