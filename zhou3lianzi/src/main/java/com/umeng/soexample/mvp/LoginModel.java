package com.umeng.soexample.mvp;

import com.umeng.soexample.CallBack;

public class LoginModel {

    public void  login(String name1, String pwd1, CallBack callBack){
         if (name1.equals("123" )&&pwd1.equals("000000")){
             callBack.LoginonSuccess("登录成功");
         }else{
             callBack.onLoginFailer("登录失败");
         }
    }
}
