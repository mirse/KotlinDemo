package com.wdz.ktcommon.constant

/**

 * @Author dezhi.wang

 * @Date 2021/3/31 13:34

 */
class ARouterConstant{
    companion object{
        //app
        const val ACTIVITY_MAIN = "/app/MainActivity"

        //module_account
        const val FRAGMENT_ACCOUNT = "/module_account/AccountFragment"
        const val ACTIVITY_LOGIN = "/module_account/LoginActivity"
        const val ACTIVITY_REGISTER = "/module_account/RegisterActivity"

        //module_article
        const val FRAGMENT_SYSTEM = "/module_article/SystemFragment"
        const val ACTIVITY_TREE_INFO = "/module_article/TreeInfoActivity"

        const val FRAGMENT_ARTICLE = "/module_article/ArticleFragment"
        const val FRAGMENT_PROJECT = "/module_article/ProjectFragment"

        //module_main
        const val FRAGMENT_MAIN = "/module_main/MainFragment"
        const val ACTIVITY_SEARCH = "/module_main/SearchActivity"
        const val ACTIVITY_SEARCH_INFO = "/module_main/SearchInfoActivity"
    }
}