package com.wdz.ktcommon.response

import android.os.Parcel
import android.os.Parcelable
import java.util.*

class TreeResponse {
    var children: List<Children> = mutableListOf()
    var courseId = 0
    var id = 0
    var name: String = ""
    var order = 0
    var parentChapterId = 0
    var userControlSetTop = false
    var visible = 0



    class Children {
        var children: List<String> = mutableListOf()
        var courseId = 0
        var id = 0
        var name: String =""
        var order = 0
        var parentChapterId = 0
        var userControlSetTop = false
        var visible = 0




    }



}
