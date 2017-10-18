package com.bewei.eventbus24.bean;

/**
 * 1. 类的用途
 * 2. @author forever
 * 3. @date 2017/10/10 16:03
 */


public class MessageEvent {
    private String msg;

    public MessageEvent(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
