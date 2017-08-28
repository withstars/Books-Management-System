package com.book.web;

//LoginCommand类仅包含用户，密码这两个属性(和web请求中的 用户名/密码 参数名相同)
public class LoginCommand {

    private int id;
    private String passwd;

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPasswd() {
        return passwd;
    }

    public int getId() {
        return id;
    }
}
