package com.wdz.ktcommon.http.response

data class HotKeyResponse(var id: Int,
                          var link: String,
                          var name: String,
                          var order: Int,
                          var visible: Int)