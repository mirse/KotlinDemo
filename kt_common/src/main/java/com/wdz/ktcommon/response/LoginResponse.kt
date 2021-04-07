package com.wdz.ktcommon.response

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LoginResponse(var isAdmin: Boolean,
                         var chapterTops: List<String>,
                         var coinCount: Int,
                         var collectIds: List<String>,
                         var email: String,
                         var icon: String,
                         var id: Int,
                         var nickname: String,
                         var password: String,
                         var publicName: String,
                         var token: String,
                         var type: Int,
                         var username: String) : Parcelable