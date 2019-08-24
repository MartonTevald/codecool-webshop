package com.codecool.shop.user.implentation;

import javax.servlet.http.HttpServletRequest;

public class SessionUtil {


    public String readFromSession(HttpServletRequest request, String name) {

        String value = String.valueOf(request.getSession().getAttribute(name));
        if (value.equals("null"))
            return "-1";
        return value;
    }
}
