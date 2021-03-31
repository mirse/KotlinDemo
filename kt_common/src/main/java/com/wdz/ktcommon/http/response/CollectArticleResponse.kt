package com.wdz.ktcommon.http.response

/**

 * @Author dezhi.wang

 * @Date 2021/3/31 16:56

 */
data class CollectArticleResponse(var curPage: Int,
                                  var datas: List<Datas>,
                                  var offset: Int,
                                  var over: Boolean,
                                  var pageCount: Int,
                                  var size: Int,
                                  var total: Int)