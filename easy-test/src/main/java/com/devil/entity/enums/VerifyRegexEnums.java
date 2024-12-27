package com.devil.entity.enums;

/**
 * ClassName：VerifyRegexEnums
 *
 * @author: Devil
 * @Date: 2024/9/21
 * @Description:
 * @version: 1.0
 */
public enum VerifyRegexEnums {
    PHONE("^(13[0-9]|14[5-9]|15[0-3,5-9]|16[6]|17[0-8]|18[0-9]|19[8-9])\\d{8}$","手机号码");
    private String regex;
    private String desc;

    VerifyRegexEnums(String regex, String desc) {
        this.regex = regex;
        this.desc = desc;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
