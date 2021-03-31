package com.wdz.ktcommon.response

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class LoginResponse : Parcelable {
    var isAdmin = false
    var chapterTops: List<String> = mutableListOf()
    var coinCount = 0
    var collectIds: List<String> = mutableListOf()
    var email: String? = null
    var icon: String? = null
    var id = 0
    var nickname: String? = null
    var password: String? = null
    var publicName: String? = null
    var token: String? = null
    var type = 0
    var username: String? = null



}