package com.wdz.module_account.login.bean

import com.wdz.common.net.response.LoginResponse

/**

 * @Author dezhi.wang

 * @Date 2021/1/13 11:35

 */
class LoginStatus {
    var isLoginSuccess: Boolean? = null
    var errorMsg:String = ""
    var loginResponse:LoginResponse = LoginResponse()

}