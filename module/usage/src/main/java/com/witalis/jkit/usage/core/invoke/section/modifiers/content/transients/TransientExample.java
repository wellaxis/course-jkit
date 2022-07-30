package com.witalis.jkit.usage.core.invoke.section.modifiers.content.transients;

import java.io.Serializable;

/**
 * Desc: Transient example
 * User: Wellaxis
 * Date: 4/12/2022
 */
public class TransientExample implements Serializable {
    public static final long serialVersionUID = 1L;
    // constant
    public static final int DEFAULT_CODE = 100;
    // serializable variables
    private final long id;
    // public modifier
    public int code;
    // private modifier
    private int mode;
    // default modifier
    boolean status = true;
    // volatile modifier
    volatile String login;
    // transient - security (not store value)
    private transient String password;
    // transient - can be calculated (by date)
    private transient int age;
    // static - not serializable
    public static String message;
    // final always serializable, but they are incompatible
    public final transient String address;

    static {
        message = "comments...";
    }

    public TransientExample(long id, int code, int mode, boolean status, String login, String password, int age, String address) {
        this.id = id;
        this.code = code;
        this.mode = mode;
        this.status = status;
        this.login = login;
        this.password = password;
        this.age = age;
        this.address = address;
    }

    @Override
    public String toString() {
        return "TransientExample [" +
            "id=" + id +
            ", code=" + code +
            ", mode=" + mode +
            ", status=" + status +
            ", login='" + login + '\'' +
            ", password='" + password + '\'' +
            ", age=" + age +
            ", address='" + address + '\'' +
            "], " +
            "Static [" + "message=" + message + "], " +
            "Default [" + "code=" + DEFAULT_CODE + "]";
    }
}
