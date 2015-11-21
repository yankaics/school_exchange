package com.schoolexchange.www.tools;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * Created by shadow on 2015/11/20.
 *
 */
public class MyAuthenticator extends Authenticator {

    String userName = null;
    String password = null;

    public MyAuthenticator() {
    }

    public MyAuthenticator(String username, String password) {
        this.userName = username;
        this.password = password;
    }

    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(userName, password);
    }
}
