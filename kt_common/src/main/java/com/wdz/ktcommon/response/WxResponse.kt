package com.wdz.ktcommon.response

data class WxResponse(var children: List<String>,
                      var courseId: Int,
                      var id: Int,
                      var name: String,
                      var order: Int,
                      var parentChapterId: Int,
                      var userControlSetTop: Boolean,
                      var visible: Int,) {


}