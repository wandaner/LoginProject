package com.situ.project.Tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tools {
    public static boolean CheckMobilePhoneNum(String phoneNum) {
        String regex = "^(1[3-9]\\d{9}$)";
        if (phoneNum.length() == 11) {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phoneNum);
            if (m.matches()) {
                return true;
            }
        }
        return false;
    }
}
