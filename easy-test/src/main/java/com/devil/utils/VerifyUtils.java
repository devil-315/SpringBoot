package com.devil.utils;

import com.devil.entity.enums.VerifyRegexEnums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ClassName：VerifyUtils
 *
 * @author: Devil
 * @Date: 2024/9/21
 * @Description:
 * @version: 1.0
 */
public class VerifyUtils {
    /*
    * regex：校验的规则
    * value：要校验的值
    * */
    public static boolean verify(String regex,String value){
        if(StringTools.isEmpty(value)){
            return false;
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    public static boolean verify(VerifyRegexEnums verifyRegexEnums,String value){
        return verify(verifyRegexEnums.getRegex(), value);
    }
}
