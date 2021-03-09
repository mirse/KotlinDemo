package com.wdz.module_article.system

import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.wdz.common.constant.ARouterConstant
import com.wdz.common.mvvm.BaseMvvmActivity
import com.wdz.common.net.response.TreeResponse
import com.wdz.module_article.R
import com.wdz.module_article.adapter.TreeMainInfoAdapter
import com.wdz.module_article.databinding.ActivityTreeInfoBinding
import kotlinx.android.synthetic.main.activity_tree_info.*

@Route(path = ARouterConstant.ACTIVITY_TREE_INFO)
class TreeInfoActivity : BaseMvvmActivity<TreeInfoViewModel>() {
    private val TAG = this::class.simpleName
    @Autowired(name = "systemFragment")
    lateinit var treeResponse :TreeResponse


    override fun isTransparentBar(): Boolean {
        return true
    }

    override fun isUseDataBinding(): Boolean {
        return true
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_tree_info
    }

    override fun vmToDataBinding() {
        (viewDataBinding as ActivityTreeInfoBinding).model = vm
    }

    override fun initView() {



    }

    override fun initData() {
        val childrenList = mutableListOf<TreeResponse.Children>()
        commonTitleBar.setTitleName(treeResponse.name)
        for (i in treeResponse.children.indices){
            childrenList.add(treeResponse.children[i])
        }

        viewPager.adapter = TreeMainInfoAdapter(this,childrenList)
        TabLayoutMediator(tableLayout,viewPager,true, object:
            TabLayoutMediator.TabConfigurationStrategy{
            override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
                tab.text = treeResponse.children[position].name
            }

        }).attach()
    }
}