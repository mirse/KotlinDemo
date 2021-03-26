package com.wdz.main.main

import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.wdz.common.constant.ARouterConstant
import com.wdz.common.mvvm.BaseMvvmFragment
import com.wdz.common.net.HttpRequestStatus
import com.wdz.main.R
import com.wdz.main.databinding.FragmentHomeBinding
import com.wdz.main.main.adapter.MyHomeAdapter
import com.wdz.main.main.bean.MainArticle
import com.wdz.main.view.SearchPopupWindow
import kotlinx.android.synthetic.main.fragment_home.*


/**
 * 分页效果配合SmartRefreshLayout使用
 * @property mainArticles MutableList<MainArticle>
 * @property searchPopupWindow SearchPopupWindow
 * @property homeAdapter MyHomeAdapter
 * @property page Int
 */
@Route(path = ARouterConstant.FRAGMENT_MAIN)
class HomeFragment : BaseMvvmFragment<HomeViewModel>(), View.OnClickListener {
    private val TAG = this::class.simpleName
    var mainArticles = mutableListOf<MainArticle>()
    lateinit var searchPopupWindow:SearchPopupWindow
    private lateinit var homeAdapter: MyHomeAdapter
    private var page = 0

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun init() {

    }

    override fun initView() {

        searchPopupWindow = SearchPopupWindow(activity);
        textInput.setOnClickListener(this)
        rv_article_main.layoutManager = LinearLayoutManager(activity)

        homeAdapter = MyHomeAdapter(context!!, mainArticles,vm)


        rv_article_main.adapter = homeAdapter

//        homeAdapter.setOnClickListener(object : MyHomeAdapter.OnClickListener{
//            override fun onClickItem(position:Int,data: MainArticle) {
//
//            }
//
//            override fun onClickCollect(position:Int,data: MainArticle) {
//                Log.i(TAG, "onClickCollect: "+data)
//                if (data.collect){
//                    vm.unCollectArticle(data.id)
//                }
//                else{
//                    vm.collectArticle(data.id)
//                }
//                showLoading()
//
//
//
//            }
//
//        })

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
//        vm.getArticle().observe(this,object:Observer<PagedList<MainArticle>>{
//            override fun onChanged(t: PagedList<MainArticle>?) {
//                // TODO: 2021/3/23 分页时不触发？
//                homeAdapter.submitList(t)
//                if (smartRefreshLayout.isRefreshing){
//                    smartRefreshLayout.finishRefresh()
//                }
//
//            }
//        })


        vm.getMyArticle(page)
        vm.mainPageArticleList.observe(this,object:Observer<MutableList<MainArticle>>{
            override fun onChanged(t: MutableList<MainArticle>?) {
                if (t != null) {
                    mainArticles.clear()
                    mainArticles.addAll(t)
                    homeAdapter.notifyDataSetChanged()
                }
            }
        } )
        vm.otherPageArticleList.observe(this,object:Observer<MutableList<MainArticle>>{
            override fun onChanged(t: MutableList<MainArticle>?) {
                if (t != null) {
                    mainArticles.addAll(t)
                    homeAdapter.notifyDataSetChanged()
                }
            }
        } )


        /**
         * collect相关状态判定
         */
        vm.collectStatus.observe(this@HomeFragment,object:Observer<Int>{
            override fun onChanged(t: Int?) {
                for (i in mainArticles.indices){
                    if (t == mainArticles[i].id){
                        mainArticles[i].collect = true
                        homeAdapter.notifyDataSetChanged()
                        hideLoading()
                        return
                    }
                }
                hideLoading()
            }
        })
        /**
         * unCollect相关状态判定
         */
        vm.unCollectStatus.observe(this@HomeFragment,object:Observer<Int>{
            override fun onChanged(t: Int?) {
                for (i in mainArticles.indices){
                    if (t == mainArticles[i].id){
                        mainArticles[i].collect = false
                        homeAdapter.notifyDataSetChanged()
                        hideLoading()
                        return
                    }
                }
                hideLoading()
            }
        })



    }

    override fun vmToDataBinding() {
        (viewDataBinding as FragmentHomeBinding).model = vm
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