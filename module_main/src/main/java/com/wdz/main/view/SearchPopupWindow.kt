package com.wdz.main.view

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wdz.common.net.BaseObserver
import com.wdz.common.net.NetManager
import com.wdz.common.net.response.HotKeyResponse
import com.wdz.common.view.BasePopupWindow
import com.wdz.main.R

class SearchPopupWindow(context: Context?) : BasePopupWindow(context) {

    var hotKeyList = mutableListOf<String>()

    override fun initView() {
        super.initView()
        val rvSearch:RecyclerView = getItemView(R.id.rv_search) as RecyclerView
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        rvSearch.layoutManager = linearLayoutManager

    }

    override fun initData() {
        hotKeyList.clear()
        NetManager.getInstance().getHotKey(object: BaseObserver<HotKeyResponse>(){
            override fun onRequestSuccess(t: HotKeyResponse?) {
                if (t!=null){
                    for (i in t.data.indices){
                        hotKeyList.add(t.data.get(i).name)
                    }
                }
            }

            override fun onRequestError(errorCode: Int, errorMsg: String?) {

            }

            override fun onRequestFailure(errorMsg: String?) {

            }

        })
    }

    override fun getLayoutId(): Int {
            return R.layout.popup_window_search
    }

    override fun getPopupWidth(): Int {
        return WindowManager.LayoutParams.MATCH_PARENT
    }

    override fun getPopupHeight(): Int {
        return WindowManager.LayoutParams.MATCH_PARENT
    }

    override fun getBackgroundDrawable(): ColorDrawable {
        return ColorDrawable(0x0000000)
    }

    override fun isPopupFocus(): Boolean {
        return true
    }

    override fun getPopupAnimationStyle(): Int {
        return R.style.PopupWindowAnimStyle
    }
}