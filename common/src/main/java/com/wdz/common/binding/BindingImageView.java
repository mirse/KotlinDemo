package com.wdz.common.binding;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

/**
 * @Author dezhi.wang
 * @Date 2021/3/25 14:27
 */
public class BindingImageView {
    @BindingAdapter("android:setCollected")
    public static void setCollected(ImageView imageView,boolean isSelected){
        imageView.setSelected(isSelected);
    }
}
