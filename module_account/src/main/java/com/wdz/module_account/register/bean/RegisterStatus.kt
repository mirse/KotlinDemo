package com.wdz.module_account.login.bean

import com.wdz.common.net.HttpRequestStatus
import com.wdz.common.net.response.LoginResponse

/**

 * @Author dezhi.wang

 * @Date 2021/1/13 11:35

 */
class RegisterStatus {
    //注册操作状态
    var requestStatus: HttpRequestStatus? = HttpRequestStatus.NONE
    //注册失败回调信息
    var errorMsg:String = ""

    //注册成功后登录账号是否成功
    var isLogin:Boolean = false
    //登录失败回调信息
    var loginErrorMsg:String = ""
    //登录回调信息
    var loginResponse:LoginResponse = LoginResponse()

}