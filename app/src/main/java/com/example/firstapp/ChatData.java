package com.example.firstapp;

import java.io.Serializable;

public class ChatData implements Serializable {

    private String msg;
    private String Nickname;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getNickname() {
        return Nickname;
    }

    public void setNickname(String nickname) {
        this.Nickname = nickname;
    }

}
