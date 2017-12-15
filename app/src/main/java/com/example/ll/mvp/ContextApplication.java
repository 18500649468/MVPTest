package com.example.ll.mvp;

import android.app.Application;

import com.example.ll.mvp.wxapi.UserVoinfo;

/**
 * Created by Administrator on 2017/11/9 0009.
 */

public class ContextApplication extends Application {
    private UserVoinfo userVoinfo;

    public UserVoinfo getUserVoinfo() {
        return userVoinfo;
    }

    public void setUserVoinfo(UserVoinfo userVoinfo) {
        this.userVoinfo = userVoinfo;
    }
}
