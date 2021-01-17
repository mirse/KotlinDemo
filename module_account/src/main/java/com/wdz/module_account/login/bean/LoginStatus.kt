package com.wdz.module_account.login.bean

import com.wdz.common.net.HttpRequestStatus
import com.wdz.common.net.response.LoginResponse

/**

 * @Author dezhi.wang

 * @Date 2021/1/13 11:35

 */
class LoginStatus {
    // TODO: 2021/1/17 状态抽取基类 
    var requestStatus: HttpRequestStatus? = HttpRequestStatus.NONE
    var errorMsg:String = ""
    var loginResponse:LoginResponse = LoginResponse()

}