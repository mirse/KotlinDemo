package com.wdz.main.main

import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter

import com.wdz.ktcommon.base.BaseKVmFragment
import com.wdz.ktcommon.constant.ARouterConstant
import com.wdz.ktcommon.http.HttpRequestStatus
import com.wdz.main.R
import com.wdz.main.databinding.FragmentHomeBinding
import com.wdz.main.main.adapter.MyHomeAdapter
import com.wdz.main.main.bean.MainArticle
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.support.v4.toast


/**
 * 分页效果配合SmartRefreshLayout使用
 * @property mainArticles MutableList<MainArticle>
 * @property searchPopupWindow SearchPopupWindow
 * @property homeAdapter MyHomeAdapter
 * @property page Int
 */
@Route(path = ARouterConstant.FRAGMENT_MAIN)
class HomeFragment : BaseKVmFragment(), View.OnClickListener {
    private val TAG = this::class.simpleName
    var mainArticles = mutableListOf<MainArticle>()
    private var page = 0

    private val vm by getVm<HomeViewModel>()

    private val homeAdapter by lazy {
        MyHomeAdapter(mainArticles,vm)
    }


    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun init() {

    }

    override fun initView() {
        (viewDataBinding as FragmentHomeBinding).run {
            //绑定数据
            model = vm
            activity?.let { vm.initModel(it) }
        }
        textInput.setOnClickListener(this)
        rv_article_main.run {
            layoutManager = LinearLayoutManager(activity)
            adapter = homeAdapter
        }


        /**
         * 下拉刷新
         */
        smartRefreshLayout.setOnRefreshListener {
            page = 0
            vm.getMyArticle(page)
            smartRefreshLayout.finishRefresh()
        }

        /**
         * 上滑到头加载更多
         */
        smartRefreshLayout.setOnLoadMoreListener {
            page++
            vm.getMyArticle(page)
            smartRefreshLayout.finishLoadMore()
        }
    }

    override fun initData() {

        vm.getMyArticle(page)
        vm.mainPageArticleList.observe(this,
            Observer<MutableList<MainArticle>> { t ->
                if (t != null) {
                    mainArticles.clear()
                    mainArticles.addAll(t)
                    //homeAdapter.notifyItemRangeChanged(0,mainArticles.size)
                    // TODO: 2021/3/29 使用 RecycleView + SwipeRefreshLayout 刷新时,使用notifyDataSetChange会闪烁
                    homeAdapter.notifyDataSetChanged()
                }
            })
        vm.otherPageArticleList.observe(this,
            Observer<MutableList<MainArticle>> { t ->
                if (t != null) {
                    mainArticles.addAll(t)
                    homeAdapter.notifyDataSetChanged()
                }
            })

        vm.httpLiveData.observe(this@HomeFragment,
            Observer<HttpRequestStatus> {
                when(it){
                    HttpRequestStatus.REQUESTING -> {
                        showLoading()
                    }
                    HttpRequestStatus.REQUEST_SUCCESS -> {
                        for (i in mainArticles.indices){
                            if (it.msg == mainArticles[i].id){
                                mainArticles[i].collect = it.status == "collect"
                                homeAdapter.notifyItemChanged(i)
                                break
                            }
                        }
                        hideLoading()

                    }
                    HttpRequestStatus.REQUEST_FAIL -> {
                        toast(it.msg as String)
                        hideLoading()
                    }
                    else -> {

                    }
                }

            })

    }


    override fun isUseDataBinding(): Boolean {
        return true
    }

    override fun onClick(p0: View?) {
        if (p0!=null){
            if (p0.id == R.id.textInput){
                ARouter.getInstance().build(ARouterConstant.ACTIVITY_SEARCH).navigation()
            }
        }
    }

}