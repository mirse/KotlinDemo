package com.wdz.ktcommon.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter

/**
 * @Author dezhi.wang
 * @Date 2021/3/25 14:27
 */
object BindingImageView {
    // TODO: 2021/4/7  Required DataBindingComponent is null in class RecyclerItemMainArticleBindBindingImpl. A BindingAdapter in com.wdz.ktcommon.binding.BindingImageView is not static and requires an object to use, retrieved from the DataBindingComponent. If you don't use an inflation method taking a DataBindingComponent, use DataBindingUtil.setDefaultComponent or make all BindingAdapter methods static.
    //解决方法 注解@JvmStatic
    //@JvmStatic 只能用在object、companion类中 @JvmField
    //@JvmStatic fun method(){} -> public static final void method(){}
    @BindingAdapter("android:setCollected")
    @JvmStatic
    fun setCollected(imageView: ImageView, isSelected: Boolean) {
        imageView.isSelected = isSelected
    }
}