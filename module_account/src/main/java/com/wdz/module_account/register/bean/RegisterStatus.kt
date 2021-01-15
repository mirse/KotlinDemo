package com.wdz.module_account.login.bean

import com.wdz.common.net.HttpRequestStatus
import com.wdz.common.net.response.LoginResponse

/**

 * @Author dezhi.wang

 * @Date 2021/1/13 11:35

 */
class RegisterStatus {
    var requestStatus: HttpRequestStatus? = HttpRequestStatus.NONE
    var errorMsg:String = ""

    var isLogin:Boolean = false
    var loginErrorMsg:String = ""
    var loginResponse:LoginResponse = LoginResponse()

}