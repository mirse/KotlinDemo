package com.wdz.ktcommon.http.response

data class CollectWebResponse(var data: Data) {


    data class Data(
        var desc: String,
        var icon: String,
        var id: Int,
        var link: String,
        var name: String,
        var order: Int,
        var userId: Int,
        var visible: Int
    ) {

    }
}